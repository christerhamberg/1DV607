package model.rules;

import model.Dealer;
import model.Player;

class AmericanNewGameStrategy implements INewGameStrategy {

  public boolean NewGame(Dealer a_dealer, Player a_player) {

	  // Player first card
	  a_dealer.giveUserCardShow (a_player);

		  
	  // Dealer first card
	  a_dealer.giveUserCardShow (a_dealer);

	  // Player second card		  
	  a_dealer.giveUserCardShow (a_player);

		  
	  // Dealer second card = HIDDEN
	  a_dealer.giveUserCardHide (a_dealer);

		  /*
	      Card c;

          c = a_deck.GetCard();
          c.Show(true);
          a_player.DealCard(c);

          c = a_deck.GetCard();
          c.Show(true);
          a_dealer.DealCard(c);
    
          c = a_deck.GetCard();
          c.Show(true);
          a_player.DealCard(c);

          c = a_deck.GetCard();
          c.Show(false);
          a_dealer.DealCard(c);
    
          */
    

	  return true;
  }
  
  
   
}