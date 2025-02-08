package sample00_template;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//コンポーネント
	JLabel titleLabel;
	JLabel start;
	JLabel exit;
	JLabel select;
	JLabel message;
	Menu checkMenu = Menu.START;
	Border border = BorderFactory.createLineBorder(Color.BLACK,2);//いらなければ消す
//	MyKeyListener myKeyListener;
	
	//列挙型
	public enum Menu {
		START,
		EXIT,
	}
	
	//コンストラクタ
	MainPanel(){
		//パネルサイズと貼り付け位置の設定は不要（CardLayoutが勝手に画面サイズを書き換えてくれる）
		this.setLayout(null);         //レイアウトの設定
		this.setBackground(Color.GREEN);//背景の色
		//その他の追加設定をここに追加
	}
	
	//コンストラクタが呼ばれた後手動で呼び出す
	public void prepareComponents() {
		//以降コンポーネントに関する命令（以下は例）
		//タイトルロゴ
		titleLabel = new JLabel();             //ラベル作成
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.setText("タイトル画面");    //ラベルに文字を記入
		titleLabel.setBounds(100,100,100,50);    //位置とサイズを設定
		                                       //↑コンストラクタでレイアウトを「null」に設定したので
		                                       //  細かく手動で位置を設定できる
		this.add(titleLabel);                  //ラベルをこのパネルに貼る
		
	}

}
