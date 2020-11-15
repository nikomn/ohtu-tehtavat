package ohtu;

public class Player implements Comparable<Player> {

    /*
    {"name":"CJ Suess","nationality":"USA","assists":0,"goals":0,
    "penalties":0,"team":"WPG","games":1}
     */
    private String name;
    private String nationality;
    private Integer assists;
    private Integer goals;
    private Integer penalties;
    private String team;
    private Integer games;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        // Markus Nutivaara
        // Mikael Granlund
        if (name.length() > 15) {
            return name + "\t" + team + "\t" + goals + " + " + assists + " = " + (goals + assists);
        } else {
            return name + "\t\t" + team + "\t" + goals + " + " + assists + " = " + (goals + assists);
        }

    }

    @Override
    public int compareTo(Player player) {
        return (player.getAssists() + player.getGoals()) - (this.goals + this.assists);
    }

}
