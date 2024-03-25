package homework.poker;

import java.util.ArrayList;
import java.util.Collections;

public class HandUtils {

  public static short rankHand(ArrayList<Card> hand) {
    if(hand.size() != 5)
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

    // There is definitely a way we can optimize this.
    // Worst case, this will run in O(8n) time.
    // Perhaps we can check for each case simultaneously by removing
    // possibilities from an array every time a condition isn't met.
    // Four of a kind & three of a kind would benefit from this
    // type of checking.

    // (I've never been the best at writing performant code. I will
    // pretty much always prioritize readability and functionality.)
  }

  // Rank 8
  private static boolean isStraightFlush(ArrayList<Card> hand) {
    Card lastCard = hand.get(0);
    for(int i = 1; i < hand.size(); i++) {
      Card currentCard = hand.get(i);
      if(lastCard.suit != currentCard.suit && lastCard.rank + 1 != currentCard.rank)
        return false;

      lastCard = currentCard;
    }

    System.out.println("Hand " + hand + " is a straight flush.");
    return true;
  }

  // Rank 7
  private static boolean isFourOfKind(ArrayList<Card> hand) {
    for(int i = 0; i < hand.size() - 1; i++) {
      Card thisCard = hand.get(i);
      Card nextCard = hand.get(i + 1);
      // If we're at the beginning of the hand and this card's rank isn't the
      // same as the next card's rank, skip to the next iteration.
      // It's possible that it's the final four instead of the first four
      // that match.
      if(thisCard.rank != nextCard.rank && i == 0) {
        continue;
      }

      // We're at least at the second iteration now.
      // If this card's rank doesn't match the next, there's no way it
      // can be a four of a kind.
      if(thisCard.rank != nextCard.rank) {
        return false;
      }
    }

    System.out.println("Hand " + hand + " is a four of a kind");
    return true;
  }

  // Rank 6
  private static boolean isFullHouse(ArrayList<Card> hand) {


    return true;
  }

  // Rank 5
  private static boolean isFlush(ArrayList<Card> hand) {
    return true;
  }

  // Rank 4
  private static boolean isStraight(ArrayList<Card> hand) {
    return true;
  }

  // Rank 3
  private static boolean isThreeOfKind(ArrayList<Card> hand) {
    return true;
  }

  // Rank 2
  private static boolean isTwoPair(ArrayList<Card> hand) {
    return true;
  }

  // Rank 1
  private static boolean isPair(ArrayList<Card> hand) {
    return true;
  }

}
