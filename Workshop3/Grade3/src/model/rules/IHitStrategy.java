package model.rules;

import model.Player;
import view.visitor.RuleElementVisitor;

public interface IHitStrategy {
    boolean DoHit(Player a_dealer);

	void accept(RuleElementVisitor visitor);
}