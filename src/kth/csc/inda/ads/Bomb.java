package kth.csc.inda.ads;

import java.awt.Color;

import kth.csc.inda.ads.snake.NSnake;

/**
 * A bomb, a object that kill you if you go into it.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-10
 */
public class Bomb implements ActAndDraw {
	private Color color = Color.BLACK;
	
	/**
	 * Contructor.
	 */
	public Bomb() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean act(NSnake snake) {
		snake.kill("died by a bomb");
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		return color;
	}

}
