/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis.tennis_draw.domain;


/**
 *
 * @author user
 */
public class Match {
    private Player playerOne;
    private Player playerTwo;

    public Match() {
    }

    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    @Override
    public String toString() {
        
        return (playerOne!=null? playerOne.toString():"null") + " - "+(playerTwo!=null? playerTwo.toString():"null");
    }
    
    
}
