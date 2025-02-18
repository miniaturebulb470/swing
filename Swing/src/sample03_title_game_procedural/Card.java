package sample03_title_game_procedural;

import java.util.ArrayList;
import java.util.Collections;

public class Card {
	ArrayList<Integer> cardList = new ArrayList<Integer>();
	int totalCardNumber = 52;
	int[] cardStatus = new int[totalCardNumber];

	public Card() {
		makeCardList();
		Collections.shuffle(this.cardList);
		initCardStatus();
		printCardList();
	}

	public void makeCardList() {
		for (int i = 1; i <= this.totalCardNumber; i++) {
			this.cardList.add(Integer.valueOf(i));
		}
	}

	public void printCardList() {
		switch (totalCardNumber) {
		case 10 -> {
			for (int i = 0; i < cardList.size(); i++) {
				System.out.printf("%d ", cardList.get(i));
			}
			System.out.printf("%n");
		}
		case 52 -> {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 11; j++) {
					if (i * 11 + j == 52) {
						break;
					}
					System.out.printf("%4d ", cardList.get(i * 11 + j));
				}
				System.out.printf("%n");
			}
			System.out.printf("%n");
		}
		}
	}

	public void printCardStatus() {
		switch (totalCardNumber) {
		case 10 -> {
			for (int i = 0; i < cardStatus.length; i++) {
				System.out.printf("%d ", cardStatus[i]);
			}
			System.out.printf("%n");

		}
		case 52 -> {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 11; j++) {
					if (i * 11 + j == 52) {
						break;
					}
					System.out.printf("%d ", cardStatus[i * 11 + j]);
				}
				System.out.printf("%n");
			}
			System.out.printf("%n");
		}

		}

	}

	public void initCardStatus() {
		for (int i = 0; i < totalCardNumber; i++) {
			this.cardStatus[i] = 1;
		}
	}

	public void switchStatusFacedUp(int placeNum) {
		this.cardStatus[placeNum] = 2;
	}

	public void switchStatusTurnedOver(int placeNum) {
		this.cardStatus[placeNum] = 1;
	}

	public void switchStatusCollected(int placeNum) {
		this.cardStatus[placeNum] = 0;
	}

	public int getCardNumber(int placeNum) {
		return this.cardList.get(placeNum);
	}

	public int countCardsWithSameStatus(int status) {
		int counter = 0;
		for (int i = 0; i < this.cardStatus.length; i++) {
			if (this.cardStatus[i] == status) {
				counter++;
			}
		}
		return counter;
	}

	public boolean twoCardsIsSameNumber() {
		int cardNum1 = cardList.get(getIndexesOfStatus(2).get(0));
		int cardNum2 = cardList.get(getIndexesOfStatus(2).get(1));
		cardNum1 = changeNumberComparable(cardNum1);
		cardNum2 = changeNumberComparable(cardNum2);
		return cardNum1 == cardNum2;
	}

	public ArrayList<Integer> getIndexesOfStatus(int status) {
		ArrayList<Integer> indexesOfStatus = new ArrayList<Integer>();
		for (int i = 0; i < cardList.size(); i++) {
			if (cardStatus[i] == status) {
				indexesOfStatus.add(i);
			}
		}
		return indexesOfStatus;
	}

	public int changeNumberComparable(int number) {
		if (totalCardNumber == 10) {
			number = number % 5;
		}
		if (totalCardNumber == 52) {
			number = number % 13;
		}
		return number;
	}

}
