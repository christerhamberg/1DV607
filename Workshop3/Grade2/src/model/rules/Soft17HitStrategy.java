package model.rules;

import model.Card;
import model.Player;

class Soft17HitStrategy implements IHitStrategy {
	
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer) {
    	
    	if (a_dealer.CalcScore() == g_hitLimit){
    		
    	    // See http://www.readybetgo.com/blackjack/strategy/soft-17-rule-2496.html

    	    int count = 0;

    	    for(Card c : a_dealer.GetHand()) {
    	        if (c.GetValue() == Card.Value.Ace) count++;
    	    }
    	        	    
    	    if (count == 1) return true;
    	    else return false;
    		
    	}
    	else {
    		
             return a_dealer.CalcScore() < g_hitLimit;
    	
    	}
    	
    }
    
}