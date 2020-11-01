package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Statistics {

    private List<Player> players;

    public Statistics(Reader reader) {
        //reader = new PlayerReader("https://nhlstatisticsforohtu.herokuapp.com/players.txt");
        players = reader.getPlayers();       
    }

    public Player search(String name) {
        for (Player player : players) {
            if (player.getName().contains(name)) {
                return player;
            }
        }

        return null;
    }

    public List<Player> team(String teamName) {
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();
        
        for (Player player : players) {
            if ( player.getTeam().equals(teamName)) {
                playersOfTeam.add(player);
            }
        }
        
        return playersOfTeam;
    }

    public List<Player> topScorers(int howMany) {
        Collections.sort(players);
        ArrayList<Player> topScorers = new ArrayList<Player>();
        Iterator<Player> playerIterator = players.iterator();
        
        /*
        Testit feilaa tämän kohdalla. Ei ihan selvää, pitikö korjata, mutta 
        näihän tällainen varmaan menis oikein?
        Ensinnä alkuun varmaan pitää olla joku tällanen:
        */
        
//        if (howMany > players.size()) {
//            return players;
//        }
        
        // Ja lisäksi tähän kohtaan:
        
//        while (howMany > 0) {
        while (howMany>=0) {
            topScorers.add( playerIterator.next() );            
            howMany--;
        }
        
        return topScorers;
    }

}
