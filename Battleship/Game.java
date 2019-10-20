/**
 *
 * @author Kevin
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  Scanner scan = new Scanner(System.in);  //Inicializa el Scanner.

  public Game(){
  }

  //Método para que el jugador ingresado coloque todos los barcos.
  public void placeAllShips(Player player){
    ArrayList<Ship> ships = player.getPlayerShips(); //Arreglo de barcos.

    //Recorre el arreglo de barcos y va colocando uno a uno.
    for(int i = 0; i < ships.size(); i++){
      player.updatePrincipalBoard();  //Invoca función que actualiza la principalBoard[][] de player.
      System.out.println("Barcos por colocar: "+(ships.size()-i)+"\n\n");
      placeOneShip(ships.get(i), player);  //Método para colocar un barco.
    }
    player.updatePrincipalBoard();  //Invoca función que actualiza la principalBoard[][] de player.

    clearConsole();
    System.out.println("Todos los barcos de "+player.getName()+" fueron colocados con correctamente");
    pressEnter();
  }

  //Método para colocar un barco actualizando los atributos correspondientes en player.
  public void placeOneShip(Ship ship, Player player){

    String shipName = ship.getShipName();  //Nombre del barco.
    int shipSize = ship.getSize();  //Tamaño del barco.
      
    player.updatePrincipalBoard(); //Invoca función que actualiza la principalBoard[][] de player.
    player.showPrincipalBoard();  //Imprime principalBoard[][] de player.

    System.out.println("\nVas a colocar un barco "+ shipName);
    System.out.println("Tiene un tamaño de "+ shipSize);
    System.out.print("\nInserte el número de fila: ");
    int i = scan.nextInt(); //Recibe el int correspondiente a la fila.
    System.out.print("Inserte el número de columna: ");
    int j = scan.nextInt(); //Recibe columna.
    System.out.print("Ingrese la orientación (UP, DOWN, LEFT, RIGHT): ");
    String orientation = scan.next(); //Recibe orientación.
    orientation = orientation.toLowerCase(); //Transforma el string en minusculas

    //Comprobación de que la orientación dada sea válida.
    if(validateOrientation(orientation)){
      //Orientación dada es válida.

      boolean canBePlaced = player.shipCanBePlaced(i,j,orientation, ship); //Comprueba si el barco puede ser colocado en el lugar indicado por player
      clearConsole();

      if(canBePlaced){
        //El barco puede ser colocado

        ship.setStartPositionI(i); //Establece la posicion inicial en I de ship.
        ship.setStartPositionJ(j); //Establece la posicion inicial en J de ship.
        ship.setOrientation(orientation); //Establece la orientación de ship.

        player.putShip(ship); //Coloca el barco en donde corresponde en shipsBoard[][].
        System.out.println("Barco colocado con éxito. :D\n");
      }else{
        //El barco no puede ser colocado

        System.out.println("El barco no puede colocarse en el lugar indicado D:\nIngrese los datos nuevamente.... ;)\n");
        placeOneShip(ship, player); //player debe ingresar denuevo los datos para el mismo barco
      }
    }else{
      //Orientación dada es invalida.

      clearConsole();
      System.out.println("Ingrese una orientación válida");
      placeOneShip(ship, player); //player debe ingresar denuevo los datos para el mismo barco
    }
    clearConsole();
  }


  //Pide al usuario ingresar los datos(fila, columna) del tiro que desea realizar.
  //Actualiza los atributos de player y enemy conforme al tiro que player efectúa.
  public void doAHit(Player player, Player enemy){
    System.out.println("Es tu turno de realizar un tiro, "+player.getName()+"!");
    System.out.print("Inserte el número de fila: ");
    int i = scan.nextInt();  //Recibe el int correspondiente a la fila.
    System.out.print("Inserte el número de columna: ");
    int j = scan.nextInt();  //Recibe el int correspondiente a la columna.
    Boolean hit = enemy.shipInPos(i,j); //Compruba si hay un barco en la posición dada.

    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    if(hit){
      //Barco tocado.

      //Actualiza los atributos de player y enemy con el hit 
      Ship ship = enemy.getShipInBoard(i, j); //ship corresponde al barco tocado
      enemy.allyShipHited(i, j, ship);   //Invoca método que realiza las modificaciones acertado un tiro enemigo
      player.enemyShipHited(i, j, ship); //Invoca método que realiza las modificaciones acertado un tiro aliado.

      //Imprime mensaje con el resultado del hit ("HUNDIDO" ó "TOCADO").
      if(ship.isSunk()){
        System.out.println("Haz hundido un barco "+ship.getShipName()+"!\n");
      }else{
        System.out.println("Haz tocado un barco !\n");
      }
    }
    if(!hit){
      //Ningún barco es tocado.

      //Actualiza los atributos de player y enemy con el miss
      player.setAllyMissOnBoard(i, j); //Invoca método que realiza las modificaciones fallado un tiro aliado.
      enemy.setEnemyMissOnBoard(i, j); //Invoca método que realiza las modificaciones fallado un tiro enemigo.
      System.out.println("Haz fallado el tiro XD \n");
    }
  }

  //Imprime las estadísticas(Hits,Misses,Lives) del Player recibido.
  public void showPlayerStats(Player player){
    System.out.println("Stats de "+player.getName() );
    System.out.println("Hits: "+player.getHits() );
    System.out.println("Misses: "+player.getMisses() );
    System.out.println("Lives: "+player.getLives() );
  }

  //Verifica si hay un jugador sin vidas.
  //Retorna true si hay un jugador sin vidas o false si no hay jugadores sin vidas.
  public boolean winner(Player player1, Player player2){
    if(player1.getLives()==0){
      return true;
    }
    if(player2.getLives()==0){
      return true;
    }
    else{return false;}
  }

  //Retorna el nombre del Player ganador.
  public String whoIsTheWinner(Player player1, Player player2){
    if(player1.getLives()==0){
      return player2.getName();
    }else{
      return player1.getName();
    }
  }

  //Verifica que el String dado sea una orientación correcta (up, right, down, left).
  //Retorna true si es válida o false si no lo es.
  public boolean validateOrientation(String string){
    if("up".equals(string)||"right".equals(string)||"down".equals(string)||"left".equals(string)){
      return true;
    }
    return false;
  }

  //Imprime un mensaje y cuando el usuario presiona enter la función termina
  public void pressEnter(){
    System.out.print("Presiona enter para continuar... ");
    try{
      System.in.read();
      }
      catch(Exception e){
        e.printStackTrace();
        }
  }

  //Imprime saltos de linea para limpiar la consola
  public void clearConsole(){
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }




  
}
