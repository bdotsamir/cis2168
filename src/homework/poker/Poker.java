package homework.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Poker {
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>(List.of(
            "Sean", "Natalie", "Vivian", "Emma", "Shannon", "Victor", "Alexis", "Maggie", "Amy", "John", "Alex", "Henry", "Ryan", "Eric", "David", "Andrew", "Nathan", "Dylan"
    ));
    Collections.shuffle(names); // Shuffle names

    ArrayList<Card> deck = new ArrayList<>();

    // Generate the deck
    for(int rank = 1; rank <= 13; rank++) { // for each rank
      for(int suit = 1; suit <= 4; suit++) { // for each suit
        Card.Suit cardSuit = Card.parseSuit(suit);
        Card card = new Card(cardSuit, (short) rank);
        deck.add(card);
      }
    }
    Collections.shuffle(deck); // Shuffle the deck

    // Create players
    ArrayList<Player> players = new ArrayList<>();
    for(int i = 0; i < 4; i++) { // Four players
      String playerName = names.remove(i);
      players.add(new Player(playerName));
    }

    // Divvy up cards by player
    for(int i = 0; i < 5 * players.size(); i++) {
      // Round-table distribution
      Player p = players.get(i % players.size());
      Card topCard = deck.remove(0);
      p.addCard(topCard);
      p.sortHandByRank();
    }

    for(Player player : players) {
      System.out.println(player + " | Rank: " + player.getHandRank());
    }

  }
}
