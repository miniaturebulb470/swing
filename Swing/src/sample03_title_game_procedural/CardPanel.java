package sample03_title_game_procedural;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	GameButtonListener gameButtonListener = new GameButtonListener();
	GridLayout cardLayout = new GridLayout(2, 5, 20, 20);

	JButton[] cardButtons = {
			new JButton("Card1"),
			new JButton("Card2"),
			new JButton("Card3"),
			new JButton("Card4"),
			new JButton("Card5"),
			new JButton("Card6"),
			new JButton("Card7"),
			new JButton("Card8"),
			new JButton("Card9"),
			new JButton("Card10")		
	};
	int[] cardSwitch = {1,1,1,1,1,1,1,1,1,1};
	int cardNum1;
	int cardNum2;
	int placeNum1;
	int placeNum2;
	String buttonText1;
	String buttonText2;
//	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("torannpu-illust02.png"));

	ArrayList<Integer> cardList = new ArrayList<Integer>();
	final int TOTAL_CARD = 10;
	int returnCounter = 0;

	
	public CardPanel() {
		makeCardList(TOTAL_CARD);

		this.setLayout(cardLayout);

		for(JButton card : cardButtons) {
			this.add(card);
			card.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
			card.addActionListener(gameButtonListener);
		}
//		cardButtons[0].setIcon(icon);
//	    int iconHeight = icon.getIconHeight();
//	    int iconWidth = icon.getIconWidth();
//	    cardButtons[1].setText(iconWidth + "×" + iconHeight);

	
	}

	public class GameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.printf("command : %s%n", command);
			openCard(command);
			//			if (command.equals("Card1")) {
//				int placeNum = Integer.valueOf(command.replaceAll("[^0-9]",""))-1;
//				String cardNum = ""+cardList.get(placeNum);
//				cardButtons[placeNum].setText(cardNum);
//				cardButtons[placeNum].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
//				if (returnCounter == 0) {
//					cardNum1 = cardList.get(placeNum);
//					placeNum1 = placeNum;
//					returnCounter = 1;
//				} else if (returnCounter == 1) {
//					cardNum2 = cardList.get(placeNum);
//					placeNum2 = placeNum;
//					returnCounter = 0;
//					if(cardNum1 == cardNum2) {
//						cardButtons[placeNum1].setText("クリア");
//						cardButtons[placeNum2].setText("クリア");
//						cardSwitch[placeNum1] = 0;
//						cardSwitch[placeNum2] = 0;
//					}else {
//						String buttonNum1 = ""+placeNum1; 
//						String buttonNum2 = ""+placeNum2; 
//						try {
//							Thread.sleep(5000);
//						}catch (InterruptedException event) {
//						}
//						cardButtons[placeNum1].setText(buttonNum1);
//						cardButtons[placeNum2].setText(buttonNum2);
//					}
//					
//				}
//			}
		}

	}
	public void openCard(String command) {
		int placeNum = Integer.valueOf(command.replaceAll("[^0-9]",""))-1;
		System.out.printf("placeNum : %d%n",placeNum);
		String cardNum = ""+cardList.get(placeNum);
		if (returnCounter == 0) {
			cardButtons[placeNum].setText(cardNum);
			cardButtons[placeNum].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
			cardNum1 = cardList.get(placeNum);
			placeNum1 = placeNum;
			returnCounter = 1;
			System.out.printf("%d%n",returnCounter);
		} else if (returnCounter == 1) {
			cardButtons[placeNum].setText(cardNum);
			cardButtons[placeNum].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
			cardNum2 = cardList.get(placeNum);
			placeNum2 = placeNum;
			returnCounter = 2;
			System.out.printf("%d%n",returnCounter);
			if(cardNum1 == cardNum2) {
				cardButtons[placeNum1].setText("クリア");
				cardButtons[placeNum2].setText("クリア");
				cardButtons[placeNum1].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
				cardButtons[placeNum2].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
				cardButtons[placeNum1].setEnabled(false);
				cardButtons[placeNum2].setEnabled(false);
				cardSwitch[placeNum1] = 0;
				cardSwitch[placeNum2] = 0;
				returnCounter = 0;
				for(int swich : cardSwitch) {
					System.out.printf("%d ",swich);
				}
				System.out.printf("%n");
				System.out.printf("%d%n",returnCounter);
			}else {
				buttonText1 = "Card"+(placeNum1+1); 
				buttonText2 = "Card"+(placeNum2+1); 

			}
			
		}
		
	}
	public void makeCardList(int cardNum) {
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= cardNum / 2; j++) {
				cardList.add(Integer.valueOf(j));
			}
		}
		Collections.shuffle(cardList);
		for (int i = 0; i < cardNum; i++) {
				System.out.printf("%d ",cardList.get(i));
				
		}
		System.out.printf("%n");
	}
}
