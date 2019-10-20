/*
 * Contiene informacion del jugador y sus respectivos tableros,
 * 2 tableros por jugador que corresponde al tablero principal donde se encuentran
 * los barcos y otro de disparos para cada jugador.
 */

import java.util.ArrayList;
/**
 *
 * @author jsandi321
 */
public class Player {
    //Atributos
    private String name;
    private int hits = 0;
    private int misses = 0;
    private int lives = 21;
    private String principalBoard[][]= new String[10][10];
    private String shootsBoard[][]= new String[10][10];
    public Ship shipsBoard[][]= new Ship[10][10];
    private ArrayList<Ship> ships = new ArrayList();
    
    //Constructor
    public Player(String name){
        this.name = name;
    }
    
    //Getters
    public String getName(){
        return name;
    }
    public int getHits(){
        return hits;
    }
    public int getMisses(){
        return misses;
    }
    public int getLives(){
        return lives;
    }
    public String[][] getPrincipalBoard(){
        return principalBoard;
    }
    public String[][] getShootsBoard(){
        return shootsBoard;
    }
    public ArrayList getPlayerShips(){
        return ships;
    }
    public Ship getShipInBoard(int i, int j){
      return shipsBoard[i][j];
    }

    //Setters
    public void setMissOnBoard(int i, int j, String[][] board){
      board[i][j] = "x";
    }

    //Métodos

    //Marca el fallo aliado en shootsBoard
    public void setAllyMissOnBoard(int i, int j){
      setMissOnBoard(i,j, shootsBoard);
      misses++;
    }

    //Marca el fallo enemigo en principalBoard
    public void setEnemyMissOnBoard(int i, int j){
      setMissOnBoard(i,j, principalBoard);
    }

    //Actualiza (shipsBoard, ship, principalBoard, lives) con el hit del barco aliado
    public void allyShipHited(int i, int j, Ship ship){
      shipsBoard[i][j] = null;

      ship.updateShip();
      if(ship.isSunk()){
        updateShipOnBoard(ship, principalBoard, true);
      }else{
        principalBoard[i][j] = cyan+"ø"+reset;
      }
      lives--;
    }

    //Actualiza (shootsBoard, hits) con el hit del barco.
    //Si el barco isSunk su representación pasa a ser del color que le corresponde en shootsBoard
    public void enemyShipHited(int i, int j, Ship ship){
      if(ship.isSunk()){
        updateShipOnBoard(ship, shootsBoard, true);
      }else{
        shootsBoard[i][j] = cyan+"ø"+reset;
      }hits++;
    }
    
    //Retorna un Boolean correspondiente a si en la posición indicada hay un barco o no en shipsBoard[][].
    public Boolean shipInPos(int i, int j){
      if(shipsBoard[i][j] instanceof Ship){
        return true;
      }
      return false;
    }

    //Coloca en shipsBoard[][] el barco en cada posicion que pertenece a este.
    public void putShip(Ship ship){
      for(int i = 0; i < ship.getSize(); i++){
        if(i == 0) {
          shipsBoard[ship.getStartPositionI()][ship.getStartPositionJ()] = ship;
        }else{
          if("up".equals(ship.getOrientation()) ){
            shipsBoard[ship.getStartPositionI()-i][ship.getStartPositionJ()] = ship;
            }
          if("down".equals(ship.getOrientation()) ){
            shipsBoard[ship.getStartPositionI()+i][ship.getStartPositionJ()] = ship;
            }
          if("left".equals(ship.getOrientation()) ){
            shipsBoard[ship.getStartPositionI()][ship.getStartPositionJ()-i] = ship;
            }
          if("right".equals(ship.getOrientation()) ){
            shipsBoard[ship.getStartPositionI()][ship.getStartPositionJ()+i] = ship; 
            }
  
          }
        }
 
      }

      //Actualiza la representación de ship en board[][]
    public void updateShipOnBoard(Ship ship, String board[][], Boolean sunk){
      String shipSymbol;
      String field = "";
      if(sunk){shipSymbol="ø";}else{shipSymbol="o";}
      if("Submarine".equals(ship.getClass().getName())){
        field = green+shipSymbol+reset;
        }
      if("Frigate".equals(ship.getClass().getName())){
        field = yellow+shipSymbol+reset;
        }
      if("Destroyer".equals(ship.getClass().getName())){
        field = red+shipSymbol+reset;
        }
      if("AircraftCarrier".equals(ship.getClass().getName()))  {
        field = blue+shipSymbol+reset;
        }
        
      for(int i = 0; i < ship.getSize(); i++){
        if(i == 0) {
          board[ship.getStartPositionI()][ship.getStartPositionJ()] = field;
        }else{
          if("up".equals(ship.getOrientation()) ){
            board[ship.getStartPositionI()-i][ship.getStartPositionJ()] = field;
            }
          if("down".equals(ship.getOrientation()) ){
            board[ship.getStartPositionI()+i][ship.getStartPositionJ()] = field;
            }
          if("left".equals(ship.getOrientation()) ){
            board[ship.getStartPositionI()][ship.getStartPositionJ()-i] = field;
            }
          if("right".equals(ship.getOrientation()) ){
            board[ship.getStartPositionI()][ship.getStartPositionJ()+i] = field;
            }  
          }
        }
      }


    //Declaración de colores
    String black="\033[30m"; 
    String red="\033[31m"; 
    String green="\033[32m"; 
    String yellow="\033[33m"; 
    String blue="\033[34m"; 
    String purple="\033[35m"; 
    String cyan="\033[36m"; 
    String white="\033[37m";
    String reset="\u001B[0m";


    //Actualiza la representación de cada barco en principalBoard[][] con respecto a shipsBoard[][]. 
    //Sólo se utiliza luego de colocar un barco en shipsBoard[][]
    public void updatePrincipalBoard(){
      for(int i = 0; i < shipsBoard.length; i++){
        for(int j = 0; j < shipsBoard.length; j++){
          if (shipsBoard[i][j]!=null){
            if("Submarine".equals(shipsBoard[i][j].getClass().getName())){
              principalBoard[i][j] = green+"o"+reset;
              }
            if("Frigate".equals(shipsBoard[i][j].getClass().getName())){
              principalBoard[i][j] = yellow+"o"+reset;
              }
            if("Destroyer".equals(shipsBoard[i][j].getClass().getName())){
              principalBoard[i][j] = red+"o"+reset;
              }
            if("AircraftCarrier".equals(shipsBoard[i][j].getClass().getName())){
              principalBoard[i][j] = blue+"o"+reset;
            }else{}
          }
        }
      }
    }

    //Retorna un Boolean que corresponde a que si un ship puede ser colocado en una matriz de 10x10.
    public Boolean shipCanBePlaced(int i,int j,String orientation,Ship ship){
      int nextI, nextJ;
      for(int n = 0; n < ship.getSize(); n++){
        if(i>=10 || j>=10) {
          return false;
          }else{
            if("up".equals(orientation)){
              nextI =  i-n;
              nextJ = j;
              if(nextI>=10 || nextJ>=10){
                return false;
                }
              if(shipsBoard[nextI][nextJ]!=null){
                return false;
                }
            }
            if("down".equals(orientation)){
              nextI = i+n;
              nextJ = j;
              if(nextI>=10 || nextJ>=10){
                return false;
                }
              if(shipsBoard[nextI][nextJ]!=null){
                return false;
                }
            }
            if("left".equals(orientation)){
              nextI = i;
              nextJ = j-n;
              if(nextI>=10 || nextJ>=10){
                return false;
                }
              if(shipsBoard[nextI][nextJ]!=null){
                return false;
                }
            }
            if("right".equals(orientation)){
              nextI = i;
              nextJ = j+n;
              if(nextI>=10 || nextJ>=10){
                return false;
              }
              if(shipsBoard[nextI][nextJ]!=null){
                return false;
              }
            }
                
          }
      }return true;
    }

    /**
    * Descripcion: Metodo que rellena el tablero de disparos con Strings que se 
    * reemplazaran por lo disparos del jugador.
    */
    public void generateBoard(String[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                String field = "~";
                board[i][j] = field;
            }
        }
    }

    /**
     * Descripcion: Metodo imprime en pantalla shootsBoard[][]
     */
    public void showShootBoard(){
        System.out.println("Este es tu tablero de disparos, "+name);
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        for (int i=0; i < shootsBoard.length;i++) {
            System.out.print(i+" |");
            for (int j=0; j < shootsBoard[i].length; j++) {
                System.out.print (shootsBoard[i][j]);
                if (j!=shootsBoard[i].length-1) System.out.print("  ");
            }
            System.out.println("| "+i);
        }
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
    }

    /**
     * Descripcion: Metodo que imprime en pantalla principalBoard[][]
     */
    public void showPrincipalBoard(){
      updatePrincipalBoard();
        System.out.println("Este es tu tablero de barcos, "+name);
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        for (int i=0; i < principalBoard.length; i++) {
            System.out.print(i+" |");
            for (int j=0; j < principalBoard[i].length; j++) {
                System.out.print (principalBoard[i][j]);
                if (j!=principalBoard[i].length-1) System.out.print("  ");
            }
            System.out.println("| "+i);
        }
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
    }

    /**
     * Descripcion: Este metodo genera los objetos de tipo Ship y los almacena
     * en ships
     */
    public void generateShips(){
        AircraftCarrier portaAviones = new AircraftCarrier();
        ships.add(portaAviones);
        for(int i = 0; i < 3; i++){
            Submarine submarinos = new Submarine();
            ships.add(submarinos);
        }
        for(int i = 0; i < 3; i++){
            Destroyer destructor = new Destroyer();
            ships.add(destructor);
        }
        for(int i = 0; i < 2; i++){
            Frigate fragata = new Frigate ();
            ships.add(fragata);
        }
    }
}
