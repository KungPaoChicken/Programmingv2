package Snek;

import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

import java.util.Scanner;

public class Game {

	private static final boolean LOW_RESOLUTION = false;
	private static final int FRAMES_PER_SECOND = 10;

	private Field field;
	private Renderer ui;

	private Game() {
		if (UIAuxiliaryMethods.askUserForBoolean("Play from input file?")) {
			UIAuxiliaryMethods.askUserForInput();
			Scanner file = new Scanner(System.in);
			field = new Field(file);
			file.close();
		} else {
			field = new Field();
		}
		UserInterfaceFactory.enableLowResolution(LOW_RESOLUTION);
		ui = new Renderer(Field.WIDTH, Field.HEIGHT, FRAMES_PER_SECOND);
		ui.render(field);
	}

	private void start() {
		Direction newDirection = field.getSnake().getDirection();
		while (true) {
			EventHandler event = new EventHandler(ui.getEvent());
			if (event.isMovementCommand()) {
				newDirection = Direction.fromString(event.getData());
			}
			if (event.isFrame()) {
				// Direction is set here so it is strictly synchronized to the frame
				field.getSnake().setDirection(newDirection);
				Coordinate newHead = field.nextHead();
				if (field.snakeTouchesSomething(newHead)) {
					ui.print("Game over");
					ui.pause();
					break;
				}
				Coordinate oldTail = field.getSnake(field.getSnakeSize() - 1);
				boolean snakeEats = field.update(newHead);
				ui.updateSnake(snakeEats, field, oldTail, field.getSnake().getHead());
			}
		}
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
