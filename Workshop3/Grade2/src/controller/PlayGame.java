package controller;

import view.IView;

import model.Game;
import model.observer.Observer;

public class PlayGame{
	
	private IView a_view;
	private Game a_game;
	
	private boolean observerRegistered = false;
	
  public boolean Play(Game myGame, IView myView) {
	  
	  a_game = myGame;
	  
	  if (observerRegistered == false){
	     Observer a_obs =  model.observer.Observer.getInstance();
	     a_obs.registerObserver(this);
	     observerRegistered = true;
	  }
	  
      a_view = myView;
	  
    a_view.DisplayWelcomeMessage();
    
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
        
    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());

    }
        
    a_view.DisplaySelectMessage();


    int input = a_view.GetInput();
    
    
    // replace 'p,h,s,q' 
    
    if (a_view.isNewGame(input) == true)
    {
        a_game.NewGame();
    }
    else if (a_view.isHit(input) == true)
    {
        a_game.Hit();        
    }
    else if (a_view.isStand(input) == true)
    {
        a_game.Stand();
    }
    else if (a_view.isQuit(input) == true){
    	return false;
    }

    return true;
    
  }

  public void newCardEvent() {
	    
	  a_view.DisplayWelcomeMessage();

	  a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
	  a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
	  
	  // ADD fancy delay
	  
	  try {
		Thread.sleep (1000L);
	  } catch (InterruptedException e) {
		e.printStackTrace();
	  }
	  
      a_view.DisplaySelectMessage();

	  
  }
}