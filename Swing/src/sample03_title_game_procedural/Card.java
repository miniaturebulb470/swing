package sample03_title_game_procedural;

import java.util.ArrayList;
import java.util.Collections;

public class Card {
	ArrayList<Integer> cardList = new ArrayList<Integer>();
	final int TOTAL_CARD = 10;
	int[] cardStatus = new int[TOTAL_CARD];
	
	
	public Card() {
		makeCardList();
		Collections.shuffle(this.cardList);
		initCardStatus();
		printCardList();
	}
	public void makeCardList() {
		for (int i = 1; i <= this.TOTAL_CARD; i++) {
			this.cardList.add(Integer.valueOf(i));
		}
	}

	public void printCardList() {
		for (int i = 0; i < cardList.size(); i++) {
			System.out.printf("%d ", cardList.get(i));
		}
		System.out.printf("%n");
	}
	public void printCardStatus() {
		for (int i = 0; i < cardStatus.length; i++) {
			System.out.printf("%d ", cardStatus[i]);
		}
		System.out.printf("%n");
	}
	public void initCardStatus() {
		for(int i=0; i<TOTAL_CARD; i++) {
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
	public int countFacedUpCard() {
		int counter = 0;
		for(int i=0; i<this.cardStatus.length; i++) {
			if(this.cardStatus[i] == 2) {
				counter++;
			}
		}
		return counter;
	}
	public boolean compareFacedUpCards() {
		ArrayList<Integer> indexesOfStatus_2 = new ArrayList<Integer>();
		for (int i=0; i<cardList.size(); i++) {
			if(cardStatus[i] == 2) {
				indexesOfStatus_2.add(i);
			}
		}
		int cardNum1 = cardList.get(indexesOfStatus_2.get(0));
		int cardNum2 = cardList.get(indexesOfStatus_2.get(1));
		cardNum1 = changeNumberComparable(cardNum1);
		cardNum2 = changeNumberComparable(cardNum2);
		return cardNum1 == cardNum2; 
	}
	public int changeNumberComparable(int number) {
		if(number > 5) {
			number = number - 5;
		}
		return number;
	}

}
