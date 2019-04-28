import java.util.List;
import java.util.ArrayList;

/**
 * The ThirteensBoard class represents the board in a game of Thirteens.
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
     * Creates a new <code>ThirteensBoard</code> instance.
     */
     public mathBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
        varWrap = new variableWrap();
    }
    
    /**
     * Determines if the selected cards form a valid group for removal.
     * In Thirteens, the legal groups are (1) a pair of non-face cards
     * whose values add to 13, and (2) a king.
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
     * In Thirteens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 13, or (2) a king.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        List<Integer> cIndexes = cardIndexes();
        return findPairSum(cIndexes).size() > 0;
             /*|| findKing(cIndexes).size() > 0*/
    }

    /**
     * Look for an trio sum in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 13-pair.
     * @return a list of the indexes of an 13-pair, if an 13-pair was found;
     *         an empty list, if an 13-pair was not found.
     */
    private List<Integer> findPairSum(List<Card> selectedOrder) {
        List<Integer> foundIndexes = new ArrayList<Integer>();
        
        for (int sk1 = 0; sk1 < selectedOrder.size(); sk1++) {
            int k1 = selectedOrder.get(sk1).pointValue();
            for (int sk2 = sk1 + 1; sk2 < selectedOrder.size(); sk2++) {
                int k2 = selectedOrder.get(sk2).pointValue();
                for(int sk3 = sk2 + 1; sk3 < selectedOrder.size(); sk3++){
                int k3 = selectedOrder.get(sk3).pointValue();
                if(((cardAt(k1).rank().equals("knight")) 
                || (cardAt(k2).rank().equals("knight"))
                ||(cardAt(k3).rank().equals("knight"))) /*&&
                ((cardAt(k1).pointValue() == 14) 
                || (cardAt(k2).pointValue() == 14) 
                || (cardAt(k3).pointValue() == 14))*/){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        
                        varWrap.updateCards("You had a knight in your hand.");
                        return foundIndexes;
                    
                }
                
                if ((cardAt(k1).pointValue() != 0 
                && cardAt(k2).pointValue() != 0 
                && cardAt(k3).pointValue() != 0)) {
                    if((cardAt(k1).pointValue() + cardAt(k2).pointValue() == cardAt(k3).pointValue())
                    || (cardAt(k2).pointValue() + cardAt(k1).pointValue() == cardAt(k3).pointValue())){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You did " + cardAt(k1).pointValue() + " + " + cardAt(k2).pointValue() + " = " + cardAt(k3).pointValue() + ".");
                        return foundIndexes;
                    }
                    
                    if(cardAt(k1).pointValue() - cardAt(k2).pointValue() == cardAt(k3).pointValue()){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You did " + cardAt(k1).pointValue() + " - " + cardAt(k2).pointValue() + " = " + cardAt(k3).pointValue() + ".");
                        return foundIndexes;
                    }
                    
                    if(cardAt(k1).pointValue() * cardAt(k2).pointValue() == cardAt(k3).pointValue()){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You did " + cardAt(k1).pointValue() + " * " + cardAt(k2).pointValue() + " = " + cardAt(k3).pointValue() + ".");
                        return foundIndexes;
                    }
                    
                    if((cardAt(k1).pointValue()) / (cardAt(k2).pointValue()) == cardAt(k3).pointValue()){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        varWrap.updateCards("You did " + cardAt(k1).pointValue() + " / " + cardAt(k2).pointValue() + " = " + cardAt(k3).pointValue() + ".");
                        return foundIndexes;
                    }
                    /*
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
                        return foundIndexes;
                    }*/
                }
                
                if((cardAt(k1).pointValue() == 0 && cardAt(k2).pointValue() == 0 && (cardAt(k3).pointValue() != 0)) 
                || (cardAt(k2).pointValue() == 0 && cardAt(k3).pointValue() == 0 && (cardAt(k1).pointValue() != 0))
                ||(cardAt(k1).pointValue() == 0 && cardAt(k3).pointValue() == 0 && (cardAt(k2).pointValue() != 0))){
                    /*if(((cardAt(k1).pointValue() + cardAt(k2).pointValue() == cardAt(k3).pointValue())
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
                        return foundIndexes;
                    }*/
                    if(cardAt(k1).pointValue() != 0)
                        if((cardAt(k2).pointValue() % cardAt(k1).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k2).pointValue() == 0)){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        return foundIndexes;
                       }
                    if(cardAt(k2).pointValue() != 0)
                        if((cardAt(k3).pointValue() % cardAt(k2).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k2).pointValue() == 0)){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
                        return foundIndexes;
                       }
                    if(cardAt(k3).pointValue() != 0)
                        if((cardAt(k1).pointValue() % cardAt(k3).pointValue() == 0)
                        || (cardAt(k3).pointValue() / cardAt(k1).pointValue() == 0)){
                        foundIndexes.add(new Integer(k1));
                        foundIndexes.add(new Integer(k2));
                        foundIndexes.add(new Integer(k3));
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
                    /*if(((cardAt(k1).pointValue() + cardAt(k2).pointValue() == cardAt(k3).pointValue())
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
                        return foundIndexes;
                    }*/
                    
                    
                    if(cardAt(k1).pointValue() == 0){
                       if((cardAt(k1).pointValue() % cardAt(k2).pointValue() == 0)
                        || (cardAt(k1).pointValue() % cardAt(k3).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k2).pointValue() == 0)
                        || (cardAt(k1).pointValue() / cardAt(k3).pointValue() == 0)){
                            foundIndexes.add(new Integer(k1));
                            foundIndexes.add(new Integer(k2));
                            foundIndexes.add(new Integer(k3));
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
    /*
    /**
     * Look for a king in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a king.
     * @return a list of the index of a king, if a king was found;
     *         an empty list, if a king was not found.
     
    private List<Integer> findKing(List<Integer> selectedCards) {
        List<Integer> foundIndexes = new ArrayList<Integer>();
        for (Integer kObj : selectedCards) {
            int k = kObj.intValue();
            if (cardAt(k).rank().equals("king")) {
                foundIndexes.add(new Integer(k));
                return foundIndexes;
            }
        }
        return foundIndexes;
    }
    */
    /**
     * Looks for a legal play on the board.  If one is found, it plays it.
     * @return true if a legal play was found (and made); false othewise.
     */
    public boolean playIfPossible() {
        return playPairSumIfPossible(); /*|| playKingIfPossible();*/
    }

    /**
     * Looks for a pair of non-face cards whose values sum to 13.
     * If found, replace them with the next two cards in the deck.
     * The simulation of this game uses this method.
     * @return true if an 13-pair play was found (and made); false othewise.
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
    
    /*
    /**
     * Looks for a King.
     * If found, replace it with the next card in the deck.
     * The simulation of this game uses this method.
     * @return true if a king play was found (and made); false othewise.
    
    private boolean playKingIfPossible() {
        List<Integer> foundIndexes = cardIndexes();
        List<Integer> cardsToReplace = findKing(foundIndexes);
        if (cardsToReplace.size() > 0) {
            replaceSelectedCards(cardsToReplace);
            if (I_AM_DEBUGGING) {
                System.out.println("King removed.\n");
            }
            return true;
        } else {
            return false;
        }
    }
    */
}
