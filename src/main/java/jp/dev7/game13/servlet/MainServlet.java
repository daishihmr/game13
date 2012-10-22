package jp.dev7.game13.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.dev7.game13.ServerMain;
import jp.dev7.game13.websocket.EchoSocket;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.google.common.collect.Maps;

@SuppressWarnings("serial")
public class MainServlet extends WebSocketServlet {
	private static class Router {

		private final Map<String, Class<? extends WebSocket>> patterns = Maps
				.newHashMap();

		public void add(String pattern, Class<? extends WebSocket> webSocket) {
			patterns.put(pattern, webSocket);
		}

		public Class<? extends WebSocket> route(String path) {
			for (String pattern : patterns.keySet()) {
				if (path.matches(pattern)) {
					return patterns.get(pattern);
				}
			}
			return null;
		}
	}

	private final Router router;

	public MainServlet() {
		router = new Router();
		router.add("/echo", EchoSocket.class);
	}

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest req, String origin) {
		final Class<? extends WebSocket> socketClass = router.route(req
				.getRequestURI());
		if (socketClass == null) {
			return null;
		}
		return ServerMain.getInjector().getInstance(socketClass);
	}

}
