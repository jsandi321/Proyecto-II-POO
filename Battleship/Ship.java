/**
 *
 * @author Emmanuel
 */
public class Ship {
    //Atributos
    protected int size;
    protected int lives;
    protected int startPositionI;
    protected int startPositionJ;
    protected String orientation;
    protected boolean isSunk;

    //Constructores
    public Ship(int size){
        this.size = size;
        this.lives = size;
        this.isSunk = false;      
    }

    public Ship(int size, int startPositionI, int startPositionJ, String orientation){
        this.size = size;
        this.lives = size;
        this.isSunk = false;
        this.startPositionJ = startPositionI;
        this.startPositionI = startPositionJ;
        this.orientation = orientation;
    }
    
    //Getters
    public int getSize() {
        return size;
    }

    public int getLives() {
        return lives;
    }

    public int getStartPositionI(){
        return startPositionI;
    }
    
    public int getStartPositionJ(){
        return startPositionJ;
    }
    
    public String getOrientation(){
        return orientation;
    }

    public String getShipName(){
        return getClass().getName();
    }
    
    public boolean isSunk() {
        return isSunk;
    }
    
    //Setters
    public void setStartPositionI(int i){
        startPositionI = i;
    }
    
    public void setStartPositionJ(int j){
        startPositionJ = j;
    }
    
    public void setOrientation(String orientation){
        this.orientation = orientation;
    } 
    

    //Metodos

    //Actualiza el barco una vez que fue tocado
    public void updateShip() {
      lives--;
        if(lives == 0) {
            isSunk = true;
        }else{}
    }
   
}
