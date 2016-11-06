

import model.Game;
import model.rules.AbstractFactoryInterface;
import model.rules.GameRuleSetInterface;
import model.rules.RuleSet1Factory;
import model.rules.RuleSet2Factory;
import model.rules.RuleSet3Factory;
import view.*;
import controller.*;

public class Program
{

  public static void main(String[] args)
  {

	  String usageString = "java Program --ruleset <number>";
	  
	  boolean rulesSetFound = false;
	  int rulesSet = 1; // default

	if (args.length>0){
			
			try {
			
			    for (int loopMe=0;args.length>loopMe;){
				
			    	if (args[loopMe].compareTo("--ruleset") == 0){
			    		rulesSetFound = true;
			    	
			    		try {
			    			rulesSet = Integer.parseInt(args[loopMe+1]);
			    		}
			    		catch (NumberFormatException e){
							System.out.print (usageString);
							e.printStackTrace();
						    // end the program
							return;			    			
			    		}
			    		    
			    		    
			    		loopMe = loopMe+2;
			    	}
			    	else { //unknown parameter
			    		System.out.print("\nIllegal parameter : " +args[loopMe]);
			    	    System.out.print(usageString);
			    	    
			    	    // end the program
			    	    return;
			    	}
			    	
			    }
			
			}
			catch (IndexOutOfBoundsException e){
				System.out.print (usageString);
				e.printStackTrace();
			    // end the program
				return;
			}
		}
		else{
			System.out.print (usageString);
			// end the program
			return;
		}
		
		
	  
	  AbstractFactoryInterface gameRules = null;  
	  GameRuleSetInterface rules = null;
	  	  	
	  if (rulesSet == 1){
		  gameRules = new RuleSet1Factory ();
		  rules = gameRules.createRuleSet();
	  }
	  else if (rulesSet == 2){
		  gameRules = new RuleSet2Factory ();
		  rules = gameRules.createRuleSet();
	  }
	  else if (rulesSet == 3){
		  gameRules = new RuleSet3Factory ();
		  rules = gameRules.createRuleSet();
	  }
	  else rulesSet = 1; // default
	  
	  Game g;
	  if (rules != null){
		  		  
		  g = new Game (rules);
		  
	  }
	  else g = new Game();

	  IView v = new SimpleView(); //new SwedishView();
      PlayGame ctrl = new PlayGame();
    
      while (ctrl.Play(g, v));
      
  }
}