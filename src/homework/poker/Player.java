package homework.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

  public final String name;
  private final ArrayList<Card> hand;

  public Player(String name) {
    this.name = name;
    this.hand = new ArrayList<>();
  }

  public void addCard(Card card) {
    if(this.hand.size() == 5) {
      return;
    }

    this.hand.add(card);
  }

  public short getHandRank() {
    HandRanker ranker = new HandRanker(this.hand);
    return ranker.rankHand();
  }

  /**
   * Sorts hand according to rank.
   * <h2>Modifies the player's hand <i>in place</i></h2>
   */
  public void sortHandByRank() {
    Collections.sort(this.hand);
  }

  public String toString() {
    return this.name + ": " + hand;
  }

}
