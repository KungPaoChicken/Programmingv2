package Snek;

import ui.UIAuxiliaryMethods;

import java.util.Scanner;

class Field {

	public static final int WIDTH = 32;
	public static final int HEIGHT = 24;

	private CoordinateRow walls;
	private Snake snake;
	private Coordinate apple;

	private int width, height;

	Field() {
		init();
		snake = new Snake();
		walls = new CoordinateRow(width * height);
		makeApple();
	}

	Field(Scanner in) {
		init();
		// Fix snake constructor to depend on field
		snake = new Snake(in);
		walls = new CoordinateRow(width * height, in.next());
		makeApple();
	}

	private void init() {
		width = WIDTH;
		height = HEIGHT;
		apple = new Coordinate(0, 0);
	}

	int getWidth() {
		return width;
	}

	int getHeight() {
		return height;
	}

	Snake getSnake(){
		return snake;
	}

	int getSnakeSize() {
		return snake.getBody().getLength();
	}

	Coordinate getSnake(int i){
		return snake.getBody().getRow(i);
	}

	CoordinateRow getWalls() {
		return walls;
	}

	Coordinate getApple() {
		return apple;
	}

	private void makeApple() {
		do {
			apple.set(UIAuxiliaryMethods.getRandom(0, width), UIAuxiliaryMethods.getRandom(0, height));
		} while (apple.isIn(snake.getBody()) || apple.isIn(walls));
	}

	Coordinate nextHead() {
		int x = snake.getHead().getX();
		int y = snake.getHead().getY();

		switch (snake.getDirection()) {
			// Check on left and up for optimizations
			case LEFT:
				x = x - 1;
				x = (x < 0) ? width + x : x % width;
				break;
			case UP:
				y = y - 1;
				y = (y < 0) ? height + y : y % height;
				break;
			case RIGHT:
				x = (x + 1) % width;
				break;
			case DOWN:
				y = (y + 1) % height;
				break;
			default:
				System.out.println("You messed something up mate");
		}
		return new Coordinate(x, y);
	}

	boolean snakeTouchesSomething(Coordinate newHead) {
		return newHead.isIn(snake.getBody()) || newHead.isIn(walls);
	}

	boolean update(Coordinate newHead) {
		boolean snakeEats = newHead.isEqualTo(apple);
		if (snakeEats) {
			snake.grow(newHead);
			makeApple();
		}
		snake.updateHead(newHead);
		return snakeEats;
	}
}
