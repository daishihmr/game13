package jp.dev7.game13;

import java.util.Properties;

public class Conf {

	private final Properties props;

	public Conf(Properties props) {
		this.props = props;
	}

	public int getPort() {
		return Integer.parseInt(props.getProperty("port"));
	}
}
