package kth.csc.inda.ads;

/**
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-10
 */
public class Controls {
	private int up, down, left, right;
	
	public Controls(int up, int down, int left, int right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	// 
	// Below is the get methods for the 4 snake controls.
	// 

	public int up() {
		return up;
	}

	public int right() {
		return right;
	}

	public int down() {
		return down;
	}

	public int left() {
		return left;
	}
}
