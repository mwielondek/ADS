package kth.csc.inda.ads;

import java.awt.Color;

public class NSnakeSeg implements ActAndDraw {
	private NSnake snake;
	private Location loc;

	/**
	 * Constructor
	 * 
	 * @param snake
	 */
	public NSnakeSeg(NSnake snake, Location loc) {
		this.snake = snake;
		this.loc = loc;
	}

	/**
	 * @return The color of the segment.
	 */
	public Color getColor() {
		return snake.getColor();
	}

	/**
	 * @return The location of the segment.
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean act(NSnake otherSnake) {
		otherSnake.kill("died by " + snake.getName());
		return false;
	}
}
