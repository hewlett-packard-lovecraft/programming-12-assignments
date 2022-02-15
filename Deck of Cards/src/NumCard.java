public class NumCard extends Card implements Comparable<Card> {
    public NumCard(Suit suit, int value) {
        super(suit, value);
    }

    @Override
    public int compareTo(Card o) {
        if (this.value > o.value) {
            return -1;
        }
        else if(o.value > this.value) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "NumCard{" +
            "suit=" + suit + "," +
            "number=" + value +
            '}';
    }
}
