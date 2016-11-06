package model;

import model.rules.GameRuleSetInterface;
import model.rules.RulesFactory;

public class Game {

  private Dealer m_dealer;
  private Player m_player;
  
  private GameRuleSetInterface rules = null;

  public Game()
  {
    m_dealer = new Dealer(new RulesFactory());
    m_player = new Player();
    
  } 
  
  public Game (GameRuleSetInterface ruleset){
	  
	  rules = ruleset;
	  
	  m_dealer = new Dealer (rules);
      m_player = new Player();
      
  }
  
  public GameRuleSetInterface getCurrentRuleSet(){
	  return rules;
  }
    
  public boolean IsGameOver()
  {
    return m_dealer.IsGameOver();
  }
  
  public boolean IsDealerWinner()
  {
    return m_dealer.IsDealerWinner(m_player);
  }
  
  public boolean NewGame()
  {
    return m_dealer.NewGame(m_player);
  }
  
  public boolean Hit()
  {
    return m_dealer.Hit(m_player);
  }
  
  public boolean Stand()
  {
	return m_dealer.Stand();
  }
  
  public Iterable<Card> GetDealerHand()
  {
    return m_dealer.GetHand();
  }
  
  public Iterable<Card> GetPlayerHand()
  {
    return m_player.GetHand();
  }
  
  public int GetDealerScore()
  {
    return m_dealer.CalcScore();
  }
  
  public int GetPlayerScore()
  {
    return m_player.CalcScore();
  }
    
  
}