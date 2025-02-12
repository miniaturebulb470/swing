package sample03_title_game_procedural;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	GameButtonListener gameButtonListener = new GameButtonListener();
	GridLayout cardLayout = new GridLayout(2, 5, 20, 20);
	Color backColor = new Color(0x008000);

	Card card = new Card();
	Font cardFont = new Font("consolas", Font.BOLD, 1);
	JButton[] cardButtons = {
			new JButton("0"),
			new JButton("1"),
			new JButton("2"),
			new JButton("3"),
			new JButton("4"),
			new JButton("5"),
			new JButton("6"),
			new JButton("7"),
			new JButton("8"),
			new JButton("9")
	};
	int placeNum1;
	int placeNum2;
	String buttonText1;
	String buttonText2;
	ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("back.png"));
	ImageIcon disabledIcon = new ImageIcon(getClass().getClassLoader().getResource("feild.png"));

	ImageIcon[] icons = {
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust01.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust02.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust03.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust04.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust05.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust40.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust41.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust42.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust43.png")),
			new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust44.png"))
	};


	public CardPanel() {

		this.setLayout(cardLayout);
		this.setBackground(backColor);

		for (JButton cardButton : cardButtons) {
			this.add(cardButton);
			cardButton.setFont(cardFont);
			cardButton.setHorizontalTextPosition(JButton.CENTER);
			cardButton.setVerticalTextPosition(JButton.BOTTOM);
			cardButton.addActionListener(gameButtonListener);
			cardButton.setForeground(backColor);
			cardButton.setBackground(backColor);
			cardButton.setBorderPainted(false);
			cardButton.setIcon(backIcon);
		}
	}

	public class GameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.printf("command : %s%n", command);
			openCard(command);
		}

	}

	public void openCard(String command) {
		int placeNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
		System.out.printf("placeNum : %d%n", placeNum);
		cardButtons[placeNum].setIcon(icons[card.getCardNumber(placeNum) - 1]);
		card.switchStatusFacedUp(placeNum);
		if (card.countCardsWithSameStatus(2) == 1) {
			setText(placeNum);
			placeNum1 = placeNum;
			card.printCardStatus();
			System.out.printf("ひっくり返した枚数 : %d%n", card.countCardsWithSameStatus(2));
		} else if (card.countCardsWithSameStatus(2) == 2) {
			setText(placeNum);
			placeNum2 = placeNum;
			card.printCardStatus();
			System.out.printf("ひっくり返した枚数 : %d%n", card.countCardsWithSameStatus(2));
			if (card.twoCardsIsSameNumber()) {
				setDisableCard(placeNum1);
				setDisableCard(placeNum2);
				for (int swich : card.cardStatus) {
					System.out.printf("%d ", swich);
				}
				System.out.printf("%n");
			} else {
				buttonText1 = "" + (placeNum1);
				buttonText2 = "" + (placeNum2);

			}

		}

	}

	public void setDisableCard(int placeNum) {
		cardButtons[placeNum].setText("クリア");
		cardButtons[placeNum].setFont(cardFont);
		cardButtons[placeNum].setEnabled(false);
		cardButtons[placeNum].setDisabledIcon(disabledIcon);
		card.switchStatusCollected(placeNum);
	}

	public void setText(int placeNum) {
		cardButtons[placeNum].setText("a");
		cardButtons[placeNum].setFont(cardFont);
		cardButtons[placeNum].setHorizontalTextPosition(JButton.CENTER);
		cardButtons[placeNum].setVerticalTextPosition(JButton.BOTTOM);
	}
}
