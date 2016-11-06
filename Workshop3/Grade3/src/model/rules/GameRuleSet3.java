package model.rules;

import view.visitor.RuleElementVisitor;

public class GameRuleSet3 implements GameRuleSetInterface{
	
	// SETUP THE FOLLOWING RULE
	// NewGame = American
	// Hit     = Soft17
	// Winner  = Player
	

	private INewGameStrategy myGameRule = null;
	private IHitStrategy myHitStrategyRule = null;
	private IWinnerRule myWinnerRule = null;
	  
	public GameRuleSet3 (){
		
		myGameRule = new AmericanNewGameStrategy ();
		myHitStrategyRule = new Soft17HitStrategy ();
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
