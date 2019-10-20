import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author jsandi321
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Game gm = new Game();


        //Mensaje de bienvenida y pide nombres de los jugadores
        gm.clearConsole();
        System.out.println("\n\nBienvenidos a Battleship\n\n");
        System.out.print("Jugador 1 por favor introduce tu nombre: ");
        String name1 = sc.next();
        System.out.print("\nJugador 2 por favor introduce tu nombre: ");
        String name2 = sc.next();

        //Inicialización de objetos
        Player player1 = new Player(name1);
        player1.generateShips();
        player1.generateBoard(player1.getPrincipalBoard());
        player1.generateBoard(player1.getShootsBoard());

        Player player2 = new Player(name2);
        player2.generateShips();
        player2.generateBoard(player2.getPrincipalBoard());
        player2.generateBoard(player2.getShootsBoard());


        //Saludo personalizado a los jugadores
        gm.clearConsole();
        System.out.println("Bienvenidos "+player1.getName()+" y "+player2.getName()+" :D");
        gm.pressEnter();

        //ArrayList de los jugadores para crear loop
        ArrayList<Player> players = new ArrayList();
        players.add(player1);
        players.add(player2);
        //players.get(posicion);

        int a = 0;
        while(a<2){
          //Ambos jugadores colocan sus barcos
          gm.clearConsole();
          System.out.println("Es momento de que "+players.get(a).getName()+" coloque sus barcos\n\n");
          gm.pressEnter();
          gm.clearConsole();
          gm.placeAllShips(players.get(a));
          a++;
        }

        //Mensaje pre-rondas/Loop de juego
        gm.clearConsole();
        System.out.println("Empiezan las rondas");
        gm.pressEnter();
        gm.clearConsole();

        //Boolean para cambio de jugador por turno
        boolean player1HitTurn = true;

        //Loop de juego. Termina hasta que haya un jugador sin vidas
        while(gm.winner(player1, player2)==false){
          if(player1HitTurn){

            //Turno de player1
            System.out.println("Es turno de "+player1.getName() );
            gm.pressEnter();
            gm.clearConsole();

            //Imprime shipsBoard[][] y shootBoard[][] del player1
            player1.showPrincipalBoard();
            player1.showShootBoard();

            gm.doAHit(player1, player2);  //Invoca función de realizar un tiro.
            gm.showPlayerStats(player1);  //Invoca función que muestra las estadisticas del jugador.
            player1HitTurn = false;  //Pasa el turno al otro jugador
            
            //Imprime shootBoard[][] luego del tiro realizado por player1
            player1.showShootBoard();

            gm.pressEnter();
            gm.clearConsole();
          }else{

            //Turno de player2
            System.out.println("Es turno de "+player2.getName() );
            gm.pressEnter();
            gm.clearConsole();

            //Imprime shipsBoard[][] y shootBoard[][] del player2
            player2.showPrincipalBoard();
            player2.showShootBoard();

            gm.doAHit(player2, player1);  //Invoca función de realizar un tiro.
            gm.showPlayerStats(player2);  //Invoca función que muestra las estadisticas del jugador.
            player1HitTurn = true; //Pasa el turno al otro jugador

            //Imprime shootBoard[][] luego del tiro realizado por player1
            player2.showShootBoard();

            gm.pressEnter();
            gm.clearConsole();
            }
        }
        gm.clearConsole();
        String winner = gm.whoIsTheWinner(player1, player2);  //Obtiene el nombre del ganador del juego
        System.out.println("EL GANADOR ES "+winner.toUpperCase()+" !!!"); //Imprime mensaje personalizado con nombre del ganador.

   }
}
