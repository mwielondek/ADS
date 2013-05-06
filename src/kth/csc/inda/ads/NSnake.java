package kth.csc.inda.ads;

import java.awt.Color;
import java.util.LinkedList;

public class NSnake {
	// Name of the snake.
	private String name;
	// Color of the snake.
	private Color color;
	// LinkedList of the snake-segments.
	private LinkedList<NSnakeSeg> body = new LinkedList<NSnakeSeg>();
	// Max length of the snake, if <= 0 then endless snake size.
	private int maxLength;
	// Current direction of the snake.
	private Direction direction;
	// Boolean field to see if it is turning.
	private boolean turning;
	// If the snake is dead.
	private boolean isAlive;
	// Field where the snake is placed in.
	private NField field;
	// The cause of death if there is any.
	private String causeOfDeath;
	// Controls to steer the snake with.
	private Controls controls;

	public NSnake(String name, Color color, NField field, Location startLoc, Direction direction, Controls controls, int maxLength) {
		this.name = name;
		this.color = color;
		this.field = field;
		this.controls = controls;
		this.maxLength = maxLength;
		this.direction = direction;
		this.isAlive = true;
		
		// Add the starting segment of the snake.
		body.addFirst(new NSnakeSeg(this, startLoc));
	}

	/**
	 * @return The color of the snake.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the snake.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Controls getControls() {
		return controls;
	}

	/**
	 * @return Current length of the snake.
	 */
	public int getMaxLength() {
		return maxLength;
	}
	
	/**
	 * @return Name of the snake.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the maximum allowed length of the snake.
	 * @param maxLength The maximum allowed length of the snake.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	private void changeDirection(Direction direction) {
		this.direction = direction;
		turning = true;
	}
	
	/**
	 * Sets a new direction if a new direction has not already been set.
	 * @param newDirection
	 * @return True if possible to change direction, otherwise false.
	 */
	public boolean turn(Direction newDirection) {
		// If the direction has not been changed.
		if (!turning) {
			// Each case has 2 subcases that are possible to
			// proceed changing direction to.
			System.out.println(newDirection);
			switch (direction) {
			case DOWN:
				switch (newDirection) {
				case DOWN:
					return false;
				case LEFT:
					changeDirection(newDirection);
					return true;
				case RIGHT:
					changeDirection(newDirection);
					return true;
				case UP:
					return false;
				}
			case LEFT:
				switch (newDirection) {
				case DOWN:
					changeDirection(newDirection);
					return true;
				case LEFT:
					return false;
				case RIGHT:
					return false;
				case UP:
					changeDirection(newDirection);
					return true;
				}
			case RIGHT:
				switch (newDirection) {
				case DOWN:
					changeDirection(newDirection);
					return true;
				case LEFT:
					return false;
				case RIGHT:
					return false;
				case UP:
					changeDirection(newDirection);
					return true;
				}
			case UP:
				switch (newDirection) {
				case DOWN:
					return false;
				case LEFT:
					changeDirection(newDirection);
					return true;
				case RIGHT:
					changeDirection(newDirection);
					return true;
				case UP:
					return false;
				}
			}
		} 
		// When the direction has already been changed.
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public void move() {
		Location loc = body.getFirst().getLoc();
		Location newLoc;
		int row = loc.getRow();
		int col = loc.getCol();
		switch (direction) {
		case RIGHT:
			col++;
			break;
		case UP:
			row--;
			break;
		case LEFT:
			col--;
			break;
		case DOWN:
			row++;
			break;
		}
		// Reset the turning
		turning = false;
		
		// Checks for if the next move is within the field.
		if (col < 0 || col >= field.getDepth() || row < 0
				|| row >= field.getWidth()) {
			kill("crashed into a wall");
			return; 
		}
		
		newLoc = new Location(row, col);

		// Can we add it to the spot we want to?
		ActAndDraw actor;
		if ((actor = field.getObjectAt(newLoc)) != null) {
			if(!actor.act(this)) {
				// Return if the actor method does not want the snake to move any further.
				return;
			}
		}

		// Add the new location to snakes body.
		addFirst(newLoc);

		if (body.size() > maxLength) {
			// Remove and the corresponding segment and field.
			removeLast();
		}
	}
	
	private void addFirst(Location loc) {
		NSnakeSeg newSeg = new NSnakeSeg(this, loc);
		body.addFirst(newSeg);
		field.place(newSeg, loc);
	}

	private void removeLast() {
		field.clear(body.removeLast().getLoc());
	}
	
	/**
	 * Sets the snake to be dead.
	 */
	public void kill(String causeOfDeath) {
		isAlive = false;
		this.causeOfDeath = causeOfDeath;
	}
	
	/**
	 * @return True if alive, otherwise false.
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Returns info about the player.
	 */
	public String toString() {
		if (isAlive()) {
			return name + ", " + body.getFirst().getLoc() + " is alive.";
		} 
		return name + ", " + body.getFirst().getLoc() + " " + causeOfDeath;
	}

}
