package homework.poker;

import java.util.*;
import java.util.stream.Collectors;

public class HandRanker {

  private final ArrayList<Card> hand;

  private final Map<Short, Short> rankFrequencyMap = new HashMap<>();
  private final Map<Card.Suit, Short> suitFrequencyMap = new HashMap<>();

  public HandRanker(ArrayList<Card> hand) {
    this.hand = hand;

    for(Card card : hand) {
      var rankFreq = this.rankFrequencyMap.get(card.rank);
      if(rankFreq == null) {
        this.rankFrequencyMap.put(card.rank, (short) 1);
      } else {
        this.rankFrequencyMap.replace(card.rank, (short) (rankFreq + 1));
      }

      var suitFreq = this.suitFrequencyMap.get(card.suit);
      if(suitFreq == null) {
        this.suitFrequencyMap.put(card.suit, (short) 1);
      } else {
        this.suitFrequencyMap.replace(card.suit, (short) (suitFreq + 1));
      }
    }
  }

  public short rankHand() {
    if (this.hand.size() != 5)
      throw new RuntimeException("Must be exactly five cards in this hand!");

    Collections.sort(this.hand); // It should already be sorted, but just in case.

    if (this.isStraightFlush()) return 8;
    else if (this.isFourOfKind()) return 7;
    else if (this.isFullHouse()) return 6;
    else if (this.isFlush()) return 5;
    else if (this.isStraight()) return 4;
    else if (this.isThreeOfKind()) return 3;
    else if (this.isTwoPair()) return 2;
    else if (this.isPair()) return 1;
    else return 0;
  }

  // Rank 8
  // Same suit, consecutive ranks
  private boolean isStraightFlush() {
    return this.isStraight() && this.isFlush();
  }

  // Rank 7
  // Four of the same rank
  private boolean isFourOfKind() {
    // Short circuit if there are more than two ranks in the hand
    if(this.rankFrequencyMap.size() > 2) return false;

    // For each card in the hand,
    for(Card card : this.hand) {
      // Get the frequency of this rank in the hand.
      var rankFreq = this.rankFrequencyMap.get(card.rank);
      // If this rank's frequency is four, short circuit true.
      if(rankFreq == 4) return true;
    }

    return false;
  }

  // Rank 6
  // Three of same suit, two of different suit
  private boolean isFullHouse() {
    // If there are not exactly two suits in this hand, short circuit false.
    if(this.suitFrequencyMap.size() != 2) return false;

    for(Short frequency : this.suitFrequencyMap.values()) {
      if(frequency == 3 || frequency == 2) return true;
    }

    return false;
  }

  // Rank 5
  // All suits are the same
  private boolean isFlush() {
    Set<Card.Suit> suits = this.hand.stream().map(card -> card.suit).collect(Collectors.toSet());
    return suits.size() == 1;

  }

  // Rank 4
  // Consecutive ranks (suit egal)
  private boolean isStraight() {
    Card card1 = hand.get(0);
    Card card2 = hand.get(1);
    Card card3 = hand.get(2);
    Card card4 = hand.get(3);
    Card card5 = hand.get(4);

    return card1.rank + 1 == card2.rank &&
            card2.rank + 1 == card3.rank &&
            card3.rank + 1 == card4.rank &&
            card4.rank + 1 == card5.rank;

  }

  // Rank 3
  // Three cards of the same rank
  private boolean isThreeOfKind() {
    for(Short freq : this.rankFrequencyMap.values()) {
      if(freq == 3) return true;
    }

    return false;
  }

  // Rank 2
  // Two cards of the same rank, two of another, one left over.
  private boolean isTwoPair() {
    int twoPairFreq = 0;
    for(Short freq : this.rankFrequencyMap.values()) {
      if(freq == 2) twoPairFreq++;
    }

    return twoPairFreq == 2;
  }

  // Rank 1
  // Two cards of the same rank. Doesn't matter what the others are.
  private boolean isPair() {
    for(Short freq : this.rankFrequencyMap.values()) {
      if(freq == 2) return true;
    }

    return false;
  }

}
