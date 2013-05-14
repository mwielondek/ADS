package kth.csc.inda.ads;

import java.awt.Color;
import java.util.ArrayList;

import kth.csc.inda.ads.snake.NSnake;

/**
 * Changes the color of all of the snakes.
 * 
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-14
 */
public class PowUpSwitchColors extends PowUpGlobal implements Powerup {
	private int count;

	public PowUpSwitchColors(ArrayList<NSnake> players) {
		super(players, null);
		count = 0;
	}

	@Override
	public boolean act(NSnake snake) {
		ArrayList<NSnake> players = super.getPlayers();
		if (players.size() != 1) {
			Color temp = players.get(0).getColor();
			for (int i = 1; i < players.size(); i++) {
				players.get(i - 1).setColor(players.get(i).getColor());
			}
			players.get(players.size() - 1).setColor(temp);
		} else {
			// Do nothing for single player.
		}
		return true; // Able to move on the square.
	}

	@Override
	public Color getColor() {
		count++;
		if (count <= 5) {
			return Color.MAGENTA;
		} else if (count <= 10) {
			return Color.RED;
		} else if (count <= 15) {
			return Color.CYAN;
		} else if (count <= 19) {
			return Color.ORANGE;
		} else {
			count = 0;
			return Color.ORANGE;
		}
	}
}
