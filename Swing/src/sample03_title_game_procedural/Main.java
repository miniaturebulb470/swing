package sample03_title_game_procedural;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main extends JFrame implements KeyListener {
	CardLayout layout = new CardLayout();
	JPanel titlePanel = new JPanel();
	JLabel start;
	JLabel exit;
	JLabel select = new JLabel();
	Color backColor = new Color(0x008000);
	
	BorderLayout gameLayout = new BorderLayout();
	JPanel gamePanel = new JPanel();
	JPanel menuPanel = new JPanel();
	CardPanel cardPanel = new CardPanel();
	Font cardFont = new Font("ＭＳ ゴシック", Font.BOLD, 10);

	JButton cardReturnButton;
	JButton cardResetButton;
	GameButtonListener titleButtonListener = new GameButtonListener();
	int checkMenu = 0;

	JMenuBar menubar = new JMenuBar();

	public static void main(String[] args) {
		new Main();
	}

	Main() {
		getContentPane().setBackground(Color.GRAY);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //右上の「×」でウィンドウを閉じる設定
		setLayout(layout);
		setPreferredSize(new Dimension(990, 625)); //サイズ設定
		pack(); //自動サイズ調整（これがないと変なサイズになる）
		

		JMenu menu1 = new JMenu("File");
		menubar.add(menu1);
		JMenuItem menuItem1_1 = new JMenuItem("タイトル画面");
		JMenuItem menuItem1_2 = new JMenuItem("EXIT");
		menu1.add(menuItem1_1);
		menu1.add(menuItem1_2);
		setJMenuBar(menubar);
		menuItem1_1.addActionListener(titleButtonListener);
		menuItem1_2.addActionListener(titleButtonListener);

		titlePanel.setLayout(null);
//		titlePanel.setBackground(Color.GRAY);
		titlePanel.setBackground(backColor);
		add(titlePanel, "タイトル画面");

		start = new JLabel();
		start.setText("START");
		start.setFont(new Font("MS 明朝", Font.BOLD, 40));
		//		start.setHorizontalAlignment(JLabel.CENTER);
		//		start.setVerticalTextPosition(JLabel.CENTER);
		start.setBounds(100, 100, 150, 45);

		exit = new JLabel();
		exit.setText("EXIT");
		exit.setFont(new Font("MS 明朝", Font.BOLD, 40));
		exit.setHorizontalTextPosition(JLabel.CENTER);
		exit.setVerticalTextPosition(SwingConstants.BOTTOM);
		exit.setBounds(100, 150, 110, 45); //位置とサイズを設定

		select.setBackground(Color.DARK_GRAY);
		select.setOpaque(true); //透明にするかどうかを切り替える
		select.setBounds(50, 107, 30, 30); //位置とサイズを設定

		gamePanel.setLayout(null);
		gamePanel.setBackground(backColor);
		add(gamePanel, "ゲーム画面");

//		JLabel gameLabel = new JLabel();
//		gameLabel.setText("GAME");
//		gameLabel.setFont(new Font("MS 明朝", Font.BOLD, 40));
//		gameLabel.setBounds(100, 100, 150, 45);

		menuPanel.setPreferredSize(new Dimension(100, 40));
		menuPanel.setBackground(backColor);
		menuPanel.setLayout(null);

		cardReturnButton = new JButton();
		cardReturnButton.setText("カードを裏に戻す");
		cardReturnButton.setBounds(250, 5, 180, 30);
		cardReturnButton.setFocusable(false);
		cardReturnButton.addActionListener(titleButtonListener);

		cardResetButton = new JButton();
		cardResetButton.setText("リセット");
		cardResetButton.setBounds(500, 5, 180, 30);
		cardResetButton.setFocusable(false);
		cardResetButton.addActionListener(titleButtonListener);

		titlePanel.setLayout(null);
		titlePanel.add(start);
		titlePanel.add(exit);
		titlePanel.add(select);

		gamePanel.setLayout(gameLayout);
		menuPanel.add(cardReturnButton);
		menuPanel.add(cardResetButton);
		gamePanel.add(menuPanel, BorderLayout.SOUTH);
		gamePanel.add(cardPanel, BorderLayout.CENTER);
		pack();

		titlePanel.requestFocus();
		titlePanel.addKeyListener(this);
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			if (checkMenu == 0) {
				select.setLocation(select.getX(), select.getY() + 50);
				checkMenu = 1;
			} else {
				if (checkMenu == 1) {
					select.setLocation(select.getX(), select.getY() + 50);
					checkMenu = 2;
				}
			}
			break;
		case KeyEvent.VK_UP:
			if (checkMenu == 1) {
				select.setLocation(select.getX(), select.getY() - 50);
				checkMenu = 0;
			} else {
				if (checkMenu == 2) {
					select.setLocation(select.getX(), select.getY() - 50);
					checkMenu = 1;
				}

			}
			break;
		case KeyEvent.VK_ENTER://enterkeyを押した場合
			if (checkMenu == 0) {
				layout.show(getContentPane(), "ゲーム画面");
				gamePanel.requestFocus();
			} else if (checkMenu == 1) {
				System.exit(0);
			}
			break;

		}
	}

	public class GameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String comand = e.getActionCommand();
			System.out.printf("%s%n", comand);
			if (comand.equals("タイトル画面")) {
				//タイトル画面に戻る
				layout.show(getContentPane(), "タイトル画面");
				titlePanel.requestFocus();
			} else if (comand.equals("EXIT")) {
				System.exit(0);
			} else if (comand.equals("カードを裏に戻す")) {
				if (cardPanel.returnCounter == 2) {
					cardPanel.cardButtons[cardPanel.placeNum1].setText(cardPanel.buttonText1);
					cardPanel.cardButtons[cardPanel.placeNum2].setText(cardPanel.buttonText2);
					cardPanel.cardButtons[cardPanel.placeNum1].setFont(cardFont);
					cardPanel.cardButtons[cardPanel.placeNum2].setFont(cardFont);
					cardPanel.returnCounter = 0;
				}
			}else if(comand.equals("リセット")){
				int sum = 0;
				for(int i : cardPanel.cardSwitch) {
					sum += i;
				}
				if(sum == 0) {
					cardPanel.makeCardList();
					for(int i=0; i<cardPanel.cardButtons.length; i++) {
						String buttonText = ""+i; 
						cardPanel.cardButtons[i].setText(buttonText);
						cardPanel.cardButtons[i].setFont(cardFont);
						cardPanel.cardButtons[i].setEnabled(true);

						
					}
					cardPanel.returnCounter = 0;
					
				}
			}
		}

	}

}