package kth.csc.inda.ads.snake;

import java.awt.Color;

import kth.csc.inda.ads.Location;

public class NSnakeSegHead extends NSnakeSegBody {
	
	/**
	 * Constructor.
	 */
	public NSnakeSegHead(NSnake snake, Location loc) {
		super(snake, loc);
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}
}
