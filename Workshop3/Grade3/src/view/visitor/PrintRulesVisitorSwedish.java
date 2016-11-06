package view.visitor;

import model.rules.AmericanNewGameStrategy;
import model.rules.BasicHitStrategy;
import model.rules.InternationalNewGameStrategy;
import model.rules.Soft17HitStrategy;
import model.rules.WinnerRuleEqualDealer;
import model.rules.WinnerRuleEqualPlayer;

public class PrintRulesVisitorSwedish implements RuleElementVisitor{

	@Override
	public void visit(WinnerRuleEqualDealer winnerRule) {		
		System.out.print ("\nDealer vinner vid lika resultat");
		
	}

	@Override
	public void visit(WinnerRuleEqualPlayer winnerRule) {
		System.out.print ("\nSpelaren vinner vid lika resultat");
				
	}

	@Override
	public void visit(AmericanNewGameStrategy newGameRule) {
		System.out.print ("\nKorten delas ut med Amerikansk stil");
		
	}

	@Override
	public void visit(InternationalNewGameStrategy newGameRule) {
		System.out.print ("\nKorten delas ut Internationell stil");
		
	}

	@Override
	public void visit(Soft17HitStrategy hitRule) {
		System.out.print ("\nSoft17");
		
	}

	@Override
	public void visit(BasicHitStrategy hitRule) {
		System.out.print ("\nBasic");
		
	}
	
}
