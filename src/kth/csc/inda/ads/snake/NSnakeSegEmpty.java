package kth.csc.inda.ads.snake;

import java.awt.Color;

import kth.csc.inda.ads.Location;

/**
 * A empty snake segment. Which means you can walk over it and nothing happens.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-12
 */

public class NSnakeSegEmpty extends NSnakeSeg {

	/**
	 * Constructor.
	 */
	public NSnakeSegEmpty(NSnake snake, Location loc) {
		super(snake, loc);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		return Color.WHITE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean act(NSnake otherSnake) {
		// Do nothing as it an empty hole.
		return true;
	}
}