package DeckOfCards;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card cardOne, Card cardTwo) {
        if (cardOne.suit != cardTwo.suit) {
            if (cardOne.suit.ordinal() > cardTwo.suit.ordinal()) {
                return 1;
            }
            else if(cardTwo.suit.ordinal() > cardOne.suit.ordinal()) {
                return -1;

            }

        }

        else if (cardOne instanceof FaceCard faceCardOne && cardTwo instanceof FaceCard faceCardTwo) {
            if (faceCardOne.face.ordinal() > faceCardTwo.face.ordinal()) {
                return 1;
            }
            else if(faceCardTwo.face.ordinal() > faceCardOne.face.ordinal()) {
                return -1;
            }

        }

        else {
            if (cardOne.value > cardTwo.value || cardOne instanceof FaceCard) {
                return 1;
            }
            else if(cardTwo.value > cardOne.value || cardTwo instanceof FaceCard) {
                return -1;
            }
        }

        return 0;
    }
}
