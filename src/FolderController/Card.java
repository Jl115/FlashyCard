package FolderController;



public class Card {
    protected String cardName;
    protected String front;
    protected String right;
    protected String wrong1;
    protected String wrong2;

    public Card(String cardName, String front, String right, String wrong1, String wrong2) {
        this.cardName = cardName;
        this.front = front;
        this.right = right;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }



    public String getCardName() {
        return cardName;
    }

    public String getFront() {
        return front;
    }

    public String getRight() {
        return right;
    }

    public String getWrong1() {
        return wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }
}
