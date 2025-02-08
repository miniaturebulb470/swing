package card_console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Card_console {

	public static void main(String[] args) {
		ArrayList<Integer> cardList = new ArrayList<Integer>();
		final int TOTAL_CARD = 6;
		int getNum1;
		int getNum2;
		Scanner scan = new Scanner(System.in);
		int counter = 0;

		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= TOTAL_CARD / 2; j++) {
				cardList.add(Integer.valueOf(j));
			}
		}

		Collections.shuffle(cardList);

		while (true) {
//			printList(cardList);

			int clear = 0;
			for (int i = 0; i < cardList.size(); i++) {
				clear += cardList.get(i);
			}
			if (clear == 0) {
				break;
			}

			System.out.printf("1～%dの数字を２回入力してください%n",TOTAL_CARD);
			System.out.printf("１回目 : ");
			getNum1 = scan.nextInt() - 1;

			System.out.printf("%n１回目の数 : %d%n", cardList.get(getNum1));
			System.out.printf("２回目 : ");
			getNum2 = scan.nextInt() - 1;
			System.out.printf("%n２回目の数 : %d%n", cardList.get(getNum2));

			if (cardList.get(getNum1) == cardList.get(getNum2)) {
				System.out.printf("あたり%n");
				cardList.set(getNum1, 0);
				cardList.set(getNum2, 0);
			} else {
				System.out.printf("はずれ%n");
			}
			counter++;

		}
		System.out.printf("クリア！！！%n");
		System.out.printf("回数 : %d回%n",counter);
		
		
	}

	public static void printList(ArrayList<Integer> list) {
		System.out.printf("[ ");
		for (int i = 0; i < 6; i++) {
			System.out.printf("%d ", list.get(i));
		}
		System.out.printf("]%n");

	}

}
