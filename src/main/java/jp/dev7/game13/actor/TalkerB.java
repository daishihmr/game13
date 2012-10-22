package jp.dev7.game13.actor;

import com.googlecode.actorom.annotation.OnMessage;

public class TalkerB {

	@OnMessage(type = String.class)
	public void onHi(String message) {
		System.out.println("onHi");
		System.out.println("get message = " + message);
	}

}
