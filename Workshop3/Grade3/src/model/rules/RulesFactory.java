package model.rules;

public class RulesFactory {
	
	int BASIC_HIT_STRATEGY = 0;
	int SOFT17_HIT_STRATEGY = 1;
	
	int WINNER_RULE_EQUAL_DEALER = 0;
	int WINNER_RULE_EQUAL_PLAYER = 1;
	
	private int hitRule ;
	private int winnerRule;

	// Change rules here
	
	public RulesFactory (){
		hitRule = BASIC_HIT_STRATEGY;
		winnerRule = WINNER_RULE_EQUAL_DEALER;
	}
	
  public IHitStrategy GetHitRule() {
	  if (hitRule == BASIC_HIT_STRATEGY) return new BasicHitStrategy();
	  else if (hitRule == SOFT17_HIT_STRATEGY)  return new Soft17HitStrategy();
	  else return new BasicHitStrategy(); // default
  }

  public INewGameStrategy GetNewGameRule() {
    return new AmericanNewGameStrategy();
  }
  
  public IWinnerRule GetWinnerRule(){
	  if (winnerRule == WINNER_RULE_EQUAL_DEALER) return new WinnerRuleEqualDealer();
	  else if (winnerRule == WINNER_RULE_EQUAL_PLAYER) return new WinnerRuleEqualPlayer();
	  else return new WinnerRuleEqualDealer();
  }
  
}