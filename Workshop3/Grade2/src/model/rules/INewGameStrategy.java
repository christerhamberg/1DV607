package model.rules;

import model.Dealer;
import model.Player;

public interface INewGameStrategy {
    boolean NewGame(Dealer a_dealer, Player a_player);
}