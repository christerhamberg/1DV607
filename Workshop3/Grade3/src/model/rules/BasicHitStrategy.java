package model.rules;

import model.Player;
import model.visitor.RuleElement;
import view.visitor.RuleElementVisitor;

public class BasicHitStrategy implements IHitStrategy, RuleElement {
	
	@Override
	public void accept(RuleElementVisitor visitor) {
		visitor.visit(this);
		
	}
	
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer) {
      return a_dealer.CalcScore() < g_hitLimit;  
    }
}