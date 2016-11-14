package Snek;

enum Direction {
	UP      (1),
	DOWN    (-1),
	LEFT    (2),
	RIGHT   (-2);

	private final int value;
	Direction(int ordinal_value) {
		this.value = ordinal_value;
	}

	private static final Direction DEFAULT_DIRECTION = Direction.RIGHT;

	static Direction fromString(String direction) {
		switch (direction) {
			case "U":
				return Direction.UP;
			case "D":
				return Direction.DOWN;
			case "L":
				return Direction.LEFT;
			case "R":
				return Direction.RIGHT;
			default:
				return DEFAULT_DIRECTION;
		}
	}

	boolean isOpposite(Direction direction) {
		return this.value + direction.value == 0;
	}
}
