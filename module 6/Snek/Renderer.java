package Snek;

import ui.Event;
import ui.SnakeUserInterface;
import ui.UserInterfaceFactory;

class Renderer {

	private SnakeUserInterface ui;

	Renderer(int width, int height, double fps) {
		ui = UserInterfaceFactory.getSnakeUI(width, height);
		ui.setFramesPerSecond(fps);
	}

	private void render(CoordinateRow rows, int symbol) {
		for (int i = 0; i < rows.getLength(); i++) {
			ui.place(rows.getRow(i).getX(), rows.getRow(i).getY(), symbol);
		}
		ui.showChanges();
	}

	private void render(Coordinate c, int symbol) {
		ui.place(c.getX(), c.getY(), symbol);
		ui.showChanges();
	}

	void render(Field field) {
		render(field.getWalls(), SnakeUserInterface.WALL);
		render(field.getSnake().getBody(), SnakeUserInterface.SNAKE);
		render(field.getApple(), SnakeUserInterface.FOOD);
	}

	private void remove(Coordinate c) {
		render(c, SnakeUserInterface.EMPTY);
	}

	Event getEvent() {
		return ui.getEvent();
	}

	void pause() {
		ui.setFramesPerSecond(0);
	}

	void print(String message) {
		ui.printf(message);
	}

	// Much more faster than doing a full render every time
	void updateSnake(boolean snakeEats, Field field, Coordinate oldTail, Coordinate newHead) {
		if (!snakeEats) {
			remove(oldTail);
		} else {
			render(field.getApple(), SnakeUserInterface.FOOD);
		}
		render(newHead, SnakeUserInterface.SNAKE);
	}
}