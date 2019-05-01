import java.util.List;
import java.util.ArrayList;

/**
 * The mathBoard class represents the board in a game of Thirteens.
 */
public class mathBoard extends Board {

    variableWrap varWrap;
    
    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 12;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
        {"joker", "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "knight"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
        {"spades", "hearts", "diamonds", "clubs"};

        
    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;

    private String cardsPlayed;
    
    /**
     * Creates a new <code>mathBoard</code> instance.
     */
     public mathBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
        varWrap = new variableWrap();
    }
    
    /**
     * Determines if the selected cards form a valid group for removal.
     * In MathJack, legal cards are a trio of cards that are mathemtatically related to each other.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        if (selectedCards.size() == 3) {
            return findPairSum(selectedCards).size() > 0;
        } else {
            return false;
        }
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In MathJack, legal cards are a trio of cards that are mathemtatically related to each other.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        List<Integer> cIndexes = cardIndexes();
        return findPairSum(cIndexes).size() > 0;
    }

    /**
     * Look for an trio sum in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a legal pair.
     * @return a list of the indexes of an mathJack-pair, if a mathJack-pair was found;
     *         an empty list, if a pair was not found.
     */
    private List<Integer> findPairSum(List<Integer> selectedCards) {
        List<Integer> foundIndexes = new ArrayList<Integer>();
        for (int sk1 = 0; sk1 < selectedCards.size(); sk1++) {
            int k1 = selectedCards.get(sk1).intValue();
            for (int sk2 = sk1 + 1; sk2 < selectedCards.size(); sk2++) {
                int k2 = selectedCards.get(sk2).intValue();
                for(int sk3 = sk2 + 1; sk3 < selectedCards.size(); sk3++){
                int k3 = selectedCards.get(sk3).intValue();
                if((cardAt(k1).rank().equals("knight")) 
                || (cardAt(k2).rank().equals("knight"))
                ||(cardAt(k3).rank().equals("knight"))){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.setTrue();
                        varWrap.updateCards("You had a knight in your hand.");
                        return foundIndexes;
                    
                }
                
                if ((cardAt(k1).pointValue() != 0 
                && cardAt(k2).pointValue() != 0 
                && cardAt(k3).pointValue() != 0)) {
                    if((cardAt(k1).pointValue() + cardAt(k2).pointValue() == cardAt(k3).pointValue()) 
                        ||(cardAt(k1).pointValue() + cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() + cardAt(k3).pointValue() == cardAt(k1).pointValue()
                        
                        ||cardAt(k1).pointValue() - cardAt(k2).pointValue() == cardAt(k3).pointValue()) 
                        ||(cardAt(k1).pointValue() - cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() - cardAt(k3).pointValue() == cardAt(k1).pointValue())
    
                        ||cardAt(k1).pointValue() * cardAt(k2).pointValue() == cardAt(k3).pointValue() 
                        ||(cardAt(k1).pointValue() * cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() * cardAt(k3).pointValue() == cardAt(k1).pointValue())
                        
                        ||cardAt(k1).pointValue() / cardAt(k2).pointValue() == cardAt(k3).pointValue() 
                        ||(cardAt(k1).pointValue() / cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() / cardAt(k3).pointValue() == cardAt(k1).pointValue()
                        )
                        
                        ||cardAt(k1).pointValue() % cardAt(k2).pointValue() == cardAt(k3).pointValue() 
                        ||(cardAt(k1).pointValue() % cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() % cardAt(k3).pointValue() == cardAt(k1).pointValue()
                        )
                        ){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                        return foundIndexes;
                    }
                }
                
                if((cardAt(k1).pointValue() == 0 && cardAt(k2).pointValue() == 0 && (cardAt(k3).pointValue() != 0)) 
                || (cardAt(k2).pointValue() == 0 && cardAt(k3).pointValue() == 0 && (cardAt(k1).pointValue() != 0))
                ||(cardAt(k1).pointValue() == 0 && cardAt(k3).pointValue() == 0 && (cardAt(k2).pointValue() != 0))){
                    if(((cardAt(k1).pointValue() + cardAt(k2).pointValue() == cardAt(k3).pointValue())
                        ||cardAt(k1).pointValue() + cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() + cardAt(k3).pointValue() == cardAt(k1).pointValue()
                        
                        ||cardAt(k1).pointValue() - cardAt(k2).pointValue() == cardAt(k3).pointValue()) 
                        ||(cardAt(k1).pointValue() - cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() - cardAt(k3).pointValue() == cardAt(k1).pointValue())
    
                        ||cardAt(k1).pointValue() * cardAt(k2).pointValue() == cardAt(k3).pointValue() 
                        ||(cardAt(k1).pointValue() * cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() * cardAt(k3).pointValue() == cardAt(k1).pointValue())
                        ){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                        return foundIndexes;
                    }
                    if(cardAt(k1).pointValue() != 0)
                        if((cardAt(k2).pointValue() % cardAt(k1).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k2).pointValue() == 0)){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                        return foundIndexes;
                       }
                    if(cardAt(k2).pointValue() != 0)
                        if((cardAt(k3).pointValue() % cardAt(k2).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k2).pointValue() == 0)){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                        return foundIndexes;
                       }
                    if(cardAt(k3).pointValue() != 0)
                        if((cardAt(k1).pointValue() % cardAt(k3).pointValue() == 0)
                        || (cardAt(k3).pointValue() / cardAt(k1).pointValue() == 0)){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                        return foundIndexes;
                       }
                }
                
                if(cardAt(k3).pointValue() == 0 
                && cardAt(k2).pointValue() == 0
                && cardAt(k1).pointValue() == 0){
                    varWrap.updateCards("Nope.");
                    return foundIndexes;
                }
                
                if(((cardAt(k1).pointValue() == 0) && (cardAt(k2).pointValue() != 0) && (cardAt(k3).pointValue() != 0)) 
                ||((cardAt(k3).pointValue() == 0) && (cardAt(k1).pointValue() != 0) && (cardAt(k2).pointValue() != 0))
                ||((cardAt(k2).pointValue() == 0) && (cardAt(k3).pointValue() != 0) && (cardAt(k1).pointValue() != 0))){
                    if(((cardAt(k1).pointValue() + cardAt(k2).pointValue() == cardAt(k3).pointValue())
                        ||cardAt(k1).pointValue() + cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() + cardAt(k3).pointValue() == cardAt(k1).pointValue()
                        
                        ||cardAt(k1).pointValue() - cardAt(k2).pointValue() == cardAt(k3).pointValue()) 
                        ||(cardAt(k1).pointValue() - cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() - cardAt(k3).pointValue() == cardAt(k1).pointValue())
    
                        ||cardAt(k1).pointValue() * cardAt(k2).pointValue() == cardAt(k3).pointValue() 
                        ||(cardAt(k1).pointValue() * cardAt(k3).pointValue() == cardAt(k2).pointValue())
                        ||(cardAt(k2).pointValue() * cardAt(k3).pointValue() == cardAt(k1).pointValue())
                        ){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                        return foundIndexes;
                    }
                    if(cardAt(k1).pointValue() == 0){
                       if((cardAt(k1).pointValue() % cardAt(k2).pointValue() == 0)
                        || (cardAt(k1).pointValue() % cardAt(k3).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k2).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k3).pointValue() == 0)){
                            foundIndexes.add(new Integer(k1));
                            foundIndexes.add(new Integer(k2));
                            foundIndexes.add(new Integer(k3));
                            varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                            return foundIndexes;
                       }
                    }
                    if(cardAt(k2).pointValue() == 0){
                       if((cardAt(k2).pointValue() % cardAt(k1).pointValue() == 0)
                        || (cardAt(k2).pointValue() % cardAt(k3).pointValue() == 0)
                        || (cardAt(k2).pointValue() / cardAt(k1).pointValue() == 0)
                        || (cardAt(k2).pointValue() / cardAt(k3).pointValue() == 0)){
                            foundIndexes.add(new Integer(k1));
                            foundIndexes.add(new Integer(k2));
                            foundIndexes.add(new Integer(k3));
                            varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                            return foundIndexes;
                       }
                    }
                    if(cardAt(k3).pointValue() == 0){
                        if((cardAt(k3).pointValue() % cardAt(k2).pointValue() == 0)
                        || (cardAt(k3).pointValue() % cardAt(k1).pointValue() == 0)
                        || (cardAt(k3).pointValue() / cardAt(k2).pointValue() == 0)
                        || (cardAt(k3).pointValue() / cardAt(k1).pointValue() == 0)){
                            foundIndexes.add(new Integer(k1));
                            foundIndexes.add(new Integer(k2));
                            foundIndexes.add(new Integer(k3));
                            varWrap.updateCards("You played a " + cardAt(k1).pointValue() + ", " + cardAt(k2).pointValue() + ", and a " + cardAt(k3).pointValue()+ ".");
                            return foundIndexes;
                       }
                    }
                }
               }
            }
        }
        varWrap.updateCards("Nope.");
        return foundIndexes;
    }
  
    /**
     * Looks for a legal play on the board.  If one is found, it plays it.
     * @return true if a legal play was found (and made); false othewise.
     */
    public boolean playIfPossible() {
        return playPairSumIfPossible();
    }

    /**
     * Looks for a pair of non-face cards whose values are mathematically related.
     * If found, replace them with the next three cards in the deck.
     * The simulation of this game uses this method.
     * @return true if a mathJack-pair play was found (and made); false othewise.
     */
    private boolean playPairSumIfPossible() {
        List<Integer> foundIndexes = cardIndexes();
        List<Integer> cardsToReplace = findPairSum(foundIndexes);
        if (cardsToReplace.size() > 0) {
            replaceSelectedCards(cardsToReplace);
            if (I_AM_DEBUGGING) {
                System.out.println("Trio removed.\n");
            }
            return true;
        } else {
            return false;
        }
    }
   
}
