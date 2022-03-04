package DeckOfCards;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    ArrayList<Card> deck;

    public CardDeck() {
        this.deck = initialize();
    }

    public ArrayList<Card> initialize() {
        ArrayList<Card> deck = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (int value = 2; value <= 10; value++) {
                deck.add(new NumCard(suit, value));
            }

            for (Face face : Face.values()) {
                deck.add(new FaceCard(suit, face));
            }
        }

        return deck;
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public void sort() {
        CardComparator c = new CardComparator();
        deck.sort(c);
    }

    public void print() {
        for (Card card: deck) {
            System.out.println(card.toString());
        }
    }

    @Override
    public String toString() {
        return "DeckOfCards.CardDeck{" +
                "deck=" + deck +
                '}';
    }

}
