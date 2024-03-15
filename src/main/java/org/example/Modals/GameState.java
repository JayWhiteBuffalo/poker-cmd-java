package org.example.Modals;

import java.util.List;

public class GameState {
    public enum State {
        startRound, betState, midRound, endRound, endGame
    }

    private State state;

    private List<Player> players;

    public GameState(){

    }

    public GameState(List<Player> players) {
        this.players = players;
        // Initialize other state variables
    }


    public boolean isGameOver() {
        if(this.getState() == State.endGame){
            return true;
        } else {
           return false;
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
