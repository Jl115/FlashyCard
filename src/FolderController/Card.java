package FolderController;

public class Card {
    // Fields to store the properties of the card
    protected String name;
    protected String front;
    protected String right;
    protected String wrong1;
    protected String wrong2;

    // Constructor to create a card with given values
    public Card(String name, String front, String right, String wrong1, String wrong2) {
        this.name = name;
        this.front = front;
        this.right = right;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    // Getter methods to access the properties of the card
    public String getName() {
        return name;
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

    // Optional: Override the toString method to represent the card in text form
    // Useful for debugging and logging
    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", front='" + front + '\'' +
                ", right='" + right + '\'' +
                ", wrong1='" + wrong1 + '\'' +
                ", wrong2='" + wrong2 + '\'' +
                '}';
    }
}
