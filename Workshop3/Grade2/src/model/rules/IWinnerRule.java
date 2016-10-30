package model.rules;

public interface IWinnerRule {
    boolean dealerWinns(int scoreDealer, int scorePlayer);
}