package sample03_title_game_procedural;

//test
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CardPanel extends JPanel {
	GameButtonListener gameButtonListener = new GameButtonListener();
	GridLayout cardLayout = new GridLayout(2, 5, 30, 30);
	Color backColor = new Color(0x008000);
	JButton cardReturnButton;
	JButton cardResetButton;
	Card card = new Card();
	Font cardFont = new Font("consolas", Font.BOLD, 1);
	Font buttonFont = new Font("メイリオ", Font.BOLD, 15);
	JButton[] cardButtons = new JButton[card.totalCardNumber];

	ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("back.png"));
	ImageIcon disabledIcon = new ImageIcon(getClass().getClassLoader().getResource("feild.png"));

	ImageIcon[] icons = new ImageIcon[card.totalCardNumber];

	public CardPanel() {
		for (int i = 0; i < card.totalCardNumber; i++) {
			String number = "" + i;
			cardButtons[i] = new JButton(number);
		}

		for (int i = 0; i < card.totalCardNumber; i++) {
			String fileName = String.format("torannpu-illust%d.png", i + 1);
			icons[i] = new ImageIcon(getClass().getClassLoader().getResource(fileName));
		}

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
		Timer timer;
		int sec;

		GameButtonListener() {
			sec = 0;
			timer = new Timer(1000, this);
			timer.setActionCommand("timer");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.matches("^[0-9]")) {
				openCard(command);
			} else if (command.equals("カードを裏に戻す")) {
				if (card.countCardsWithSameStatus(2) == 2) {
					turnOffCard();
				}
			} else if (command.equals("リセット")) {
				if (card.countCardsWithSameStatus(0) == card.totalCardNumber) {
					Collections.shuffle(card.cardList);
					card.initCardStatus();
					card.printCardList();
					for (int i = 0; i < cardButtons.length; i++) {
						cardButtons[i].setIcon(backIcon);
						cardButtons[i].setEnabled(true);
					}
				}
			}else if(command.equals("timer")) {
				if(sec > 0) {
					setDisableCard(card.getIndexesOfStatus(2).get(1));
					setDisableCard(card.getIndexesOfStatus(2).get(0));
					for (int swich : card.cardStatus) {
						System.out.printf("%d ", swich);
					}
					System.out.printf("%n");
					cardReturnButton.setEnabled(true);
					cardResetButton.setEnabled(true);
					timer.stop();
					sec = 0;
				}else {
					sec++;
				}
				
			}
		}

		public void turnOffCard() {
			int placeNumA = card.getIndexesOfStatus(2).get(0);
			int placeNumB = card.getIndexesOfStatus(2).get(1);
			cardButtons[placeNumA].setIcon(backIcon);
			cardButtons[placeNumB].setIcon(backIcon);
			card.switchStatusTurnedOver(placeNumA);
			card.switchStatusTurnedOver(placeNumB);
		}

		public void openCard(String command) {
			int placeNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
			if (card.countCardsWithSameStatus(2) == 0) {
				cardButtons[placeNum].setIcon(icons[card.getCardNumber(placeNum) - 1]);
				card.switchStatusFacedUp(placeNum);
				card.printCardStatus();
			} else if (card.countCardsWithSameStatus(2) == 1) {
				cardButtons[placeNum].setIcon(icons[card.getCardNumber(placeNum) - 1]);
				card.switchStatusFacedUp(placeNum);
				card.printCardStatus();
				if (card.twoCardsIsSameNumber()) {
					cardReturnButton.setEnabled(false);
					cardResetButton.setEnabled(false);
					timer.start();
				}

			}

		}
	}

	public void setDisableCard(int placeNum) {
		cardButtons[placeNum].setEnabled(false);
		cardButtons[placeNum].setDisabledIcon(disabledIcon);
		card.switchStatusCollected(placeNum);
	}

}
