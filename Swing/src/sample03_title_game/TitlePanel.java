package sample03_title_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class TitlePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//
//	Color backgroundColor = Color.green;
//	Door doorcheck = Door.CLOSE;
	//コンポーネント
	JLabel title;
	JLabel start;
	JLabel exit;
	JLabel select;
	JLabel message;
	Menu checkMenu = Menu.START;
	Border border = BorderFactory.createLineBorder(Color.BLACK,2);//いらなければ消す
	MyKeyListener myKeyListener;
	
	//列挙型
	public enum Menu {
		START,
		EXIT,
	}
	
	//コンストラクタ
	TitlePanel(){
		//パネルサイズと貼り付け位置の設定は不要（CardLayoutが勝手に画面サイズを書き換えてくれる）
		this.setLayout(null);         //レイアウトの設定
		this.setBackground(Color.GRAY);//背景の色
		//その他の追加設定をここに追加
	}
	
	//コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		//以降コンポーネントに関する命令（以下は例）
		//タイトルロゴ
		title = new JLabel();             //ラベル作成
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setText("タイトル画面");    //ラベルに文字を記入
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setVerticalTextPosition(SwingConstants.BOTTOM);
		title.setBounds(90,0,600,350);    //位置とサイズを設定
		                                       //↑コンストラクタでレイアウトを「null」に設定したので
		                                       //  細かく手動で位置を設定できる
		title.setBorder(border);           //縁取り（いらなければ消す）
		
		//選択肢
		start = new JLabel();
		start.setText("START");
		start.setFont(new Font("MS 明朝",Font.BOLD,40));
		start.setHorizontalTextPosition(JLabel.CENTER);
		start.setVerticalTextPosition(JLabel.CENTER);
		start.setBounds(330,400,150,45);    //位置とサイズを設定
		                                       //↑コンストラクタでレイアウトを「null」に設定したので
		                                       //  細かく手動で位置を設定できる
//		start.setBorder(border);           //縁取り（いらなければ消す）

		exit = new JLabel();
		exit.setText("EXIT");
		exit.setFont(new Font("MS 明朝",Font.BOLD,40));
		exit.setHorizontalTextPosition(JLabel.CENTER);
		exit.setVerticalTextPosition(SwingConstants.BOTTOM);
		exit.setBounds(350,450,110,45);    //位置とサイズを設定
		                                       //↑コンストラクタでレイアウトを「null」に設定したので
		                                       //  細かく手動で位置を設定できる
//		exit.setBorder(border);           //縁取り（いらなければ消す）
		
		//選択アイコン
		select = new JLabel();
		select.setBackground(Color.blue);
		select.setOpaque(true);  //透明にするかどうかを切り替える
		select.setBounds(280,400,40,40);    //位置とサイズを設定
		                                       //↑コンストラクタでレイアウトを「null」に設定したので
		                                       //  細かく手動で位置を設定できる
		select.setBorder(border);           //縁取り（いらなければ消す）
		
		
		
		//説明
		message = new JLabel();
		message.setText("選択：↑↓　　　　決定：SPACE");
		message.setHorizontalTextPosition(JLabel.CENTER);
		message.setVerticalTextPosition(JLabel.BOTTOM);
		message.setBounds(249,517,300,30);    //位置とサイズを設定
		                                       //↑コンストラクタでレイアウトを「null」に設定したので
		                                       //  細かく手動で位置を設定できる
//		message.setBorder(border);           //縁取り（いらなければ消す）
		
		
		
		//配置
		this.setLayout(null);
		this.add(title);                  //ラベルをこのパネルに貼る
		this.add(start);                  //ラベルをこのパネルに貼る
		this.add(exit);                  //ラベルをこのパネルに貼る
		this.add(select);                  //ラベルをこのパネルに貼る
		this.add(message);                  //ラベルをこのパネルに貼る
		
		
		//リスナーの設定
		myKeyListener = new MyKeyListener(this);
	}

	private class MyKeyListener implements KeyListener{
		//
		TitlePanel panel;
		
		//
		MyKeyListener(TitlePanel p){
			super();
			panel = p;
			panel.addKeyListener(this);
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
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN://下を押した場合
				if(checkMenu == Menu.START) {
					select.setLocation(select.getX(), select.getY()+50);
					checkMenu = Menu.EXIT;
				}
				break;
			case KeyEvent.VK_UP://上を押した場合
				if(checkMenu == Menu.EXIT) {
					select.setLocation(select.getX(), select.getY()-50);
					checkMenu = Menu.START;
				}
				break;
			case KeyEvent.VK_SPACE://SPACEを押した場合
				if(checkMenu == Menu.START) {
					//
					Main.mainWindow.setFrontScreenAndFocus(ScreenMode.GAME);
				}else if(checkMenu == Menu.EXIT){
					//
					System.exit(0);
				}
				break;
				
			}
		}
		
		
		
	}
}
