package sample03_title_game;


public class Main {
	static MainWindow mainWindow;

	public static void main(String[] args) {
		mainWindow = new MainWindow();		  //ウィンドウのみを生成
		mainWindow.preparePanels();          //ペインに直接貼るパネルのみを生成
		mainWindow.prepareCompornents();     //その他のコンポーネントを生成
		mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE); //←ここを手動で書き換えて画面を変える	
		                                                      //起動後最初に表示すべき画面を手前に持ってきてそれに注目させる
		mainWindow.setVisible(true);         //最後にウィンドウを可視化
	}

}
