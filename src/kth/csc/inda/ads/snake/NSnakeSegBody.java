package kth.csc.inda.ads.snake;

import kth.csc.inda.ads.Location;

/**
 * 
 * @author Jesper Simonsson
 *
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
