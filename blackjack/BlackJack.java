package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BlackJack {
		
	public int calcHand(List<String> hand) {
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
		
		return sum;		
	}
	
	public void play() {
		List<String> cards = new ArrayList<String>(
				Arrays.asList(new String[] {
		        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
		        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
		        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
		        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
				})
			);
		Collections.shuffle(cards);
		
		List<String> dealer = new ArrayList<String>();
		List<String> player = new ArrayList<String>();
		
		boolean firstHand = true;
		boolean standing = false;
		
		player.add(cards.remove(0));
		dealer.add(cards.remove(0));
		player.add(cards.remove(0));
		dealer.add(cards.remove(0));
		
		while(true) {
			int playerScore = calcHand(player);
			int dealerScore = calcHand(dealer);
			
			if (standing) {
				System.out.println("Dealer cards: " + dealer + ", score: " + dealerScore);
			} else {
				System.out.println("Dealer cards: " + dealer.get(0) + " [?]");
			}
			
			System.out.println("Your cards: " + player + ", score: " + playerScore);
			System.out.println();
			
			if (standing) {
				if (dealerScore > 21) {
					System.out.println("Dealer busted, you win!");
				} else if (playerScore == dealerScore) {
					System.out.println("Push, nobody win.");
				} else if (playerScore > dealerScore && playerScore <= 21) {
					System.out.println("You beat the dealer, you win!");
				} else {
					System.out.println("You lose!");
				}
				
				break;
			}
			
			if (firstHand && playerScore == 21) {
				System.out.println("Blackjack! Nice!");
			}
			
			firstHand = false;
			
			System.out.println("What would you like to do?");
			System.out.println(" [1] Hit");
			System.out.println(" [2] Stand");
			System.out.println("Your choice: ");
			
			Scanner input = new Scanner(System.in);
			String choice = input.next();
			while (!choice.equals("1") && !choice.equals("2")) {
				choice = input.nextLine();
			}
			
			if (choice.equals("1")) {
				player.add(cards.remove(0));
				if (calcHand(player) > 21) {
					standing = true;
				}
			} else {
				standing = true;
				while(calcHand(dealer) <= 16) {
					dealer.add(cards.remove(0));
				}
			}
		}
	}
}
