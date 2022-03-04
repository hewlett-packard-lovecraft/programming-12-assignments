package DeckOfCards;

public class FaceCard extends Card implements Comparable<Card>  {
    Face face;

    public FaceCard(Suit suit, Face face) {
        super(suit, 52);
        // value of a face card is the highest possible so that face card of same suit is always > num card
        this.face = face;
    }

    @Override
    public int compareTo(Card o) {
        if (this.suit != o.suit) {
            if (this.suit.ordinal() > o.suit.ordinal()) {
                return -1;
            } else if (o.suit.ordinal() > this.suit.ordinal()) {
                return +1;
            }
        } else if (o instanceof FaceCard faceCardO) {
            if (this.face.ordinal() > faceCardO.face.ordinal()) {
                return -1;
            } else if (faceCardO.face.ordinal() > this.face.ordinal()) {
                return 1;
            }
        } else {
            if (this.value > o.value) {
                return -1;
            } else if (o.value > this.value) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "DeckOfCards.FaceCard{" +
                "suit=" + suit + "," +
                "face=" + face +
                '}';
    }
}
