package view;

import model.Card;

public class SwedishView implements IView 
    {
        public void DisplayWelcomeMessage()
        {
         
            for(int i = 0; i < 50; i++) {System.out.print("\n");};

            System.out.println("Hej Black Jack V�rlden");
            System.out.println("----------------------");
            System.out.println("Skriv 'j' f�r att Spela, 'n' f�r nytt kort, 's' f�r att stanna 'a' f�r att avsluta\n");
        }
        
        public int GetInput()
        {
          try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
              c = System.in.read();
            }
            return c;
          } catch (java.io.IOException e) {
            System.out.println("" + e);
            return 0;
          }
        }
        
        public void DisplayCard(Card a_card)
        {
            if (a_card.GetColor() == Card.Color.Hidden)
            {
                System.out.println("Dolt Kort");
            }
            else
            {
                String colors[] = 
                    { "Hj�rter", "Spader", "Ruter", "Kl�ver" };
                String values[] =  
                    { "tv�", "tre", "fyra", "fem", "sex", "sju", "�tta", "nio", "tio", "knekt", "dam", "kung", "ess" };
                System.out.println("" + colors[a_card.GetColor().ordinal()] + " " + values[a_card.GetValue().ordinal()]);
            }
        }
        public void DisplayPlayerHand(Iterable<Card> a_hand, int a_score)
        {
            DisplayHand("Spelare", a_hand, a_score);
        }
        public void DisplayDealerHand(Iterable<Card> a_hand, int a_score)
        {
            DisplayHand("Croupier", a_hand, a_score);
        }
        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("Slut: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Croupiern Vann!");
            }
            else
            {
                System.out.println("Du vann!");
            }
        }

        private void DisplayHand(String a_name, Iterable<Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Har: " + a_score);
            for(Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Po�ng: " + a_score);
            System.out.println("");
        }
        

        public boolean isNewGame (int value){
        	return value == 'j';
        }
        public boolean isHit (int value){
        	return value == 'n';        	
        }
        public boolean isStand (int value){
        	return value == 's';
        }
        public boolean isQuit (int value){
        	return value == 'a';
        }
        
        public void DisplaySelectMessage (){
        	System.out.print("\nV�lj >> ");
        }
        
}