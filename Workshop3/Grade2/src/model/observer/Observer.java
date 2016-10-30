package model.observer;

import java.util.ArrayList;
import java.util.List;

import controller.PlayGame;

public class Observer {
	
	static List <PlayGame> subscriber = new ArrayList <PlayGame> ();

	public static Observer  me = null;
	
	public static Observer getInstance (){
		
		if (me == null) me = new  Observer();
		return me;
		
	}
	
	public void registerObserver (PlayGame user){
	     subscriber.add (user);
	}
	
	public void cardEvent (){
	
		for (int loopMe=0;subscriber.size()>loopMe;loopMe++){

			PlayGame obj = subscriber.get(loopMe);
			obj.newCardEvent();
						
		}
	}
	

}
