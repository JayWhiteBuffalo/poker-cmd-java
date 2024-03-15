package org.example.Modals;


import java.util.ArrayList;

public class Bot extends Player{
    public enum Level {
        EASY, MEDIUM, HARD
    }
    private Level level;

    public Bot() {
    }

    public Bot(String name, ArrayList<Card> hand, Level level) {
        super(name, hand);
        this.level = level;
    }
}
