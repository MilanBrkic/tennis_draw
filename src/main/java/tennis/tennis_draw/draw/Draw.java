/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis.tennis_draw.draw;

import tennis.tennis_draw.domain.Match;
import tennis.tennis_draw.domain.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Draw {

    public static Match[] drawTournament(Player[] players) throws Exception {
        if (players == null) {
            throw new Exception("Empty array entered");
        }
        if (players.length != 32) {
            throw new Exception("Array must contain exactly 32 players");
        }
        Match[] matches = new Match[16];
        fillMatches(matches);
        sortPlayers(players);
        shufflePlayers(players);
        int mlength = matches.length;
        
        
        matches[0] = new Match();
        matches[0].setPlayerOne(players[0]);
        matches[mlength-1] = new Match();
        matches[mlength-1].setPlayerOne(players[1]);

        putSeeders(players, 2, matches, 0, mlength);
        putNonSeeders(matches,players);
        
        return matches;
    }

    public static void putSeeders(Player[] players, int start, Match[] matches,int mstart, int mlength){
        if(start>(players.length/2)-1) return;
        
        if(mlength==1) return;
        
        mlength = mlength/2;
        
        matches[mstart+mlength].setPlayerOne(players[start]);
        matches[mstart+mlength-1].setPlayerOne(players[start+1]);
        
        putSeeders(players, start*2, matches, mstart, mlength);
        putSeeders(players, start*2+2, matches, mstart+mlength, mlength);
        
        
    }
    
    public static void shufflePlayers(Player[] players){
        int start = 2;
        int end = 4;
        Random rand = new Random();
        while(start<=players.length/4){
            for (int i = 0; i < 15; i++) {
                int prvi = rand.nextInt((end - start)) + start;
                int drugi  = rand.nextInt((end - start)) + start;
                swap(players,prvi, drugi);
            }
            start*=2;
            end*=2;
        }
    }
    
    public static void swap(Player[] players, int prvi, int drugi){
        Player temp = players[prvi];
        players[prvi] = players[drugi];
        players[drugi] = temp;
    }
    
    
    public static void putNonSeeders(Match[] matches, Player[] players){
        Random rand = new Random();
        Map<Integer, String> popunjene = new HashMap<Integer, String>();
        
        for(int i = 16;i<32;i++){
            while(true){
                int redniBrMeca = rand.nextInt(16);
                if(!popunjene.containsKey(redniBrMeca)){
                    matches[redniBrMeca].setPlayerTwo(players[i]);
                    popunjene.put(redniBrMeca, "");
                    break;
                }
            }
        }
    }
    

    public static void sortPlayers(Player[] players) {
        int n = players.length;

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (players[j].getPoints() > players[maxIndex].getPoints()) {
                    maxIndex = j;
                }
            }

            Player temp = players[maxIndex];
            players[maxIndex] = players[i];
            players[i] = temp;
        }

    }

    public static Player[] makePlayerArray() {
        Player[] players = new Player[32];

        players[0] = new Player(0, "John Isner", 1895);
        players[1] = new Player(1, "Roger Federer", 6630);
        players[2] = new Player(2, "Roberto Bautista Agut", 2710);
        players[3] = new Player(3, "David Goffin", 2600);
        players[4] = new Player(4, "Anrei Rublev", 4164);
        players[5] = new Player(5, "Dusan Lajovic", 1785);
        players[6] = new Player(6, "Stefanos Tsitsipas", 5940);
        players[7] = new Player(7, "Grigor Dimitrov", 2260);
        players[8] = new Player(8, "Carlos Ruud", 1739);
        players[9] = new Player(9, "Borna Coric", 1855);
        players[10] = new Player(10, "Alexander Zverev", 5615);

        players[11] = new Player(11, "Rafael Nadal", 9850);
        players[12] = new Player(12, "Benoit Paire", 1738);
        players[13] = new Player(13, "Diego Schwartzmann", 3455);
        players[14] = new Player(14, "Harem Hurkasz", 1735);
        players[15] = new Player(15, "Stanislas Wawrinka", 2365);
        players[16] = new Player(16, "Novak Djokovic", 12030);
        players[17] = new Player(17, "Dominic Thiem", 9125);
        players[18] = new Player(18, "Daniil Medvedev", 8470);
        players[19] = new Player(19, "Filip Krajinovic", 1673);

        players[20] = new Player(20, "Thomas Fritz", 1695);
        players[21] = new Player(21, "Karen Khachanov", 2245);
        players[22] = new Player(22, "Pablo Carreno Busta", 2580);
        players[23] = new Player(23, "Fabio Fognini", 2420);
        players[24] = new Player(24, "Mateo Berrettini", 3120);
        players[25] = new Player(25, "Alex de Minaur", 2065);
        players[26] = new Player(26, "Milos Raonic", 2580);
        players[27] = new Player(27, "Ugo Humbert", 1671);
        players[28] = new Player(28, "Denis Shapovalov", 2830);
        players[29] = new Player(29, "Carlos Garin", 2180);

        players[30] = new Player(30, "Gael Monfils", 2860);
        players[31] = new Player(31, "Felix Auger-Aliassime", 2241);

        return players;
    }

    public static void main(String[] args) {

        Player[] players = makePlayerArray();
        sortPlayers(players);
        Match[] matches;
        try {
            matches = drawTournament(players);
            for (int i = 0; i < matches.length; i++) {
                if (matches[i] == null) {
                    System.out.println("null");
                }
                System.out.println(matches[i]);
                
                if(i==7) System.out.println("");
                
            }
        } catch (Exception ex) {
            Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void fillMatches(Match[] matches) {
        for (int i = 0; i < 16; i++) {
            matches[i] = new Match();
        }
    }
}
