package model.rules;

import view.visitor.RuleElementVisitor;

public interface IWinnerRule {
    boolean dealerWinns(int scoreDealer, int scorePlayer);

	void accept(RuleElementVisitor visitor);
}