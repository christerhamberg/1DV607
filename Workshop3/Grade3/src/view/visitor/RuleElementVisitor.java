package view.visitor;

import model.rules.WinnerRuleEqualDealer;
import model.rules.WinnerRuleEqualPlayer;
import model.rules.AmericanNewGameStrategy;
import model.rules.InternationalNewGameStrategy;
import model.rules.Soft17HitStrategy;
import model.rules.BasicHitStrategy;

public interface RuleElementVisitor {
	
	void visit (WinnerRuleEqualDealer winnerRule);
	void visit (WinnerRuleEqualPlayer winnerRule);

	void visit (AmericanNewGameStrategy newGameRule);
	void visit (InternationalNewGameStrategy newGameRule);
    
	void visit (Soft17HitStrategy hitRule);
	void visit (BasicHitStrategy hitRule);

}
