package kth.csc.inda.ads.snake;

import kth.csc.inda.ads.Location;

/**
 * A part of the snakes body. 
 * Will have same color as the players snake.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-10
 */

public class NSnakeSegBody extends NSnakeSeg {

	/**
	 * Constructor.
	 */
	public NSnakeSegBody(NSnake snake, Location loc) {
		super(snake, loc);
	}

	@Override
	public boolean act(NSnake otherSnake) {
		otherSnake.kill("died by " + super.getSnake().getName());
		return false;
	}

}
