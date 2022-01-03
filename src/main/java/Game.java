import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class Game implements KeyListener {
    private Screen screen;
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
        try {
            TerminalSize terminalSize = new TerminalSize(13, 13);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            TextGraphics graphics = screen.newTextGraphics();
            readMap();
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        readMap();
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

    public void run() throws IOException{
        screen.clear();
        draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(13, 13), ' ');
        bomberman.draw(graphics);
        for(ConcreteBlock block : blocks) {
            block.draw(graphics);
        }
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
