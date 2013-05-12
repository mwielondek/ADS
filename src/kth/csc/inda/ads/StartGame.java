package kth.csc.inda.ads;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import kth.csc.inda.ads.snake.Direction;
import kth.csc.inda.ads.snake.NSnake;

public class StartGame {
	private static View view;
	private static ArrayList<NSnake> players;
	private static ArrayList<JLabel> labels;
	private static NField field;
	private static JLabel info;
	private static JLabel footer;
	private static DefaultListModel model;
	private static CardLayout cl;
	private static JButton playButton;
	private static JPanel mainPanel;
	private static final int height = 90;
	private static final int width = 90;
	private static final int snakeStartLength = 5;
	private static int nrOfPlayers;
	private static int numPowerUps; // set number of powerups
	private static final int MAX_PLAYERS = 4;
	private static final Color[] PLAYER_COLORS = { Color.MAGENTA, Color.RED,
			Color.CYAN, Color.ORANGE };
	private static final String[][] PLAYER_CTRLS = { {"←","→"}, {"A","S"}, {"I","O"}, {"V","B"}};

	public static void main(String[] args) throws InterruptedException {

		view = new View(height, width);
		Container container = view.getContentPane();
		JPanel p1 = new JPanel(new FlowLayout());
		JPanel p2 = new JPanel(new FlowLayout());
		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.BLACK);
		container.add(p1, BorderLayout.NORTH);
		container.add(p2, BorderLayout.SOUTH);
		info = new JLabel(" ");
		info.setForeground(Color.WHITE);
		p1.add(info);
		footer = new JLabel("© 2013 by JJM");
		footer.setForeground(Color.WHITE);
		p2.add(footer);

		// create main menu
		JPanel startView = new JPanel(new BorderLayout());

		// add logo
		ImageIcon logoPic = new ImageIcon("ads.png");
		JLabel logo = new JLabel(logoPic);
		startView.add(logo, BorderLayout.NORTH);

		// add player panel
		JPanel playerPanel = new JPanel(new BorderLayout());
		playerPanel.setBorder(new TitledBorder("Players"));
		model = new DefaultListModel();
		PlayerBar newPlayer = new PlayerBar() {
			public void act() {
				addPlayer();
				togglePlayButton();
			}
		};
		newPlayer.add(new JLabel(new ImageIcon("plus.png")));
		JLabel l = new JLabel("Click to add player");
		l.setBorder(new EmptyBorder(10, 0, 10, 16));
		newPlayer.add(l);
		model.addElement(newPlayer);

		// create a custom cell renderer to properly display the playerbars
		final class LCR implements ListCellRenderer {

			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				return (PlayerBar) value;
			}
		}
		final JList playerList = new JList(model);
		playerList.setCellRenderer(new LCR());
		playerList.setBackground(playerPanel.getBackground());
		playerPanel.add(playerList, BorderLayout.CENTER);
		addPlayer();

		// add playerpanel to startview
		startView.add(playerPanel, BorderLayout.CENTER);

		// set main to display startview
		mainPanel = view.getMainPanel();
		mainPanel.add(startView, "Start");
		cl = (CardLayout) mainPanel.getLayout();
		cl.show(mainPanel, "Start");
		// view.setSize(logoPic.getIconWidth(), 300);

		// add listeners to playerbars
		playerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PlayerBar pb = (PlayerBar) playerList.getSelectedValue();
				pb.act();
			}
		});

		// create buttons
		JPanel buttons = createButtons();

		// place buttons inside a flow for added space
		JPanel flow = new JPanel();
		flow.add(buttons);
		startView.add(flow, BorderLayout.EAST);
		view.pack();
	}

	/**
	 * @return The panel with the created buttons.
	 */
	private static JPanel createButtons() {
		// Panel to add the buttons to.
		JPanel buttons = new JPanel(new BorderLayout());

		// Create buttons.
		playButton = playButton();
		JButton aboutButton = aboutButton();
		JButton quitButton = quitButton();
		JButton howToButton = howToButton();

		// Add the bigger play button.
		playButton.setPreferredSize(new Dimension(80, 40));
		buttons.add(playButton, BorderLayout.NORTH);
		buttons.add(Box.createRigidArea(new Dimension(0, 10)));

		// Add the rest of the buttons.
		JPanel buttonsSouth = new JPanel(new GridLayout(0, 1));
		buttonsSouth.add(aboutButton);
		buttonsSouth.add(howToButton);
		buttonsSouth.add(quitButton);
		buttons.add(buttonsSouth, BorderLayout.SOUTH);

		return buttons;
	}

	/**
	 * @return The howTo button.
	 */
	private static JButton howToButton() {
		JButton howTo = new JButton("How to play");
		howTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				sb.append("<HTML><FONT SIZE=12 COLOR=RED> How to play</FONT>");
				// Player selection.
				sb.append("<BR><FONT SIZE=4>Player selection</FONT>");
				sb.append("<BR> Add/Remove players by clicking on the bars.");
				sb.append("<BR> 1-4 players are allowed.");
				sb.append("<BR>");
				// Winning conditions.
				sb.append("<BR><FONT SIZE=4> Winning conditions</FONT>");
				sb.append("<BR> Kill the other snakes, either by making them<BR> crash into another snake or a wall");
				sb.append("<BR>");
				// Powerups info.
				sb.append("<BR><FONT SIZE=4> Powerups</FONT>");
				sb.append("<BR><FONT COLOR=BLACK>Black </FONT> dots are bombs, kills you.");
				sb.append("<BR><FONT COLOR=GREEN> Green </FONT> dots are food, makes you longer.");
				sb.append("<BR><FONT COLOR=BLUE> Does </FONT> something?");
				sb.append("</HTML>");
				ImageIcon icon = new ImageIcon("info.png");
				JOptionPane.showMessageDialog(view, sb.toString(),
						"How to play", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		return howTo;
	}

	/**
	 * @return The play button.
	 */
	private static JButton playButton() {
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nrOfPlayers = model.getSize() - 1;
				Runnable startGame = new Runnable() {
					public void run() {
						newGame();
					}
				};
				Thread t = new Thread(startGame);
				t.start();
			}
		});
		return play;
	}

	/**
	 * @return The about button.
	 */
	private static JButton aboutButton() {
		JButton about = new JButton("About");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pressed about");
				StringBuilder sb = new StringBuilder();
				sb.append("<HTML><FONT SIZE=12 COLOR=RED> Achtung die Schlange </FONT>");
				sb.append("<BR><FONT SIZE=6>A game by: </FONT>");
				sb.append("<BR>Janne Selkälä");
				sb.append("<BR> Jesper Simonsson");
				sb.append("<BR>Milosz Wielondek</HTML>");
				ImageIcon icon = new ImageIcon("snake.png");
				JOptionPane.showMessageDialog(view, sb.toString(), "About",
						JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		return about;
	}

	/**
	 * @return The quit button.
	 */
	private static JButton quitButton() {
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return quit;
	}

	/**
	 * Toogles the play button.
	 */
	private static void togglePlayButton() {
		if (model.getSize() < 2) {
			playButton.setEnabled(false);
		} else {
			playButton.setEnabled(true);
		}
	}

	/**
	 * Adds a new player to the list.
	 * 
	 * @param pl
	 *            player list to be appended.
	 */
	private static void addPlayer() {
		int playerID = model.getSize();
		if (playerID > MAX_PLAYERS)
			return;
		JToolBar newPlayer = new PlayerBar() {
			// what to do when bar is clicked
			public void act() {
				// default action is remove last player
				model.removeElementAt(model.getSize() - 2);
				togglePlayButton();
			}
		};
		newPlayer.setLayout(new BorderLayout());
		JLabel l = new JLabel("Player " + Integer.toString(playerID));
		l.setForeground(PLAYER_COLORS[playerID - 1]);
		newPlayer.add(l, BorderLayout.WEST);
		l = new JLabel("(Click to remove)");
		l.setBorder(new EmptyBorder(16, 16, 16, 16));
		l.setForeground(Color.GRAY);
		newPlayer.add(l, BorderLayout.CENTER);
		String[] ctrls = PLAYER_CTRLS[playerID - 1];
		l = new JLabel("Left: " + ctrls[0] + ", Right: " + ctrls[1]);
		newPlayer.add(l, BorderLayout.EAST);
		int idx = model.getSize() - 1;
		model.add(idx, newPlayer);
	}

	private static class PlayerBar extends JToolBar {

		PlayerBar() {
		};

		public void act() {
		};
	}

	/**
	 * Resets everything that needs to be reset.
	 */
	private static void reset() {
		field.clear();
	}

	/**
	 * Places all the starting objects on the field.
	 */
	private static void placeStartObjects() {
		// Place a set number of food objects on the field
		Random rand = new Random();
		for (int i = 0; i < nrOfPlayers + 2; i++) {
			Location temp = new Location(rand.nextInt(width),
					rand.nextInt(height));
			if (field.getObjectAt(temp) == null) {
				field.place(new PowUpFood(), temp);
			} else {
				i--;
			}
		}

		// Add a bomb in the middle of the field.
		field.place(new Bomb(), new Location(width / 2, height / 2));
	}

	/**
	 * Starts a new game with a specified amount of players.
	 * 
	 * @param nrOfPlayers
	 * 
	 * @throws InterruptedException
	 */
	private final static void newGame() {
		// funkar endast om nedan blir false - ngt fel!
		System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
		// add the fieldView and create field
		field = new NField(height, width);
		reset();
		cl.show(mainPanel, "Field");
		view.pack();

		// Create all the players.
		players = createPlayers(field, nrOfPlayers);
		// Add the players to the view.
		view.SetPlayers(players);

		// Create the starting powerups and the like.
		placeStartObjects();
		
		footer.setText(" ");

		// Start a 3 second countdown before the game starts.
		for (int j = 3; j > 0; j--) {
			info.setText("New game starts in: " + j);
			gameLoop(); // Slowly draw the snake up.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		info.setText("Go!");

		for (;;) {
			if (gameLoop()) {
				break;
			}
		}
		cl.show(mainPanel, "Start");
		info.setText(" ");
		footer.setText("© 2013 by JJM");
		footer.setForeground(Color.WHITE);
		view.pack();
	}

	/**
	 * Call it to move the snakes once. Also handles all the logic.
	 * 
	 * @throws InterruptedException
	 */
	private static boolean gameLoop() {
		for (int i = 0; i < players.size(); i++) {
			NSnake p = players.get(i);
			Location newLoc = p.getNextMove();
			ActAndDraw actor = field.getObjectAt(newLoc);
			if (actor != null) {// Check if the snake will be moved to a
								// occupied location
				if (actor instanceof Powerup) {
					// Try to place a new power up on the field. If one can't be
					// placed in 5 tries give up.
					Random rand = new Random();
					int n = 0;
					boolean placed = false;
					while (!placed && n < 5) {
						Location temp = new Location(rand.nextInt(width),
								rand.nextInt(height));
						if (field.getObjectAt(temp) != null) {
							n++;
						} else {
							field.place(new PowUpFood(), temp);
							placed = true;
						}
					}
				}
				if (actor.act(p)) {
					p.move(newLoc);
				}
			} else {
				p.move(newLoc);
			}
			System.out.println(p);
			if (!p.isAlive()) {
				players.remove(i);
				footer.setText(p.toString());
				footer.setForeground(p.getColor());
			}
		}
		// Draw everything.
		view.showStatus(field);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		if (players.isEmpty()) {
			// Single player, lost.
			ImageIcon icon = new ImageIcon("finish.png");
			JOptionPane.showMessageDialog(null, "You lost!", "Game over",
					JOptionPane.INFORMATION_MESSAGE, icon);
			return true;
		} else if (players.size() == 1 && nrOfPlayers != 1) {
			// Do something as someone has won.
			NSnake winner = players.get(0);
			String rgb = Integer.toHexString(winner.getColor().getRGB());
			rgb = rgb.substring(2, rgb.length());
			ImageIcon icon = new ImageIcon("gameover.png");
			JOptionPane.showMessageDialog(view, "<HTML><FONT COLOR=" + rgb + "> " + winner.getName()
					+ "</FONT> has won! </HTML>", "Game over", JOptionPane.INFORMATION_MESSAGE, icon);
			return true;
		}
		return false;
	}

	/**
	 * Creates the players to be used.
	 * 
	 * @param field
	 *            The field the snakes will be placed on.
	 * @param nrOfPlayers
	 *            The number of players. (1-4).
	 * @return The created players.
	 */
	private static ArrayList<NSnake> createPlayers(NField field, int nrOfPlayers) {
		ArrayList<NSnake> players = new ArrayList<NSnake>();
		switch (nrOfPlayers) {
		case 4:
			players.add(new NSnake("Player 4", Color.ORANGE, field, new Location(
					height - 10, 10), Direction.RIGHT, new TwoControls(
					KeyEvent.VK_V, KeyEvent.VK_B), snakeStartLength));
		case 3:
			players.add(new NSnake("Player 3", Color.CYAN, field, new Location(10,
					width - 10), Direction.LEFT, new TwoControls(KeyEvent.VK_I,
					KeyEvent.VK_O),
					snakeStartLength));
		case 2:
			players.add(new NSnake("Player 2", Color.RED, field, new Location(
					height - 10, width - 10), Direction.LEFT,
					new TwoControls(KeyEvent.VK_A, KeyEvent.VK_S), snakeStartLength));
		case 1:
			players.add(new NSnake("Player 1", Color.MAGENTA, field,
					new Location(10, 10), Direction.RIGHT, new TwoControls(
							KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT), snakeStartLength));
			break;
		default:
			throw new IllegalArgumentException(nrOfPlayers
					+ " is not in the intervall 1-4");
		}
		return players;
	}
}
