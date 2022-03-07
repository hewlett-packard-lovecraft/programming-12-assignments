package DeckOfCards;

public class Main {
    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        System.out.println("Printing deck");
        cardDeck.print();
        System.out.println("Shuffle deck");
        cardDeck.shuffle();
        cardDeck.print();
        System.out.println("Sort deck");
        cardDeck.sort();
        cardDeck.print();
        cardDeck.splitIntoHands();
        cardDeck.printHands();

    }
}
