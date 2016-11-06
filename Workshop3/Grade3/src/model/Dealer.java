package model;

import model.rules.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinnerRule m_winnerRule;
  
  // OLD INTEFRFCAE
  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_winnerRule = a_rulesFactory.GetWinnerRule();
    		
  }
  
  // NEW INTEFACE
  public Dealer (GameRuleSetInterface gameRules){

	    m_newGameRule = gameRules.getNewGameStrategyRule();
	    m_hitRule = gameRules.getHitStrategyRule();
	    m_winnerRule = gameRules.getWinnerStrategyRule();

  }
  
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(this, a_player);   
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {

      giveUserCardShow (a_player);
      
      return true;
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {
	  	  
	  return m_winnerRule.dealerWinns(CalcScore(), a_player.CalcScore());
	  
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }

  public boolean Stand (){
	  
	  if (m_deck != null){
		  		  
		  // ShowHand () of the dealer i.e. the hidden card
		  this.ShowHand();
		  
		  // Give game control to the dealer
		  
		  while (m_hitRule.DoHit(this)){
			  
			  giveUserCardShow (this);
		      
		  }
		  
	  }
	  
	  return true;
  }
  
  public void giveUserCardShow (Player a_player){
	  
	    Card c;
	    
	    c = m_deck.GetCard();
	    c.Show(true);
	    a_player.DealCard(c);
	  
  }

  public void giveUserCardHide (Player a_player){
	  
	    Card c;
	    
	    c = m_deck.GetCard();
	    a_player.DealCard(c);
	  
}
  
  
  
}