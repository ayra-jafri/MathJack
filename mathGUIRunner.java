/**
 * This is a class that plays the GUI version of the MathJack game.
 * See accompanying documents for a description of how MathJack is played.
 */
public class mathGUIRunner {
	/**
	 * Plays the GUI version of MathJack.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Board board = new mathBoard();
		CardGameGUI gui = new CardGameGUI(board);
		gui.displayGame();
	}
}
