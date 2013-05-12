package kth.csc.inda.ads;


/**
 * 
 * @author Jesper Simonsson
 *
 */
public class TwoControls {
	private int left, right;
	
	public TwoControls(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	//
	// Below is the get methods for the 2 snake controls.
	//

	public int right() {
		return right;
	}
	
	public int left() {
		return left;
	}
}