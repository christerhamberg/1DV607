package model.rules;

import view.visitor.RuleElementVisitor;

public interface GameRuleSetInterface {
	
	public INewGameStrategy getNewGameStrategyRule ();

	public IHitStrategy getHitStrategyRule ();

	public IWinnerRule getWinnerStrategyRule ();

	public void showRulesOfTheGame(RuleElementVisitor visitor);

}
