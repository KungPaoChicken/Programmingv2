package Snek;

import ui.Event;

class EventHandler {

	private static final String ARROW="arrow";
	private static final String ALARM="alarm";
	private static final String REFRESH="refresh";
	private String type, data;

	EventHandler(Event event) {
		type = event.name;
		data = event.data;
	}

	boolean isMovementCommand() {
		return type.equals(ARROW);
	}

	String getData() {
		return data;
	}

	boolean isFrame() {
		return type.equals(ALARM) && data.equals(REFRESH);
	}
}
