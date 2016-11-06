package model.rules;

import model.Dealer;
import model.Player;
import view.visitor.RuleElementVisitor;

public interface INewGameStrategy {
    boolean NewGame(Dealer a_dealer, Player a_player);

	void accept(RuleElementVisitor visitor);
}