package jp.dev7.game13;

import java.io.FileInputStream;
import java.util.Properties;

import jp.dev7.game13.servlet.MainServlet;
import jp.dev7.game13.websocket.EchoSocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ServerMain {

	private static final Properties defaultProperties;
	static {
		defaultProperties = new Properties();
		defaultProperties.put("port", "8080");
	}

	private static Injector injector;

	public static void main(String[] args) throws Exception {
		final Properties prop = new Properties(defaultProperties);
		prop.load(new FileInputStream("conf.xml"));
		final Conf conf = new Conf(prop);

		final Module module = new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(Conf.class).toInstance(conf);
				binder.bind(EchoSocket.class);
			}
		};
		injector = Guice.createInjector(module);

		final HandlerList handlers = new HandlerList();

		final ServletHandler sh = new ServletHandler();
		sh.addServletWithMapping(MainServlet.class, "/*");
		handlers.addHandler(sh);

		final Server server = new Server(conf.getPort());
		server.setHandler(handlers);
		server.start();
	}

	public static Injector getInjector() {
		return injector;
	}
}
