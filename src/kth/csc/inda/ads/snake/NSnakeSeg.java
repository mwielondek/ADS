package kth.csc.inda.ads.snake;

import java.awt.Color;

import kth.csc.inda.ads.ActAndDraw;
import kth.csc.inda.ads.Location;

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