package blackjackoop;

import java.util.Scanner;

public class Game {

	public void start() {
		Deck deck = new Deck();
		deck.shuffleDeck();
		Player player = new Player("player");
		Player dealer = new Player("dealer");

		boolean firstHand = true;
		boolean standing = false;

		player.hitCard(deck.hit());
		dealer.hitCard(deck.hit());
		player.hitCard(deck.hit());
		dealer.hitCard(deck.hit());

		while(true) {
			player.setScore();
			dealer.setScore();

			if (standing) {
				System.out.println("Dealer cards: " + dealer.getHand() + ", score: " + dealer.getScore());
			} else {
				System.out.println("Dealer cards: " + dealer.getHand().get(0) + " [?]");
			}
			System.out.println("Your cards: " + player.getHand() + ", score: " + player.getScore());
			System.out.println();

			if (standing) {
				if (dealer.getScore() > 21) {
					System.out.println("Dealer busted, you win!");
				} else if (player.getScore() == dealer.getScore()) {
					System.out.println("Push, nobody win.");
				} else if (player.getScore() > dealer.getScore() && player.getScore() <= 21) {
					System.out.println("You beat the dealer, you win!");
				} else {
					System.out.println("You lose!");
				}

				break;
			}

			if (firstHand && player.getScore() == 21) {
				System.out.println("Blackjack! Nice!");
			}

			firstHand = false;

			String choice = choiceToContinue();

			if (choice.equals("1")) {
				player.hitCard(deck.hit());
				player.setScore();
				if (player.getScore() > 21) {
					standing = true;
				}
			} else {
				standing = true;
				while(dealer.getScore() <= 16) {
					dealer.hitCard(deck.hit());
					dealer.setScore();
				}
			}
		}

	}

	private String choiceToContinue() {
		System.out.println("What would you like to do?");
		System.out.println(" [1] Hit");
		System.out.println(" [2] Stand");
		System.out.println("Your choice: ");

		Scanner input = new Scanner(System.in);
		String choice = input.next();
		while (!choice.equals("1") && !choice.equals("2")) {
			choice = input.nextLine();
		}

		return choice;
	}

}
