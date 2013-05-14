package kth.csc.inda.ads;

import java.awt.Color;
import java.util.ArrayList;

import kth.csc.inda.ads.snake.NSnake;
/**
 * Inverses the controls for all players except for the one who stepped on the powerup.
 * OBS: STAYS ON FOREVER.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-14
 */

public class PowUpInvControls extends PowUpGlobal implements Powerup{

	private int count;
	public PowUpInvControls(ArrayList<NSnake> players) {
		super(players, null);
		count = 0;
	}

	@Override
	public boolean act(NSnake snake) {
		for(NSnake p : super.getPlayers()) {
			if (p.getName() != snake.getName()) {
				// For all players except the one who used the powerup
				// inverse the controls.
				TwoControls c = p.getControls();
				int temp = c.left();
				c.setLeft(c.right());
				c.setRight(temp);
			}
		}
		return true;
	}
	
	@Override
	public Color getColor() {
		count++;
		if (count <= 5) {
			return Color.BLACK;
		} else if (count <= 9) {
			return Color.YELLOW;
		} else {
			count = 0;
			return Color.YELLOW;
		}
	}
}
