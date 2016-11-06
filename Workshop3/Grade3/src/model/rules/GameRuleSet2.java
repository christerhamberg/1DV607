package model.rules;

import view.visitor.RuleElementVisitor;

public class GameRuleSet2 implements GameRuleSetInterface{
	
	// SETUP THE FOLLOWING RULE
	// NewGame = International
	// Hit     = Basic
	// Winner  = Player
	

	private INewGameStrategy myGameRule = null;
	private IHitStrategy myHitStrategyRule = null;
	private IWinnerRule myWinnerRule = null;
	  
	public GameRuleSet2 (){
		
		myGameRule = new InternationalNewGameStrategy ();
		myHitStrategyRule = new BasicHitStrategy ();
		myWinnerRule = new WinnerRuleEqualPlayer ();

	}
	  
	public void showRulesOfTheGame (RuleElementVisitor visitor){
		myGameRule.accept(visitor);
		myHitStrategyRule.accept(visitor);
		myWinnerRule.accept(visitor);
	}
	  
	public INewGameStrategy getNewGameStrategyRule (){
		return myGameRule;
	}

	public IHitStrategy getHitStrategyRule (){
		return myHitStrategyRule;
	}
	  
	public IWinnerRule getWinnerStrategyRule (){
		return myWinnerRule;
	}

}
