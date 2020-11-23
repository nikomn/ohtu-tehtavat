package ohtu;

public class TennisGame {

    private int playerOneScore = 0;
    private int PlayerTwoScore = 0;
    private String playerOneName;
    private String playerTwoName;

    public TennisGame(String player1Name, String player2Name) {
        this.playerOneName = player1Name;
        this.playerTwoName = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(playerOneName)) {
            playerOneScore += 1;
        } else {
            PlayerTwoScore += 1;
        }
    }
    
    public String scoreString(int score) {
        switch (score) {
                    case 0:
                        return "Love";
                    case 1:
                        return "Fifteen";
                    case 2:
                        return "Thirty";
                    case 3:
                        return "Forty";
                }
        return "Forty";
    }

    public String evenScore(int score) {
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
        }
        return "Deuce";
    }

    public String winningScore(int scoreDifference) {
        if (scoreDifference == 1) {
            return "Advantage player1";
        } else if (scoreDifference == -1) {
            return "Advantage player2";
        } else if (scoreDifference >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (playerOneScore == PlayerTwoScore) {
            score = evenScore(playerOneScore);
        } else if (playerOneScore >= 4 || PlayerTwoScore >= 4) {
            int minusResult = playerOneScore - PlayerTwoScore;
            score = winningScore(minusResult);
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    tempScore = playerOneScore;
                } else {
                    score += "-";
                    tempScore = PlayerTwoScore;
                }
                score += scoreString(tempScore);
            }
        }
        return score;
    }
}
