package DeckOfCards;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    ArrayList<Card> deck;
    ArrayList<Card[]> hands;

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

    public void splitIntoHands() {
        ArrayList<Card[]> hands = new ArrayList<>();
        this.shuffle();
        for (int i = 0; i < deck.size()/4; i++) {
            Card[] hand = new Card[4];
            for (int c = 0; c < 4; c++) {
                hand[c] = deck.get(i * c);
            }
            hands.add(hand);
        }

        this.hands = hands;
    }

    public void printHands() {
        for (int i = 0; i < hands.size(); i++) {
            System.out.println("Hand #" + i);

            for (Card card : hands.get(i)) {
                System.out.println(card.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "CardDeck{" +
                "deck=" + deck +
                ", hands=" + hands +
                '}';
    }
}
