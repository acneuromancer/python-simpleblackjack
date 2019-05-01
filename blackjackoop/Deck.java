package blackjackoop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Deck {

	private List<String> cards;

	public Deck() {
		this.cards = new ArrayList<String>(
				Arrays.asList(new String[] {
				        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
				        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
				        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
				        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
						})
					);
	}

	public void shuffleDeck() {
		Collections.shuffle(cards);
	}

	public String hit() {
		return cards.remove(0);
	}

	public List<String> getDeck() {
		return this.cards;
	}

}
