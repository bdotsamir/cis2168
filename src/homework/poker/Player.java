package homework.poker;

import java.util.ArrayList;

public class Player {

  public final String name;
  private ArrayList<Card> hand;

  public Player(String name) {
    this.name = name;
  }

  public boolean addCard(Card card) {
    if(this.hand.size() == 5) {
      return false;
    }

    this.hand.add(card);
    return true;
  }

  public String toString() {
    return this.name + ": " + hand;
  }

}
