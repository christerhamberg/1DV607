package model.rules;

public class RuleSet1Factory implements AbstractFactoryInterface{

	@Override
	public GameRuleSetInterface createRuleSet() {
		 return new GameRuleSet1 ();
	}
	
}
