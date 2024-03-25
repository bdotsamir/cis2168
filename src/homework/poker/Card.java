package homework.poker;

import org.jetbrains.annotations.NotNull;

public class Card implements Comparable<Card> {

  public final Suit suit;
  public final short rank; // Ace (1), 2 - 10, J, Q, K (11 - 13)

  public Card(Suit suit, short rank) {
    this.suit = suit;
    this.rank = rank;
  }

  public enum Suit {
    SPADES,
    CLUBS,
    DIAMONDS,
    HEARTS
  }

  public static Suit parseSuit(int num) {
    if(num < 1 || num > 4) {
      throw new RuntimeException("Invalid number for suit: " + num + " must be in range [1,4]");
    }

    return switch (num) {
      case 1 -> Suit.SPADES;
      case 2 -> Suit.CLUBS;
      case 3 -> Suit.DIAMONDS;
      case 4 -> Suit.HEARTS;
      default -> throw new RuntimeException("Should not have gotten here");
    };
  }
  public String toString() {
    String stringSuit = switch(this.suit) {
      case SPADES -> "♠️";
      case CLUBS -> "♣️";
      case DIAMONDS -> "♦️";
      case HEARTS -> "♥️";
    };

    String stringRank = switch(this.rank) {
      case 1 -> "A";
      case 11 -> "J";
      case 12 -> "Q";
      case 13 -> "K";
      default -> String.valueOf(this.rank);
    };

    return stringRank + " " + stringSuit;
  }

  @Override
  public int compareTo(@NotNull Card o) {
    return Short.compare(this.rank, o.rank);
  }

}
