package model.rules;

public class RuleSet3Factory implements AbstractFactoryInterface{

	@Override
	public GameRuleSetInterface createRuleSet() {
		 return new GameRuleSet3 ();
	}
	
}
