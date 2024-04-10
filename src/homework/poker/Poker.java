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

    // Uncomment these lines to test each case
//    testStraightFlush();
//    testFourKind();
//    testStraight();
//    testFlush();
//    testThreeKind();
//    testTwoPair();
//    testPair();

    for(Player player : players) {
      System.out.println(player + " | Rank: " + player.getHandRank());
    }

  }

  public static void testStraightFlush() {
    var player = new Player("Straight Flush");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 4));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 5));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 6));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 7));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }

  public static void testFourKind() {
    var player = new Player("Four Kind");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.DIAMONDS, (short) 3));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 3));
    player.addCard(new Card(Card.Suit.SPADES, (short) 3));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 7));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }

  public static void testStraight() {
    var player = new Player("Straight");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 4));
    player.addCard(new Card(Card.Suit.SPADES, (short) 5));
    player.addCard(new Card(Card.Suit.DIAMONDS, (short) 6));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 7));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }

  public static void testFlush() {
    var player = new Player("Flush");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 5));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 7));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 9));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 11));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }

  public static void testThreeKind() {
    var player = new Player("Three Kind");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.DIAMONDS, (short) 3));
    player.addCard(new Card(Card.Suit.SPADES, (short) 3));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 9));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 10));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }

  public static void testTwoPair() {
    var player = new Player("Two Pair");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 3));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 5));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 5));
    player.addCard(new Card(Card.Suit.SPADES, (short) 11));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }

  public static void testPair() {
    var player = new Player("Pair");
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.CLUBS, (short) 3));
    player.addCard(new Card(Card.Suit.HEARTS, (short) 7));
    player.addCard(new Card(Card.Suit.SPADES, (short) 9));
    player.addCard(new Card(Card.Suit.DIAMONDS, (short) 11));

    System.out.println(player + " | Rank: " + player.getHandRank());
  }
}
