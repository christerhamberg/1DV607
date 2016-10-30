package model.rules;

import model.Player;

class Soft17HitStrategy implements IHitStrategy {
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer) {
    	
    	if (a_dealer.CalcScore() == g_hitLimit){
    		if (a_dealer.hasOnlyOneAce() == true){
       			return true;
    		}
    		else return false;
    	}
    	else {
             return a_dealer.CalcScore() < g_hitLimit;
    	}
    }
}