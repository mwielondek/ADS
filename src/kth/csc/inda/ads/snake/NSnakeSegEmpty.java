package kth.csc.inda.ads.snake;

import java.awt.Color;

import kth.csc.inda.ads.Location;

/**
 * 
 * @author Jesper Simonsson
 *
 */

public class NSnakeSegEmpty extends NSnakeSeg {

	/**
	 * Constructor.
	 */
	public NSnakeSegEmpty(NSnake snake, Location loc) {
		super(snake, loc);
	}
	
	@Override
	public Color getColor() {
		return Color.WHITE;
	}

	@Override
	public boolean act(NSnake otherSnake) {
		// Do nothing as it an empty hole.
		return true;
	}
}