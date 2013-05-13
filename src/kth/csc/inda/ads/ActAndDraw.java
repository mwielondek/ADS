package kth.csc.inda.ads;

import java.awt.Color;

import kth.csc.inda.ads.snake.NSnake;

/**
 * Interface for objects that will exist on the field. 
 * All of them needs to have a color and a act method.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-13
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