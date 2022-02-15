public class FaceCard extends Card implements Comparable<Card>  {
    Face face;

    public FaceCard(Suit suit, Face face) {
        super(suit, 52);
        // value of a face card is the highest possible so that face card is always > num card
        this.face = face;
    }

    @Override
    public int compareTo(Card o) {
        return 0;
    }

    @Override
    public String toString() {
        return "FaceCard{" +
                "suit=" + suit + "," +
                "face=" + face +
                '}';
    }
}
