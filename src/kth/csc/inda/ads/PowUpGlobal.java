package kth.csc.inda.ads;

import java.awt.Color;
import java.util.ArrayList;

import kth.csc.inda.ads.snake.NSnake;
/**
 * @author Janne Selkälä
 * @author Jesper Simonsson
 * @author Milosz Wielondek
 * @version 2013-05-14
 */


public class PowUpGlobal {
	private ArrayList<NSnake> players;
	private Color color;
	
	public PowUpGlobal(ArrayList<NSnake> players, Color color) {
		this.players = players;
		this.color = color;
	}
	
	/**
	 * @return The players.
	 */
	protected ArrayList<NSnake> getPlayers() {
		return players;
	}
	
	public Color getColor() {
		return color;
	}
}
