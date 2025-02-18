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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class CardPanel extends JPanel {
	GameButtonListener gameButtonListener = new GameButtonListener();
	GridLayout cardLayout;
	Color backColor = new Color(0x008000);
	JButton cardReturnButton;
	JButton cardResetButton;
	JLabel gameFinish;
	Card card = new Card();
	Font cardFont = new Font("consolas", Font.BOLD, 1);
	Font buttonFont = new Font("メイリオ", Font.BOLD, 15);
	JButton[] cardButtons = new JButton[card.totalCardNumber];
	ImageIcon[] cardIcons = new ImageIcon[card.totalCardNumber];

	ImageIcon backSideIcon = new ImageIcon(getClass().getClassLoader().getResource("back.png"));
	ImageIcon disabledIcon = new ImageIcon(getClass().getClassLoader().getResource("feild.png"));
	int countTurn = 0;

	public CardPanel() {
		if (card.totalCardNumber == 10) {
//			backSideIcon = new ImageIcon(getClass().getClassLoader().getResource("back.png"));
			cardLayout = new GridLayout(2, 5, 30, 30);
			for (int i = 0; i < card.totalCardNumber; i++) {
				String fileName = String.format("torannpu-illus%d.png", i + 1);
				cardIcons[i] = new ImageIcon(getClass().getClassLoader().getResource(fileName));
			}
		} else if (card.totalCardNumber == 52) {
			backSideIcon = new ImageIcon(getClass().getClassLoader().getResource("back52.png"));
			cardLayout = new GridLayout(5, 11, 5, 10);
			for (int i = 0; i < card.totalCardNumber; i++) {
				String fileName = String.format("torannpu-illust%d.png", i + 1);
				cardIcons[i] = new ImageIcon(getClass().getClassLoader().getResource(fileName));
			}
		}
		for (int i = 0; i < card.totalCardNumber; i++) {
			String number = "" + i;
			cardButtons[i] = new JButton(number);
		}

		cardReturnButton = new JButton();
		cardReturnButton.setText("カードを裏に戻す");
		cardReturnButton.setBounds(50, 0, 180, 30);
		cardReturnButton.setFont(buttonFont);
		cardReturnButton.setFocusable(false);
		cardReturnButton.addActionListener(gameButtonListener);

		cardResetButton = new JButton();
		cardResetButton.setText("リセット");
		cardResetButton.setBounds(250, 0, 180, 30);
		cardResetButton.setFont(buttonFont);
		cardResetButton.setFocusable(false);
		cardResetButton.setFocusable(false);
		cardResetButton.setEnabled(false);
		cardResetButton.addActionListener(gameButtonListener);

		gameFinish = new JLabel();
		gameFinish.setText("");
		gameFinish.setFont(buttonFont);
		gameFinish.setHorizontalTextPosition(JLabel.CENTER);
		gameFinish.setVerticalTextPosition(SwingConstants.BOTTOM);
		gameFinish.setFocusable(false);
		gameFinish.setBounds(450, 0, 300, 30); //位置とサイズを設定

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
			cardButton.setIcon(backSideIcon);
		}
	}

	public class GameButtonListener implements ActionListener {
		Timer timer;
		int sec;

		GameButtonListener() {
			sec = 0;
			timer = new Timer(300, this);
			timer.setActionCommand("カード回収");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.matches("^[0-9].*") && card.countCardsWithSameStatus(2)!=2) {
				openCard(command);
			} else if (command.equals("カードを裏に戻す")) {
				if (card.countCardsWithSameStatus(2) == 2) {
					turnOffCard();
					countTurn++;
				}
			} else if (command.equals("リセット")) {
				if (card.countCardsWithSameStatus(0) == card.totalCardNumber) {
					Collections.shuffle(card.cardList);
					card.initCardStatus();
					card.printCardList();
					for (int i = 0; i < cardButtons.length; i++) {
						cardButtons[i].setIcon(backSideIcon);
						cardButtons[i].setEnabled(true);
					}
					cardResetButton.setEnabled(false);
				}
			} else if (command.equals("カード回収")) {
				if (sec > 0) {
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
					if (card.getIndexesOfStatus(1).size() == 0) {
						String message = String.format("ゲーム終了　かかったターン数 : %d回", countTurn);
						gameFinish.setText(message);
						cardResetButton.setEnabled(true);
					}
				} else {
					sec++;
				}

			}
		}

		public void turnOffCard() {
			int placeNumA = card.getIndexesOfStatus(2).get(0);
			int placeNumB = card.getIndexesOfStatus(2).get(1);
			cardButtons[placeNumA].setIcon(backSideIcon);
			cardButtons[placeNumB].setIcon(backSideIcon);
			card.switchStatusTurnedOver(placeNumA);
			card.switchStatusTurnedOver(placeNumB);
		}

		public void openCard(String command) {
			int placeNum = Integer.valueOf(command);
			int iconsIndex = card.getCardNumber(placeNum) - 1;
			cardButtons[placeNum].setIcon(cardIcons[iconsIndex]);
			card.switchStatusFacedUp(placeNum);
			card.printCardStatus();
			if (card.countCardsWithSameStatus(2) == 2
					&& card.twoCardsIsSameNumber()) {
				cardReturnButton.setEnabled(false);
				cardResetButton.setEnabled(false);
				countTurn++;
				timer.start();
			}

		}
	}

	public void setDisableCard(int placeNum) {
		cardButtons[placeNum].setEnabled(false);
		cardButtons[placeNum].setDisabledIcon(disabledIcon);
		card.switchStatusCollected(placeNum);
	}

}
