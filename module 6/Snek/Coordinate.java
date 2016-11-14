package Snek;

import java.util.Scanner;

class Coordinate {
	// Copyright(c) Yoyo Lam
	// Date: 5-12-2013
	private int x;
	private int y;

	Coordinate(int x, int y) {
		set(x, y);
	}

	Coordinate(Scanner in) {
		this(in.nextInt(), in.nextInt());
	}

	boolean isEqualTo(Coordinate coordinate) {
		return this.getX() == coordinate.getX() && this.getY() == coordinate.getY();
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	boolean isIn(CoordinateRow haystack) {
		for (int i = 0; i < haystack.getLength(); i++) {
			if (this.isEqualTo(haystack.getRow(i))) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
