package kth.csc.inda.ads;

import java.awt.Color;

import kth.csc.inda.ads.snake.NSnake;

/**
 * @author jsimo
 *
 */
public interface ActAndDraw {
	
	/**
	 * An action that will do stuff with the snake.
	 * @param snake
	 * @return If the action breaks the movement loop.
	 */
	public boolean act(NSnake snake);
	
	/**
	 * @return A color.
	 */
	public Color getColor();
}