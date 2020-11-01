package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            players.add(new Player("Test", "TST", 100, 100));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void playerFound() {
        Player p = stats.search("Test");
        assertTrue("Player not found " + p.getName(), p.getName().equals("Test"));
    }
    
    @Test
    public void unfoundPlayerReturnNull() {
        Player p = stats.search("abc");
        assertTrue("Non-existing player found", p == null);
    }
    
    @Test
    public void playersOfTeamFoundCorrectly() {
        List<Player> team = stats.team("EDM");
        assertEquals("Team size found not correct", 3, team.size());
        List<Player> team2 = stats.team("abc");
        assertEquals("Team size found not correct", 0, team2.size());
        //assertTrue("Non-existing player found", p == null);
    }
    
    @Test
    public void topScorersFound() {
        List<Player> topScores = stats.topScorers(2);
        Player p1 = topScores.get(0);
        assertEquals("Top scorer not found", "Test", p1.getName());
        Player p2 = topScores.get(1);
        assertEquals("Top scorer not found", "Gretzky", p2.getName());
        
    }
    
    @Test
    public void numberOfTopScorersFoundCorrect() {
        List<Player> topScores = stats.topScorers(2);
        assertEquals("Number of top scores incorrect", 2, topScores.size());
        List<Player> topScores2 = stats.topScorers(200);
        assertEquals("Number of top scores incorrect", 6, topScores2.size());
        
    }

}
