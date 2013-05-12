package kth.csc.inda.ads;

import java.awt.Color;

import kth.csc.inda.ads.snake.NSnake;

/**
 * @author Jesper Simonsson
 */

public class Bomb implements ActAndDraw {
	private Color color = Color.BLACK;
	
	public Bomb() {
	}

	@Override
	public boolean act(NSnake snake) {
		snake.kill("died by a bomb");
		return false;
	}

	@Override
	public Color getColor() {
		return color;
	}

}
