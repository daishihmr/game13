package jp.dev7.game13.message;

import com.googlecode.actorom.Address;

public class JoinMessage {

	private final Address who;

	public JoinMessage(Address who) {
		this.who = who;
	}

	public Address getWho() {
		return who;
	}

}
