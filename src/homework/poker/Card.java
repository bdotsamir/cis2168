package homework.poker;

public class Card {

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
      case SPADES -> "Spades";
      case CLUBS -> "Clubs";
      case DIAMONDS -> "Diamonds";
      case HEARTS -> "Hearts";
    };

    String stringRank = switch(this.rank) {
      case 1 -> "Ace";
      case 11 -> "Jack";
      case 12 -> "Queen";
      case 13 -> "King";
      default -> String.valueOf(this.rank);
    };

    return stringRank + " of " + stringSuit;
  }

}
