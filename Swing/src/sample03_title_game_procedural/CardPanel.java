package sample03_title_game_procedural;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	GameButtonListener gameButtonListener = new GameButtonListener();
	GridLayout cardLayout = new GridLayout(2, 5, 30, 30);
	Color backColor = new Color(0x008000);
	JButton cardReturnButton;
	JButton cardResetButton;
	Card card = new Card();
	Font cardFont = new Font("consolas", Font.BOLD, 1);
	Font buttonFont = new Font("メイリオ", Font.BOLD, 15);
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
		cardReturnButton = new JButton();
		cardReturnButton.setText("カードを裏に戻す");
		cardReturnButton.setBounds(50, 0, 180, 30);
		cardReturnButton.setFont(buttonFont);
		cardReturnButton.setFocusable(false);
		cardReturnButton.addActionListener(gameButtonListener);

		cardResetButton = new JButton();
		cardResetButton.setText("リセット");
		cardResetButton.setBounds(400, 0, 180, 30);
		cardResetButton.setFont(buttonFont);
		cardResetButton.setFocusable(false);
		cardResetButton.addActionListener(gameButtonListener);

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
			if (command.matches("^[0-9]")) {
				openCard(command);
			} else if (command.equals("カードを裏に戻す")) {
				if (card.countCardsWithSameStatus(2) == 2) {
					placeNum1 = card.getIndexesOfStatus(2).get(0);
					placeNum2 = card.getIndexesOfStatus(2).get(1);
					cardButtons[placeNum1].setIcon(backIcon);
					cardButtons[placeNum2].setIcon(backIcon);
					card.switchStatusTurnedOver(placeNum1);
					card.switchStatusTurnedOver(placeNum2);
				}
			}else if(command.equals("リセット")){
				if(card.countCardsWithSameStatus(0) == 10) {
					Collections.shuffle(card.cardList);
					card.initCardStatus();
					card.printCardList();
					for (int i=0; i<cardButtons.length; i++) {
						cardButtons[i].setIcon(backIcon);
						cardButtons[i].setEnabled(true);
					}
				}
			}
		}

	}

	public void openCard(String command) {
		int placeNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
		if (card.countCardsWithSameStatus(2) == 0) {
			cardButtons[placeNum].setIcon(icons[card.getCardNumber(placeNum) - 1]);
			card.switchStatusFacedUp(placeNum);
			placeNum1 = placeNum;
			card.printCardStatus();
		} else if (card.countCardsWithSameStatus(2) == 1) {
			cardButtons[placeNum].setIcon(icons[card.getCardNumber(placeNum) - 1]);
			card.switchStatusFacedUp(placeNum);
			placeNum2 = placeNum;
			card.printCardStatus();
			if (card.twoCardsIsSameNumber()) {
				setDisableCard(placeNum1);
				setDisableCard(placeNum2);
				for (int swich : card.cardStatus) {
					System.out.printf("%d ", swich);
				}
				System.out.printf("%n");
			} 

		}

	}

	public void setDisableCard(int placeNum) {
		cardButtons[placeNum].setEnabled(false);
		cardButtons[placeNum].setDisabledIcon(disabledIcon);
		card.switchStatusCollected(placeNum);
	}

}
