package sample00_template;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	//フィールド
	ScreenMode screenMode = ScreenMode.MAIN;
	//定数
	final int WIDTH = 800;   //フレームの幅
	final int HEIGHT = 600;  //フレームの高さ
	//レイアウト（紙芝居のような設定用）
	CardLayout layout = new CardLayout();
	//コンポーネント
	MainPanel MainPanel;
//	GamePanel gamePanel;
	
	//コンストラクタ
	MainWindow(){
		//ウィンドウ左上のタイトルとアイコン
		this.setTitle("CARD GAME");
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("neko.png"));
		this.setIconImage(icon.getImage());
		
		//いつもの
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);              //右上の「×」でウィンドウを閉じる設定
		this.setResizable(false);                                  //画面サイズの変更を禁止
		this.getContentPane().setBackground(Color.GRAY);          // 背景の色
		//this.getContentPane().setBackground(new Color(0xF6F6F6));//細かく設定する場合はこちら
		this.setLayout(layout);                                    //紙芝居のようなレイアウトを設定する
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));        //サイズ設定
		this.pack();                                               //自動サイズ調整（これがないと変なサイズになる）
		this.setLocationRelativeTo(null);                          //起動時のスクリーンの位置を中央に（packより後で呼ぶ）
		//this,setLocation(450,50)                                 //細かく設定するときはこちら
	}
	//コンストラクタが呼ばれた後メインメソッドから最初に手動で呼び出す
	public void preparePanels(){
		//パネルの準備
		MainPanel  = new MainPanel();
		this.add(MainPanel,"イトル画面"); //このaddメソッドの第二引数はパネルの’通称’
//		gamePanel = new GamePanel();   //画面の切り替えは’通称’で呼び出す
//		this.add(gamePanel,"ゲーム画面");
		this.pack();
		
	}
	//preparePanels()が呼ばれた後メインメソッドからさらに手動で呼び出す
	public void prepareCompornents() {
		MainPanel.prepareComponents();
//		gamePanel.prepareComponents();
		
		
	}
	//スクリーンモードを切り替える
	//メインメソッドから最後に手動で呼び出す
	public void setFrontScreenAndFocus(ScreenMode s) {
		screenMode = s;
		//
		switch(screenMode) {
		case MAIN:
			layout.show(this.getContentPane(),"メイン画面");
			MainPanel.requestFocus();
			break;
//		case GAME:
//			layout.show(this.getContentPane(),"ゲーム画面");
//			gamePanel.requestFocus();
//			break;
		}
	}
}
