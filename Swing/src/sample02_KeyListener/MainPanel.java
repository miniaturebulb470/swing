package sample02_KeyListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//定数フィールド
	Color backgroundColor = Color.GRAY;
	//コンポーネント
	CatLabel catLabel;
	//キーリスナー
	MyKeyListener myKeyListener;
	
	//コンストラクタ
	MainPanel() {
		this.setLayout(null);               //レイアウトの設定
		this.setBackground(backgroundColor);//背景の色

	}

	//コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		//
		catLabel = new CatLabel();     //ラベルを作成

		this.add(catLabel);            //ラベルをこのパネルにはる
		myKeyListener = new MyKeyListener(this);           //
			
	}
		
	//内部クラス（ネコの動きを制御する）
	private class MyKeyListener implements KeyListener {
		//貼り付け先を保持
		MainPanel panel;
		
		//コンストラクタ
		MyKeyListener(MainPanel p){
			super();
			panel = p;
			p.addKeyListener(this);
		}
		@Override 
		public void keyTyped(KeyEvent e) {  //キーが押されたときに反応するメソッドを無効にする
			//do nothing
		}
		
		@Override
		public void keyReleased(KeyEvent e ) {    ////キーが離れたときに反応するメソッドを無効にする
			//do nothing
		}
		@Override
		public void keyPressed(KeyEvent e) {     //キーが長押しされたときにも反応するメソッド
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:    //上を押した場合
				if(catLabel.getY() > 0) {
					catLabel.setLocation(catLabel.getX(),catLabel.getY()-20);
				}
				break;
			case KeyEvent.VK_DOWN:    //下を押した場合
				if(catLabel.getY() + catLabel.catImage.getHeight(null) < panel.getHeight()) {
					catLabel.setLocation(catLabel.getX(),catLabel.getY()+20);
				}
				break;
			case KeyEvent.VK_LEFT:    //左を押した場合
				if(catLabel.getX() > 0) {
					catLabel.setLocation(catLabel.getX()-20,catLabel.getY());
				}
				break;
			case KeyEvent.VK_RIGHT:    //右を押した場合
				if(catLabel.getX() + catLabel.catImage.getWidth(null) < panel.getWidth()) {
					catLabel.setLocation(catLabel.getX()+20,catLabel.getY());
				}
				break;
			case KeyEvent.VK_ENTER:    //ENTERを押した場合
				if(backgroundColor == Color.GRAY) {
					backgroundColor = Color.DARK_GRAY;
					panel.setBackground(backgroundColor);
				} else if(backgroundColor == Color.DARK_GRAY) {
					backgroundColor = Color.GRAY;
					panel.setBackground(backgroundColor);
				}

				break;
				
			}
		}
	}

}
