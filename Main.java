public class Main {

    static Player player1 = new Player(1000,0,true, 1);
    static Player player2 = new Player(1000,0,true, 2);
    static Player player3 = new Player(1000,0,true, 3);

    public static void main(String[] args) {

        boolean gameOver = false;

        while (gameOver == false) {
            if (player1.inGame == true) {
                player1.Turn(1);
            }
            if (player2.inGame == true) {
                player2.Turn(2);
            }
            if (player3.inGame == true) {
                player3.Turn(3);
            }
            
            //check for a winner
            int playersAlive = 0;
            Player winner = null;
        
            if (player1.inGame) {
                playersAlive++;
                winner = player1;
            }
            if (player2.inGame) {
                playersAlive++;
                winner = player2;
            }
            if (player3.inGame) {
                playersAlive++;
                winner = player3;
            }
        
            if (playersAlive == 1) {
                gameOver = true;
                System.out.println("The winner is Player " + winner.playerNumber + "!");
            }
        }


    }


}
 
