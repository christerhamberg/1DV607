package model.visitor;

import view.visitor.RuleElementVisitor;

public interface RuleElement {

	public void accept (RuleElementVisitor visitor);
	
}
