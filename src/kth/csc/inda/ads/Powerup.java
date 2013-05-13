/**
 * 
 */
package kth.csc.inda.ads;

import java.awt.Color;

import kth.csc.inda.ads.snake.NSnake;

/**
 * An interface class for the power up objects in the game.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-12
 */
interface Powerup extends ActAndDraw {

	/**
	 * A way for the powerup to interact with the snake that takes it.
	 */
	@Override
	public boolean act(NSnake snake);

	/**
	 * returns the color of the power up
	 */
	@Override
	public Color getColor();

}
