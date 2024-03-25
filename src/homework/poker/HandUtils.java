package homework.poker;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HandUtils {

  public static short rankHand(ArrayList<Card> hand) {
    if (hand.size() != 5)
      throw new RuntimeException("Must be exactly five cards in this hand!");

    Collections.sort(hand); // It should already be sorted, but just in case.

    if (isStraightFlush(hand)) return 8;
    else if (isFourOfKind(hand)) return 7;
    else if (isFullHouse(hand)) return 6;
    else if (isFlush(hand)) return 5;
    else if (isStraight(hand)) return 4;
    else if (isThreeOfKind(hand)) return 3;
    else if (isTwoPair(hand)) return 2;
    else if (isPair(hand)) return 1;
    else return 0;

    // TODO: There is definitely a way we can optimize this.
    // Worst case, this will run in O(8n) time.
    // Perhaps we can check for each case simultaneously by removing
    // possibilities from an array every time a condition isn't met.
    // Four of a kind & three of a kind would benefit from this
    // type of checking.

    // (I've never been the best at writing performant code. I will
    // pretty much always prioritize readability and functionality.)
  }

  // Rank 8
  // Same suit, consecutive ranks
  private static boolean isStraightFlush(ArrayList<Card> hand) {
    for (int i = 0; i < hand.size() - 1; i++) {
      Card currentCard = hand.get(i);
      Card nextCard = hand.get(i + 1);

      if(currentCard.suit != nextCard.suit) {
        return false;
      }

      if(currentCard.rank + 1 != nextCard.rank) {
        return false;
      }
    }

    return true;
  }

  // Rank 7
  // Four of the same rank
  private static boolean isFourOfKind(ArrayList<Card> hand) {
    AtomicBoolean hasFourOfAKind = new AtomicBoolean(false);

    // Should realistically only run twice
    // hand of length 5, window of length 4,
    // length - window + 1 = 2.

    Poker.slidingWindow(hand, 4, window -> {
      if (hasFourOfAKind.get()) return; // skip if already got a four of a kind

      Card card1 = window.get(0);
      Card card2 = window.get(1);
      Card card3 = window.get(2);
      Card card4 = window.get(3);

      if (card1.rank == card2.rank &&
              card2.rank == card3.rank &&
              card3.rank == card4.rank) {
        hasFourOfAKind.set(true);
      }
    });

    return hasFourOfAKind.get(); // DES GOS TANG
    // (https://www.youtube.com/watch?v=cplH5dTPeus)

    // is this better than commit a48768e? who's to say!
    // i'm sure the wall clock would like to have a word with me.
  }

  // Rank 6
  // Three of same rank, two of different rank
  private static boolean isFullHouse(ArrayList<Card> hand) {
    List<Card.Suit> suits = hand.stream().map(card -> card.suit).toList();

    return suits.size() == 2;
  }

  // Rank 5
  // All suits are the same
  private static boolean isFlush(ArrayList<Card> hand) {
    Card card1 = hand.get(0);
    Card card2 = hand.get(1);
    Card card3 = hand.get(2);
    Card card4 = hand.get(3);
    Card card5 = hand.get(4);

    return card1.suit == card2.suit &&
            card2.suit == card3.suit &&
            card3.suit == card4.suit &&
            card4.suit == card5.suit;

    // I could algorithmically decide this with a for loop
    // (like in the isStraightFlush method),
    // but why would I do that when the hand size is guaranteed
    // to be five? O(1) < O(5)
  }

  // Rank 4
  // Consecutive ranks (suit egal)
  private static boolean isStraight(ArrayList<Card> hand) {
    Card card1 = hand.get(0);
    Card card2 = hand.get(1);
    Card card3 = hand.get(2);
    Card card4 = hand.get(3);
    Card card5 = hand.get(4);

    return card1.rank + 1 == card2.rank &&
            card2.rank + 1 == card3.rank &&
            card3.rank + 1 == card4.rank &&
            card4.rank + 1 == card5.rank;

    // re: isFlush
  }

  // Rank 3
  // Three cards of the same rank
  private static boolean isThreeOfKind(ArrayList<Card> hand) {
    List<Short> ranks = hand.stream().map(card -> card.rank).toList();

//    System.out.println("Three of a kind not implemented");
    return false;
  }

  // Rank 2
  // Two cards of the same rank, two of another, one left over.
  private static boolean isTwoPair(ArrayList<Card> hand) {
    // Similar to full house, except with ranks
    List<Short> ranks = hand.stream().map(card -> card.rank).toList();

    return ranks.size() == 3;
  }

  // Rank 1
  private static boolean isPair(ArrayList<Card> hand) {

    return false;
  }

}
