package org.example;

import org.example.Modals.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;

public class Main {

    public static String[] suits = {"♠", "♥", "♣", "♦"};
    public static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    static boolean isUserTurn = true;
    // Call buildDeck method to build the deck
    public static Deck deck = buildDeck();
    public static int rounds = 1;


    public static void main(String[] args) throws IOException {
        //Instantiate Game State
        GameState gameState = new GameState();
        GameStats gameStats = new GameStats();

        //Set up Game Properties
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setName("You");
        Player bot = new Player();
        bot.setName("Bot");
        players.add(player1);
        players.add(bot);

        gameState.setState(GameState.State.startRound);

        shuffleDeck(deck);
        shuffleDeck(deck);
        // Deal cards to players
        player1.setHand(dealHand(deck));
        bot.setHand(dealHand(deck));

        //Continue to run till game is over
      while (!gameState.isGameOver()) {

          for (int i = 0; i < rounds; i++) {
              gameState.setState(GameState.State.midRound);

              startRound(player1, bot, gameStats);

              // Ask the user if they want to play another round
              System.out.println("Do you want to play another round? (Y/N)");
              Scanner scanner = new Scanner(System.in);
              String input = scanner.nextLine().trim().toLowerCase();
              if (input.equals("n")) {
                  gameState.setState(GameState.State.endGame);
              } else {
                  // Increment the round counter
                  rounds++;
              }
          }

          if (gameState.isGameOver()){
              //Create Data File
              logGameData(gameStats);
              System.out.println("<==================>   Thanks for playing!   <==================>");
              System.out.println(gameStats);
              //Display data
          }
      }
    }


    public static Deck buildDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();
        for (String suit : suits) {
            for(int i = 0 ; i < ranks.length ; i++) {
                newDeck.add(new Card(ranks[i], suit, i+2));
            }
        }
        return new Deck(newDeck);
    }

    public static void shuffleDeck(Deck deck) {
        List<Card> cards = deck.getDeck();
        Collections.shuffle(cards);
        deck.setDeck(cards);
    }

    public static List<Card> dealHand(Deck deck){
        List<Card> hand = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                hand.add(deck.getDeck().removeFirst());
            }
            return hand;
    }
    public static List<Card> drawCard(Deck deck, Player player) {
        List<Card> hand = new ArrayList<>();
        int cardsToDraw = 5 - player.getHand().size();
        // Draw cards from the deck to fill the hand to 5 cards
        for (int i = 0; i < cardsToDraw; i++) {
            if (!deck.getDeck().isEmpty()) {
                hand.add(deck.getDeck().removeFirst()); // Remove card from deck and add to hand
            } else {
                // Handle the case when the deck is empty
                System.out.println("The deck is empty. Cannot draw more cards.");
                break;
            }
        }
            hand.addAll((player.getHand()));
            player.setHand(hand);

        return player.getHand();
    }

    public static void startTurn(Player player, Deck deck) throws IOException {
        //Show Hand
        System.out.println("Your Cards : " + player.getHand().toString());
        //While Loop or For Loop
            Scanner prompt = new Scanner(System.in);
            //Prompt : Would you like to change out any cards? (Y/N)
            System.out.println("Would you like to change out any cards? (Y/N)");
            //Await Scanner Input Y/N;
            String userInput = prompt.next();
            //If Yes
            if (userInput.toLowerCase().contains("y")) {
                selectCard(player.getHand(), player);
                player.setHand(drawCard(deck, player));
                System.out.println(player.getHand());
            }
                isUserTurn = false;


        }

    public static String selectCard(List cards, Player player) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Card> updatedHand = (ArrayList<Card>) player.getHand();
        boolean selected = false;

        // Loop until a card is selected
        while (!selected) {
            int selectedIndex = 0;
            // Display instructions
            System.out.println("Which Card would you like to switch out? (Enter 'done' to finish)");
            // Display cards with arrow indicating selected card
                    System.out.println(" " + "1 | 2 |  3  | 4 | 5 " + " ");
                    System.out.println(updatedHand);

            String input;
            input = scanner.next();
            if (input.equals("1")) {
                selectedIndex = (0);
                updatedHand.remove(selectedIndex);
            } else if (input.equals("2")) {
                selectedIndex = (1);
                updatedHand.remove(selectedIndex);
            } else if (input.equals("3")) {
                selectedIndex = (2);
                updatedHand.remove(selectedIndex);
            } else if (input.equals("4")) {
                selectedIndex = (3);
                updatedHand.remove(selectedIndex);
            } else if (input.equals("5")) {
                selectedIndex = (4);
                updatedHand.remove(selectedIndex);
            } else if (input.toLowerCase().equals("done")){
                selected = true;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
        System.out.println(updatedHand);
        player.setHand(updatedHand);
        System.out.println(player.getHand());
        return "End of Turn";
    }

    public static void startRound(Player player1, Player bot, GameStats gameStats) throws IOException {
        // Set up game state for a new round
        GameState gameState = new GameState();
        gameState.setState(GameState.State.startRound);
        isUserTurn = true;

        // Deal cards to players
        player1.setHand(dealHand(deck));
        bot.setHand(dealHand(deck));

        // Continue with the round until it ends
        while (gameState.getState() != GameState.State.endGame) {
            gameState.setState(GameState.State.midRound);
            // Start Turn
            if (isUserTurn && gameState.getState() == GameState.State.midRound) {
                startTurn(player1, deck);
            } else if (!isUserTurn && gameState.getState() == GameState.State.midRound) {
                System.out.println("------ Bot is now taking its turn -----");
                gameState.setState(GameState.State.endRound);
            }

            if (gameState.getState() == GameState.State.endRound) {
                // Determine Winner
                Player winner = House.determineWinner(player1, bot, gameStats);
                if (winner == player1) {
                    gameStats.setWins(gameStats.getWins() + 1);
                } else if (winner == bot) {
                    gameStats.setLosses(gameStats.getLosses() + 1);
                }
                gameStats.setRoundsPlayed(gameStats.getRoundsPlayed() + 1);
                gameState.setState(GameState.State.endGame);
            }
        }
    }

    public static void logGameData(GameStats gameStats) {
        try (FileWriter writer = new FileWriter("game_statistics.csv")) {

            writer.write("Rounds Played, Wins, Losses, Royal Flush Count, Straight Flush Count, Four of a Kind Count, Flush Count, Straight Count, Full House Count, Three of a Kind Count, Two Pair Count, One Pair Count, High Card Count, \n");

            writer.write(gameStats.getRoundsPlayed() + ",");
            writer.write(gameStats.getWins() + ",");
            writer.write(gameStats.getLosses() + ",");
            writer.write(gameStats.getRoyalFlushCount() + ",");
            writer.write(gameStats.getStraightFlushCount() + ",");
            writer.write(gameStats.getFourOfAKindCount() + ",");
            writer.write(gameStats.getFlushCount() + ",");
            writer.write(gameStats.getStraightCount() + ",");
            writer.write(gameStats.getFullHouseCount() + ",");
            writer.write(gameStats.getThreeOfAKindCount() + ",");
            writer.write(gameStats.getTwoPairCount() + ",");
            writer.write(gameStats.getOnePairCount() + ",");
            writer.write(gameStats.getHighCardCount() + ",");

            System.out.println("Statistics written to game_statistics.csv");
        } catch (IOException e) {
            System.out.println("Failed to write statistics to CSV: " + e.getMessage());
        }
    }


}

// Method to update game statistics after each round
//    private static void updateGameStats(Player player1, Player bot, GameStats gameStats) {
//        // Update rounds played
//        gameStats.setRoundsPlayed(gameStats.getRoundsPlayed() + 1);
//        // Determine the winner of the round
//        Player winner = House.determineWinner(player1, bot, gameStats);
//        if (winner == player1) {
//            gameStats.setWins(gameStats.getWins() + 1);
//        } else if (winner == bot) {
//            gameStats.setLosses(gameStats.getLosses() + 1);
//        }
//
//    }