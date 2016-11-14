package Snek;

import java.util.Scanner;

class CoordinateRow {
	private Coordinate[] rows;
	private int length;

	CoordinateRow(int length) {
		rows = new Coordinate[length];
	}

	int getLength(){
		return length;
	}

	CoordinateRow(int length, String coordinates) {
		this(length);
		Scanner in = new Scanner(coordinates);
		while (in.hasNextInt()) {
			addCoordinateAfter(new Coordinate(in));
		}
		in.close();
	}

	void addCoordinateAfter(Coordinate coordinate) {
		rows[length] = coordinate;
		length++;
	}

	Coordinate getRow(int index) {
		return rows[index];
	}

	void setRow(int index, Coordinate value) {
		rows[index] = value;
	}

	public String toString() {
		StringBuilder cr = new StringBuilder();
		for(int i = 0; i < length ; i++) {
			cr.append(rows[i].toString()).append(" ");
		}
		return cr.toString();
	}
}