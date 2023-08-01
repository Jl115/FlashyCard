package FolderController;


import javax.swing.*;
import java.awt.*;



    public class Card {
        protected String name;
        protected String front;
        protected String right;
        protected String wrong1;
        protected String wrong2;

        public Card(String name, String front, String right, String wrong1, String wrong2) {
            this.name = name;
            this.front = front;
            this.right = right;
            this.wrong1 = wrong1;
            this.wrong2 = wrong2;
        }

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

        // Optional: Überschreibe die toString-Methode, um die Karte in Textform darzustellen
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

