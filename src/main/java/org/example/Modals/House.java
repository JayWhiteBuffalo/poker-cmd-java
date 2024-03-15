package org.example.Modals;

import java.util.*;

public class House {
    public enum PokerHand {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH
    }
    private PokerHand pokerHand;


    public House() {
    }
    public PokerHand getPokerHands() {
        return pokerHand;
    }
    public void setPokerHand(PokerHand pokerHands) {
        this.pokerHand = pokerHands;
    }

    // Method to evaluate the hand rank of a player
    public static PokerHand checkHand(Player player, ArrayList<Card> hand, GameStats gameStats) {
        // Sort the hand by card rank
        hand.sort(Comparator.comparingInt(Card::getValue));

        // Check for different hand combinations
        if (isFlush(hand) && isStraight(hand)) {
            System.out.println(player.getName() + "'s hand : Straight Flush!");
            player.setHandRank(House.PokerHand.STRAIGHT_FLUSH);
            gameStats.setStraightFlushCount(gameStats.getStraightFlushCount() + 1);
            return House.PokerHand.STRAIGHT_FLUSH;
        } else if (isFlush(hand)) {
            System.out.println(player.getName() + "'s hand : Flush!");
            player.setHandRank(House.PokerHand.FLUSH);
            gameStats.setFlushCount(gameStats.getFlushCount() + 1);
            return House.PokerHand.FLUSH;
        } else if (isStraight(hand)) {
            System.out.println(player.getName() + "'s hand : Straight!");
            player.setHandRank(House.PokerHand.STRAIGHT);
            gameStats.setStraightCount(gameStats.getStraightCount() + 1);
            return House.PokerHand.STRAIGHT;
        } else {
            PokerHand result = getOtherHands(player, hand, gameStats);
            player.setHandRank(result);
            return result;
        }
    }


    // Welcome to Algorithm H311

    //FLUSH : Compare Suits Until either one is a mismatch or all match
    //Time complexity 0(n)
    private static boolean isFlush(ArrayList<Card> hand) {
        // Get the suit of the first card in the hand
        String firstSuit = hand.getFirst().getSuit();
        for (int i = 1; i < hand.size(); i++) {
            if (!hand.get(i).getSuit().equals(firstSuit)) {
                return false;
            }
        }
        return true;
    }

    // STRAIGHT : Sort hand by values, then loop through values to check if they are all consecutive
    //Time complexity O(nlogn)
    private static boolean isStraight(ArrayList<Card> hand) {
        // Sort the hand by value
        hand.sort(Comparator.comparingInt(Card::getValue));
        // Iterate through the hand to check for consecutive ranks
        for (int i = 0; i < hand.size() - 1; i++) {
            int rankValue1 = hand.get(i).getValue();
            int rankValue2 = hand.get(i + 1).getValue();
            // If the difference between ranks is not 1, ranks are not consecutive
            if (rankValue2 - rankValue1 != 1) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand contains pairs and count how many. Return based on Result //
    // UPDATE : Decided to put all other possible outcomes here as the logic would be duplicated if seperate
    private static PokerHand getOtherHands(Player player, ArrayList<Card> hand, GameStats gameStats) {
        // Map to store the count of each rank
        Map<String, Integer> rankCount = new HashMap<>();
        // Iterate through the hand and count occurrences of each rank
        for (Card card : hand) {
            //Get Rank from current card
            String rank = card.getRank();
            //If no occurrence of rank in rankCount, store {rank = 1}
            //If occurrence exists in rankCount, add 1 to that occurrence {existingRank = 2}
            //Store result in rankCount
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        // Count the number of pairs
        int pairsFound = 0;
        boolean isThreeofKind = false;
        boolean isFourofKind = false;

        // Iterate through the rankCount and check count value
        for (int count : rankCount.values()) {
            // If the occurrence has a count of 4, that is 4 of a kind
            if (count == 4) {
                isFourofKind = true;
            } else if (count == 3) {
                isThreeofKind = true;
            } else if (count == 2) {
                pairsFound++;
            }
        }

        // Check for different hand combinations
        if (isFourofKind) {
            System.out.println(player.getName() + "'s hand : FOUR OF A KIND!");
            gameStats.setFourOfAKindCount(gameStats.getFourOfAKindCount() + 1);
            player.setHandRank(PokerHand.FOUR_OF_A_KIND);
            return PokerHand.FOUR_OF_A_KIND;
        } else if (isThreeofKind && pairsFound == 1) {
            System.out.println(player.getName() + "'s hand : FULL HOUSE!");
            gameStats.setFullHouseCount(gameStats.getFullHouseCount() + 1);
            player.setHandRank(PokerHand.FULL_HOUSE);
            return PokerHand.FULL_HOUSE;
        } else if (isThreeofKind) {
            System.out.println(player.getName() + "'s hand : THREE OF A KIND!");
            gameStats.setThreeOfAKindCount(gameStats.getThreeOfAKindCount() + 1);
            player.setHandRank(PokerHand.THREE_OF_A_KIND);
            return PokerHand.THREE_OF_A_KIND;
        } else if (pairsFound == 2) {
            System.out.println(player.getName() + "'s hand : TWO PAIR!");
            gameStats.setTwoPairCount(gameStats.getTwoPairCount() + 1);
            player.setHandRank(PokerHand.TWO_PAIR);
            return PokerHand.TWO_PAIR;
        } else if (pairsFound == 1) {
            System.out.println(player.getName() + "'s hand : ONE PAIR!");
            gameStats.setOnePairCount(gameStats.getOnePairCount() + 1);
            player.setHandRank(PokerHand.ONE_PAIR);
            return PokerHand.ONE_PAIR;
        } else {
            System.out.println(player.getName() + "'s hand : HIGH CARD of " + getHighCard(hand) + " !");
            gameStats.setHighCardCount(gameStats.getHighCardCount() + 1);
            player.setHandRank(PokerHand.HIGH_CARD);
            return PokerHand.HIGH_CARD;
        }
    }

    private static Card getHighCard(ArrayList<Card> hand) {
        hand.sort(Comparator.comparingInt(Card::getValue));
        Card highCard = hand.getLast();
        return hand.getLast();
    }

    // Method to compare the hand ranks of two players and determine the winner
    public static Player determineWinner(Player player1, Player player2, GameStats gameStats) {
        // Evaluate the hand ranks of both players
        System.out.println("Your hand ====> " + player1.getHand());
        checkHand(player1, player1.getHand(), gameStats);
        System.out.println("Bot hand ====> " + player2.getHand());
        checkHand(player2, player2.getHand(), gameStats);

        PokerHand rank1 = player1.getHandRank();
        PokerHand rank2 = player2.getHandRank();

        // Compare the hand ranks and return the winner
        if (rank1.compareTo(rank2) > 0) {
            System.out.println("Player 1 Is winner");
            return player1;
        } else if (rank1.compareTo(rank2) < 0) {
            System.out.println("Player 2 Is winner");
            return player2;
        } else {
            Player result = compareHighCards(player1, player2);
            if(result == player1){
                System.out.println("Player 1 Is winner");
                return result;
            } else {
                System.out.println("Player 2 Is winner");
                return result;
            }
        }
    }

    // Method to Find winning High Card or winning Pair - other scenarios are edge cases and might be added later
    private static Player compareHighCards(Player player1, Player player2) {
        // Sort the hands of both players by card value in descending order
        player1.getHand().sort(Comparator.comparingInt(Card::getValue).reversed());
        player2.getHand().sort(Comparator.comparingInt(Card::getValue).reversed());

        if(player1.getHandRank() == PokerHand.HIGH_CARD && player2.getHandRank() == PokerHand.HIGH_CARD){
            int comparison = Integer.compare((player1.getHand().getFirst().getValue()), (player2.getHand().getFirst().getValue()));
            if (comparison != 0) {
                return comparison > 0 ? player1  : player2;
           }
        } else if (player1.getHandRank() == PokerHand.ONE_PAIR && player2.getHandRank() == PokerHand.ONE_PAIR){
            // Get the value of the pair for each player
            int pairValuePlayer1 = getPairValue(player1.getHand());
            int pairValuePlayer2 = getPairValue(player2.getHand());

            // Compare the values of the pairs
            if (pairValuePlayer1 != pairValuePlayer2) {
                return pairValuePlayer1 > pairValuePlayer2 ? player1 : player2;
            }
        }
        // If all high cards are equal, it's a tie
        System.out.println("It's a draw!");
        return null;
    }

    private static int getPairValue(List<Card> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                return hand.get(i).getValue();
            }
        }
        return -1;
    }
}