package org.example.Modals;

public class GameStats {
    private int roundsPlayed;
    private int wins;
    private int losses;
    private int royalFlushCount;
    private int straightFlushCount;
    private int fourOfAKindCount;
    private int flushCount;
    private  int straightCount;
    private int fullHouseCount;
    private int threeOfAKindCount;
    private int twoPairCount;
    private int onePairCount;
    private int highCardCount;

    public GameStats() {

    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(int roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getRoyalFlushCount() {
        return royalFlushCount;
    }

    public void setRoyalFlushCount(int royalFlushCount) {
        this.royalFlushCount = royalFlushCount;
    }

    public int getStraightFlushCount() {
        return straightFlushCount;
    }

    public void setStraightFlushCount(int straightFlushCount) {
        this.straightFlushCount = straightFlushCount;
    }

    public int getFourOfAKindCount() {
        return fourOfAKindCount;
    }

    public void setFourOfAKindCount(int fourOfAKindCount) {
        this.fourOfAKindCount = fourOfAKindCount;
    }

    public int getFlushCount(){
        return flushCount;
    }

    public void setFlushCount(int flushCount){
        this.flushCount = flushCount;
    }

    public int getStraightCount() {
        return straightCount;
    }

    public void setStraightCount(int straightCount) {
        this.straightCount = straightCount;
    }

    public int getFullHouseCount() {
        return fullHouseCount;
    }

    public void setFullHouseCount(int fullHouseCount) {
        this.fullHouseCount = fullHouseCount;
    }

    public int getThreeOfAKindCount() {
        return threeOfAKindCount;
    }

    public void setThreeOfAKindCount(int threeOfAKindCount) {
        this.threeOfAKindCount = threeOfAKindCount;
    }

    public int getTwoPairCount() {
        return twoPairCount;
    }

    public void setTwoPairCount(int twoPairCount) {
        this.twoPairCount = twoPairCount;
    }

    public int getOnePairCount() {
        return onePairCount;
    }

    public void setOnePairCount(int onePairCount) {
        this.onePairCount = onePairCount;
    }

    public int getHighCardCount() {
        return highCardCount;
    }

    public void setHighCardCount(int highCardCount) {
        this.highCardCount = highCardCount;
    }

    // Methods to update statistics
    public void incrementRoundsPlayed() {
        roundsPlayed++;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
    }

    public void incrementRoyalFlushCount() {
        royalFlushCount++;
    }

    public void incrementStraightFlushCount() {
        straightFlushCount++;
    }

    public void incrementFourOfAKindCount(){
        fourOfAKindCount++;
    }

    public void incrementFlushCount(){
        flushCount++;
    }

    public void incrementStraightCount(){
        straightCount++;
    }

    public void incrementFullHouseCount(){
        fullHouseCount++;
    }

    public void incrementThreeOfAKindCount(){
        threeOfAKindCount++;
    }

    public void incrementTwoPairCount(){
        twoPairCount++;
    }

    public void incrementOnePairCount(){
        onePairCount++;
    }

    public void incrementHighCardCount(){
        highCardCount++;
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "roundsPlayed=" + roundsPlayed + "\n" +
                ", wins=" + wins + "\n" +
                ", losses=" + losses + "\n" +
                ", royalFlushCount=" + royalFlushCount + "\n" +
                ", straightFlushCount=" + straightFlushCount + "\n" +
                ", fourOfAKindCount=" + fourOfAKindCount + "\n" +
                ", flushCount=" + flushCount + "\n" +
                ", straightCount=" + straightCount + "\n" +
                ", fullHouseCount=" + fullHouseCount + "\n" +
                ", threeOfAKindCount=" + threeOfAKindCount + "\n" +
                ", twoPairCount=" + twoPairCount + "\n" +
                ", onePairCount=" + onePairCount + "\n" +
                ", highCardCount=" + highCardCount + "\n" +
                '}';
    }
}
