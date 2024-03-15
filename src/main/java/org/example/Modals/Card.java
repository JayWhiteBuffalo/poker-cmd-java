package org.example.Modals;
import java.lang.Character;
import java.lang.reflect.Array;
import java.util.Objects;


public class Card {

    public String rank;
    public String suit;
    public int value;
    private String color;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.color = getColor(suit);
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private String getColor(String suit) {
        return (suit.equals("♠") || suit.equals("♣")) ? "Black" : "Red";
    }

    @Override
    public String toString() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLACK = "\u001B[30m";

        String colorCode = getColor(suit).equals("Red") ? ANSI_RED : ANSI_BLACK;
        return colorCode + rank + suit + ANSI_RESET;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getValue() == card.getValue() && Objects.equals(getRank(), card.getRank()) && Objects.equals(getSuit(), card.getSuit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getSuit(), getValue());
    }

}
