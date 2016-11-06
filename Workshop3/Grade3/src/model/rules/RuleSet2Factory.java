package model.rules;

public class RuleSet2Factory implements AbstractFactoryInterface{

	@Override
	public GameRuleSetInterface createRuleSet() {
		 return new GameRuleSet2 ();
	}
	
}
