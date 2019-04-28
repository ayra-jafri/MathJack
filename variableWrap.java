import java.util.*;
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

    public static volatile List<Card> selectedOrder;
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
    
    public void addSelected(Card added){selectedOrder.add(added);}
    
    public List<Card> getSelected(){return selectedOrder;}

    
    public String getCardPlayed(){return cardPlayed;}
}
