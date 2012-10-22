package jp.dev7.game13.websocket;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

public class EchoSocket implements OnTextMessage {

	private Connection conn;

	@Override
	public void onOpen(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void onMessage(String message) {
		try {
			conn.sendMessage("echo > " + message);
		} catch (IOException e) {
		}
	}

	@Override
	public void onClose(int closeCode, String message) {
	}

}
