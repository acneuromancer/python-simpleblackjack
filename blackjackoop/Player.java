package blackjackoop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

	private String name;
	private List<String> hand;
	private int score;

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<>();
		this.score = 0;
	}

	public void hitCard(String card) {
		this.hand.add(card);
	}

	public List<String> getHand() {
		return this.hand;
	}

	public void setScore() {
		List<String> nonAces = hand.stream()
				.filter(p -> p != "A")
				.collect(Collectors.toCollection(() -> new ArrayList<String>()));

		List<String> aces = hand.stream()
				.filter(p -> p == "A")
				.collect(Collectors.toCollection(() -> new ArrayList<String>()));

		int sum = 0 ;

		for (String card : nonAces) {
			if ("JQK".contains(card)) {
				sum += 10;
			} else {
				sum += Integer.parseInt(card);
			}
		}

		for (int i = 0; i < aces.size(); i++) {
			if (sum <= 10) {
				sum += 11;
			} else {
				sum++;
			}
		}

		this.score = sum;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

}
