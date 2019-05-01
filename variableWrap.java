
/**
 * Write a description of class variableWrap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class variableWrap
{
    // string that stores which cards have been played
    public static volatile String cardPlayed;
    // boolean (unused) which tests for whether the first play has been made
    public static volatile boolean firstCard = false;
    // boolean to set dark mode on and off
    public static volatile boolean darkModeOn = false;
    /**
     * Constructor for objects of class variableWrap
     */
    public variableWrap()
    {
        // initialise instance variables
    }
    
    // method to update the cardPlayed variable with new hand
    public void updateCards(String cards){
        cardPlayed = cards;
    }
    
    //unused (I think)
    public void setFalse(){firstCard = false;}
    public void setTrue(){firstCard = true;}
    public boolean getFirst(){return firstCard;}
    
    // methods to set Dark Mode on and off, and retrieve the var's value
    public void setDarkOff(){darkModeOn = false;}
    public void setDarkOn(){darkModeOn = true;}
    public boolean getDark(){return darkModeOn;}
    
    // method to retrieve the cardPlayed variable
    public String getCardPlayed(){return cardPlayed;}
}
