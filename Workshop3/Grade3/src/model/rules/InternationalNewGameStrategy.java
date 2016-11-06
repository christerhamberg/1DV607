package model.rules;

import model.Dealer;
import model.Player;
import model.visitor.RuleElement;
import view.visitor.RuleElementVisitor;

public class InternationalNewGameStrategy implements INewGameStrategy,RuleElement {

	@Override
	public void accept(RuleElementVisitor visitor) {
		visitor.visit(this);
		
	}
	
  public boolean NewGame(Dealer a_dealer, Player a_player) {
    
	  // Player first card
	  a_dealer.giveUserCardShow (a_player);
	  
	  // Dealer first card
	  a_dealer.giveUserCardShow (a_dealer);
	  
	  // Player second card
	  a_dealer.giveUserCardShow (a_player);
	  
    return true;
  }
  
  
}