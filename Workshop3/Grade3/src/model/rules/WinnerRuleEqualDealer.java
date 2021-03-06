package model.rules;

import model.visitor.RuleElement;
import view.visitor.RuleElementVisitor;

public class WinnerRuleEqualDealer implements IWinnerRule,RuleElement {

	// MAX SCORE ADDED TO WINNER rule as one might consider that the DEALER looses if over 17 but less that 20
	// or some other rule different from 21 while 21 is still the top number.
	private int MAX_SCORE = 21;
	
	@Override
	public void accept(RuleElementVisitor visitor) {
		visitor.visit(this);
		
	}
	

	@Override
	public boolean dealerWinns(int scoreDealer, int scorePlayer) {
	    if (scorePlayer > MAX_SCORE) {
	        return true;
	    } else if (scoreDealer > MAX_SCORE) {
	        return false;
	    } else if (scorePlayer == scoreDealer) return true;
	    
	   return scoreDealer > scorePlayer;
	}
}