package view.visitor;

import model.rules.AmericanNewGameStrategy;
import model.rules.BasicHitStrategy;
import model.rules.InternationalNewGameStrategy;
import model.rules.Soft17HitStrategy;
import model.rules.WinnerRuleEqualDealer;
import model.rules.WinnerRuleEqualPlayer;

public class PrintRulesVisitor implements RuleElementVisitor{

	@Override
	public void visit(WinnerRuleEqualDealer winnerRule) {		
		System.out.print ("\nDealer wins if equal");
		
	}

	@Override
	public void visit(WinnerRuleEqualPlayer winnerRule) {
		System.out.print ("\nPlayer wins if equal");
				
	}

	@Override
	public void visit(AmericanNewGameStrategy newGameRule) {
		System.out.print ("\nDealing cards American Style");
		
	}

	@Override
	public void visit(InternationalNewGameStrategy newGameRule) {
		System.out.print ("\nDealing cards International style");
		
	}

	@Override
	public void visit(Soft17HitStrategy hitRule) {
		System.out.print ("\nSoft17 hit");
		
	}

	@Override
	public void visit(BasicHitStrategy hitRule) {
		System.out.print ("\nBasic hit");
		
	}
	
}
