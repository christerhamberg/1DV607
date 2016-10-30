package model;

import java.util.List;
import java.util.LinkedList;
import model.observer.Observer;

public class Player {

  private List<Card> m_hand;
  protected final int g_maxScore = 21;
  
  public Player()
  {
  
    m_hand = new LinkedList<Card>();
    //System.out.println("Hello List World");
    		
  }
  
  
  public void DealCard(Card a_addToHand)
  {
    m_hand.add(a_addToHand);
    
    // Add notification
    Observer m_observer =  model.observer.Observer.getInstance();
    m_observer.cardEvent();
    
  }
  
  public Iterable<Card> GetHand()
  {
    return m_hand;
  }
  
  public void ClearHand()
  {
    m_hand.clear();
  }
  
  public void ShowHand()
  {
    for(Card c : m_hand)
    {
      c.Show(true);
    }
  }
  
  public int CalcScore()
  {
    // the number of scores is dependent on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int cardScores[] = {
        2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
    };
    assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
  
    
    int score = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() != Card.Value.Hidden)
        {
            score += cardScores[c.GetValue().ordinal()];
        }
    }

    if (score > g_maxScore)
    {
        for(Card c : GetHand())
        {
            if (c.GetValue() == Card.Value.Ace && score > g_maxScore)
            {
                score -= 10;
            }
        }
    }

    return score;
  }
  
  public boolean hasOnlyOneAce()
  {
 
    int count = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() == Card.Value.Ace) count++;
    }

    if (count == 1) return true;
    else return false;

  }  
  
}