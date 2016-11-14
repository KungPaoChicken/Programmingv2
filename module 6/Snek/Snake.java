package Snek;

import java.util.Scanner;

class Snake {
	private static final String DEFAULT_COORDINATES = "0 0\n0 1";
	private static final Direction DEFAULT_DIRECTION = Direction.RIGHT;

	private CoordinateRow snake;
	private Direction direction;

	Snake() {
		snake = new CoordinateRow(Field.WIDTH * Field.HEIGHT, DEFAULT_COORDINATES);
		direction = DEFAULT_DIRECTION;
	}

	Snake(Scanner in) {
		in.useDelimiter("=");
		snake = new CoordinateRow(Field.WIDTH * Field.HEIGHT, in.next());
		direction = Direction.fromString(in.next());
	}

	CoordinateRow getBody() {
		return snake;
	}

	Direction getDirection() {
		return direction;
	}

	void setDirection(Direction newDirection) {
		if (!newDirection.isOpposite(direction)) {
			direction = newDirection;
		}
	}

	Coordinate getHead(){
		return snake.getRow(0);
	}

	void updateHead(Coordinate newHead) {
		for (int i = snake.getLength() - 1; i > 0; i--) {
			snake.setRow(i, snake.getRow(i - 1));
		}
		snake.setRow(0, newHead);
	}

	void grow(Coordinate position) {
		snake.addCoordinateAfter(new Coordinate(position.getX(), position.getY()));
	}

	public String toString() {
		return snake.toString();
	}
}
