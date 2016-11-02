import controller.YachtClubController;
import model.MemberFacade;
import model.MemberFacadeInterface;
import view.UI;
import view.UIInterface;

public class YachtClubMain {

	public static void main(String[] args) {

		String usageString = "\nUsage : java YachtClubMain --file <storage path>";
		
		boolean fileFound = false;
		String fileString = "";
				
		if (args.length>0){
			
			try {
			
			    for (int loopMe=0;args.length>loopMe;){
				
			    	if (args[loopMe].compareTo("--file") == 0){
			    	    fileFound = true;
			    		fileString = args[loopMe+1];
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
		
		if (fileFound == false){
			System.out.print (usageString);
			// end the program
			return;			
		}
		
		// Create the View (UI)
		
		UIInterface ui = new UI ();	
		
		// Create the Model (CacheStorage)
		
		MemberFacadeInterface cache = new MemberFacade (fileString);
		
		// Create the Controller (YachtClubRegister)
		
        YachtClubController yachtClub = new YachtClubController ();
        yachtClub.startProg (ui,cache);
		
	}

}

