package kth.csc.inda.ads.snake;

import java.awt.Color;

import kth.csc.inda.ads.ActAndDraw;
import kth.csc.inda.ads.Location;

/**
 * A basic snakesegment for other classes to extend up on.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-12
 */
public abstract class NSnakeSeg implements ActAndDraw {
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
		 * Return the snake.
		 */
		protected NSnake getSnake() {
			return snake;
		}
	
		/**
		 * {@inheritDoc}
		 */
		@Override
		public abstract boolean act(NSnake otherSnake);
	}