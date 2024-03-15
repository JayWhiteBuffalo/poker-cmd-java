package org.example.Modals;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private House.PokerHand handRank;

    public Player(){

    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        this.handRank = null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public void setHand(List<Card> hand) {
        this.hand = (ArrayList<Card>) hand;
    }

    public House.PokerHand getHandRank() {
        return handRank;
    }

    public void setHandRank(House.PokerHand handRank) {
        this.handRank = handRank;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                '}';
    }
}
