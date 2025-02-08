package sample03_title_game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{              //タイトルパネルとほぼ同じ
	private static final long serialVersionUID = 1L;
	//コンポーネント
	JLabel gameLabel;
	
	//コンストラクタ
	GamePanel(){
		//パネルサイズと貼り付け位置の設定は不要（CardLayoutが勝手に画面サイズに合わせてくれる）
		this.setLayout(null);//レイアウトの設定
		this.setBackground(Color.DARK_GRAY);//背景の色
		//その他の追加設定をここに追加
	}
	
	//コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		//以降コンポーネントに関する命令（以下は一例）
		gameLabel = new JLabel();                           //ラベル作成
		gameLabel.setText("ゲーム画面");                    //ラベルに文字を記入
		gameLabel.setFont(new Font("MS 明朝",Font.BOLD,40));
		gameLabel.setBounds(100, 200, 300, 50);             //位置とサイズを指定
		this.add(gameLabel);                                //ラベルをこのパネルに貼る
		
	}

}
