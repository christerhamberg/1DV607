package model.rules;

import view.visitor.RuleElementVisitor;

public class GameRuleSet1 implements GameRuleSetInterface{
	
	// SETUP THE FOLLOWING RULE
	// NewGame = International
	// Hit     = Soft17
	// Winner  = Dealer
	

	private INewGameStrategy myGameRule = null;
	private IHitStrategy myHitStrategyRule = null;
	private IWinnerRule myWinnerRule = null;
	
	public GameRuleSet1 (){
				
		myGameRule = new InternationalNewGameStrategy ();
		myHitStrategyRule = new Soft17HitStrategy ();
		myWinnerRule = new WinnerRuleEqualDealer ();
		
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
