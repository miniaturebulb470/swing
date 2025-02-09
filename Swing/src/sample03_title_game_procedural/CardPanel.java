package sample03_title_game_procedural;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	GameButtonListener gameButtonListener = new GameButtonListener();
	GridLayout cardLayout = new GridLayout(2, 5, 20, 20);
	Font font = new Font("ＭＳ ゴシック", Font.BOLD, 10);
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
	int[] cardSwitch = {1,1,1,1,1,1,1,1,1,1};
	int cardNum1;
	int cardNum2;
	int placeNum1;
	int placeNum2;
	String buttonText1;
	String buttonText2;
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("back.png"));

	ArrayList<Integer> cardList = new ArrayList<Integer>();
	final int TOTAL_CARD = 10;
	int returnCounter = 0;

	
	public CardPanel() {
		makeCardList(TOTAL_CARD);

		this.setLayout(cardLayout);

		for(JButton card : cardButtons) {
			this.add(card);
			card.setFont(font);
			card.addActionListener(gameButtonListener);
//		    card.setForeground(Color.WHITE);
		    card.setBackground(Color.WHITE);
		    card.setBorderPainted(false);
		}
		cardButtons[0].setIcon(icon);
	}

	public class GameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.printf("command : %s%n", command);
			openCard(command);
//					}
//					
//				}
//			}
		}

	}
	public void openCard(String command) {
		int placeNum = Integer.valueOf(command.replaceAll("[^0-9]",""));
		System.out.printf("placeNum : %d%n",placeNum);
		String cardNum = ""+cardList.get(placeNum);
		if (returnCounter == 0) {
			cardButtons[placeNum].setText(cardNum);
			cardButtons[placeNum].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
			cardNum1 = cardList.get(placeNum);
			placeNum1 = placeNum;
			returnCounter = 1;
			System.out.printf("ひっくり返した枚数 : %d%n",returnCounter);
		} else if (returnCounter == 1) {
			cardButtons[placeNum].setText(cardNum);
			cardButtons[placeNum].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
			cardNum2 = cardList.get(placeNum);
			placeNum2 = placeNum;
			returnCounter = 2;
			System.out.printf("ひっくり返した枚数 : %d%n",returnCounter);
			if(cardNum1 == cardNum2) {
				cardButtons[placeNum1].setText("クリア");
				cardButtons[placeNum2].setText("クリア");
				cardButtons[placeNum1].setFont(font);
				cardButtons[placeNum2].setFont(font);
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
				buttonText1 = ""+(placeNum1); 
				buttonText2 = ""+(placeNum2); 

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
