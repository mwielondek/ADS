package kth.csc.inda.ads.snake;

import java.awt.Color;

/**
 * The snakeheadsegment. Is blue.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-13
 */

import kth.csc.inda.ads.Location;

public class NSnakeSegHead extends NSnakeSegBody {
	
	/**
	 * Constructor.
	 */
	public NSnakeSegHead(NSnake snake, Location loc) {
		super(snake, loc);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		return Color.BLUE;
	}
}
