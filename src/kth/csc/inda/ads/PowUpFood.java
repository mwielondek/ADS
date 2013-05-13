package kth.csc.inda.ads;

import java.awt.Color;

import kth.csc.inda.ads.snake.NSnake;

/**
 * A class describing the food power up which causes the snake to grow longer.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-12
 */

public class PowUpFood implements Powerup {
	private Color color = Color.GREEN;
	
	/**
	 * Constructor.
	 */
	public PowUpFood(){
	}
	
	/**
	 * Interact with the snake that tries to move.
	 * @param the snake that tries to move to this location
	 */
	@Override
	public boolean act(NSnake snake) {
		snake.setMaxLength(snake.getMaxLength() + 5);
		// make the snake longer
		return true;
	}

	/**
	 * returns the color of this power up.
	 * @return the color of this power up.
	 */
	@Override
	public Color getColor() {
		return color;
	}

}
