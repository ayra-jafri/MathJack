
/**
 * Write a description of class variableWrap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class variableWrap
{
    // instance variables - replace the example below with your own
    public static volatile String cardPlayed;
    public static volatile boolean firstCard = false;
    public static volatile boolean darkModeOn = false;
    /**
     * Constructor for objects of class variableWrap
     */
    public variableWrap()
    {
        // initialise instance variables
    }
    public void updateCards(String cards){
        cardPlayed = cards;
    }
    
    //unused (I think)
    public void setFalse(){firstCard = false;}
    public void setTrue(){firstCard = true;}
    public boolean getFirst(){return firstCard;}
    
    public void setDarkOff(){darkModeOn = false;}
    public void setDarkOn(){darkModeOn = true;}
    public boolean getDark(){return darkModeOn;}
    
    public String getCardPlayed(){return cardPlayed;}
}
