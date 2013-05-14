package kth.csc.inda.ads;

/**
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-12
 */
public class TwoControls {
	private int left, right;
	
	public TwoControls(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	//
	// Below is the set and get methods for the 2 snake controls.
	//

	public int right() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int left() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}
	

}