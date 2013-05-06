package kth.csc.inda.ads;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartGame {
	private static View view;
	private static ArrayList<NSnake> players;
	private static ArrayList<JLabel> labels;
	private static NField field;
	private static JLabel info;
	private static final int height = 100;
	private static final int width = 100;
	private static final int snakeStartLength = 5;
	private static int nrOfPlayers;

	public static void main(String[] args) throws InterruptedException {

		AskPlay("Do you want to play \"Achtung die Schlange\"");
		nrOfPlayers = AskNumPlayers();

		field = new NField(height, width);
		view = new View(height, width);
		info = new JLabel("Test");
		info.setForeground(Color.WHITE);
		JPanel p = new JPanel(new FlowLayout());
		p.setBackground(Color.BLACK);
		p.add(info);
		view.getContentPane().add(p, BorderLayout.NORTH);

		for (;;) {
			newGame();

			// Ask questions to play another round.
			AskPlay("Play again?");
			nrOfPlayers = AskNumPlayers();
		}
	}

	private static void AskPlay(String question) {
		String[] options = { "Yes", "No/Quit" };
		int answer = JOptionPane.showOptionDialog(null, question, "Choose...",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, "dummy");
		if (answer != 0) {
			System.exit(0);
		}
	}

	/**
	 * A JOptionPane asking to select the number of players.
	 */
	private static int AskNumPlayers() {
		String[] options = { "1 player", "2 players", "3 players", "4 players" };
		int answer = JOptionPane.showOptionDialog(null, "How many players?",
				"Choose...", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, "dummy");
		return answer + 1;
	}

	/**
	 * Resets everything that needs to be reset.
	 */
	private static void reset() {
		field.clear();
	}

	/**
	 * Starts a new game with a specified amount of players.
	 * 
	 * @param nrOfPlayers
	 * 
	 * @throws InterruptedException
	 */
	private static void newGame() throws InterruptedException {
		// Reset everything.
		reset();

		// Create all the players.
		players = createPlayers(field, nrOfPlayers);
		// Add a bomb in the middle of the field.
		field.place(new Bomb(), new Location(width / 2, height / 2));
		// Add the players to the view.
		view.SetPlayers(players);

		// Add enough labels for the specified amount of players.
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.BLACK);
		labels = new ArrayList<JLabel>();
		for (int i = 0; i < players.size(); i++) {
			JLabel l = new JLabel("player: " + (i + 1));
			l.setForeground(players.get(i).getColor());
			panel.add(l);
			labels.add(l);
		}
		Container container = view.getContentPane();
		container.add(panel, BorderLayout.SOUTH);
		view.pack(); // To make it look good.
		
		// Start a 5 second countdown before the game starts.
		for (int j = 5; j > 0; j--) {
			info.setText("New game starts in: " + j);
			gameLoop(); // Slowly draw the snake up.
			Thread.sleep(1000);
		}
		info.setText("Go!");
		
		for (;;) {
			if(gameLoop()) {
				break;
			}
		}
	}
	
	/**
	 * Call it to move the snakes once. Also handles all the logic.
	 * @throws InterruptedException 
	 */
	private static boolean gameLoop() throws InterruptedException {
		for (int i = 0; i < players.size(); i++) {
			NSnake p = players.get(i);
			p.move();

			System.out.println(p);
			labels.get(i).setText(p.toString());
			if (!p.isAlive()) {
				players.remove(i);
				labels.remove(i);
			}
		}
		// Draw everything.
		view.showStatus(field);

		Thread.sleep(100);
		if (players.isEmpty()) {
			// Single player, lost.
			JOptionPane.showMessageDialog(null, "You lost!", ":(",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else if (players.size() == 1 && nrOfPlayers != 1) {
			// Do something as someone has won.
			JOptionPane.showMessageDialog(view, players.get(0).getName()
					+ " has won!", ":)",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}

	
	/**
	 * Creates the players to be used.
	 * @param field 	  The field the snakes will be placed on.
	 * @param nrOfPlayers The number of players. (1-4).
	 * @return 			  The created players.
	 */
	private static ArrayList<NSnake> createPlayers(NField field, int nrOfPlayers) {
		ArrayList<NSnake> players = new ArrayList<NSnake>();
		switch (nrOfPlayers) {
		case 4:
			players.add(new NSnake("YELLOW", Color.YELLOW, field, new Location(
					height - 10, 10), Direction.RIGHT, new Controls(KeyEvent.VK_V,
					KeyEvent.VK_SPACE, KeyEvent.VK_C, KeyEvent.VK_B), snakeStartLength));
		case 3:
			players.add(new NSnake("BLUE", Color.BLUE, field, new Location(10,
					width - 10), Direction.LEFT, new Controls(KeyEvent.VK_I,
					KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L), snakeStartLength));
		case 2:
			players.add(new NSnake("RED", Color.RED, field,
					new Location(height - 10, width -10), Direction.LEFT, new Controls(
							KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A,
							KeyEvent.VK_D), snakeStartLength));
		case 1:
			players.add(new NSnake("GREEN", Color.GREEN, field, new Location(
					10, 10), Direction.RIGHT, new Controls(KeyEvent.VK_UP,
					KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT), snakeStartLength));
			break;
		default:
			throw new IllegalArgumentException(nrOfPlayers
					+ " is not in the intervall 1-4");
		}
		return players;
	}
}
