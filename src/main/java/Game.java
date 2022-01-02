import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener {

    Hero bomberman = new Hero(10,10);
    ArrayList<ConcreteBlock> blocks = new ArrayList<ConcreteBlock>();
    int rows = 13;
    int cols = 13;
    int[][] scene = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public Game(){
        //create interface
    }
    public void start(){
        System.out.println("ta a andar");
        //start game
    }

    public void readMap() {
        for(int i = 0; i < cols; i++) {
            for(int j = 0; j < rows; j++) {
                if(scene[i][j] == 1) {
                    ConcreteBlock block = new ConcreteBlock(i,j);
                    blocks.add(block);
                }
                if(scene[i][j] == 2) {
                    Position pos = new Position(i,j);
                    bomberman.setPosition(pos);
                }
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            bomberman.setPosition(position);
    }

    public boolean canHeroMove(Position position) {
        for(ConcreteBlock block : blocks) {
            if(block.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }



    private void update(){
        //updates
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
