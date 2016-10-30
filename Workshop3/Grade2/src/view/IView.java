package view;

import model.Card;

public interface IView
{
  void DisplayWelcomeMessage();
  int GetInput();
  void DisplayCard(Card a_card);
  void DisplayPlayerHand(Iterable<Card> a_hand, int a_score);
  void DisplayDealerHand(Iterable<Card> a_hand, int a_score);
  void DisplayGameOver(boolean a_dealerIsWinner);
  void DisplaySelectMessage ();
  
  boolean isNewGame (int value);
  boolean isHit (int value);
  boolean isStand (int value);
  boolean isQuit (int value);

  
}