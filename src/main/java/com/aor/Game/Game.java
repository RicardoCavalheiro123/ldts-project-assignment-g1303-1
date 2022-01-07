package com.aor.Game;

import com.aor.Element.Hero;
import com.aor.ElementBlock.ConcreteBlock;
import com.aor.LanternaGui.LanternaGUI;
import com.aor.Positions.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.awt.event.KeyListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Game extends LanternaGUI implements KeyListener {
    int speed = 0;
    Hero bomberman;
    Font font;
    ArrayList<ConcreteBlock> blocks = new ArrayList<ConcreteBlock>();
    int width = 720;
    int height = 480;
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
    public Game() throws IOException, FontFormatException {
        super();
        terminal.setLocationRelativeTo(null);
        terminal.setVisible(true);
        terminal.setFocusable(true);
        terminal.requestFocusInWindow();
        terminal.addKeyListener(this);
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("fonte.ttf");
        font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        terminal.getGraphics().setFont(loadedFont);
        terminal.setFont(loadedFont);
        boolean a = terminal.isFontSet();
        SwingTerminalFontConfiguration fontConfiguration = SwingTerminalFontConfiguration.newInstance(loadedFont);
        terminal.pack();
        int x =0;
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
                    bomberman = new Hero(i,j);
                }
            }
        }
    }

    private boolean canHeroMove(Position position) {
        for(ConcreteBlock block : blocks) {
            if(block.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public boolean run() throws IOException, InterruptedException {
        boolean flag = true;
        while(flag) {
            screen.clear();
            flag = update();
            draw(screen.newTextGraphics());
            screen.refresh();
            Thread.sleep(1000/120);
        }
        screen.close();
        terminal.close();

        return flag;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        bomberman.draw(graphics);
        for(ConcreteBlock block : blocks) {
            block.draw(graphics);
        }
    }



    private boolean update(){
        moving = false;
        if(end){
            return false;
        }
        if (right && canHeroMove(new Position(bomberman.getPosition().getX() + 1, bomberman.getPosition().getY()))) {
            moving = true;
        }else{
            right = false;
        }
        if (left && canHeroMove(new Position(bomberman.getPosition().getX() - 1, bomberman.getPosition().getY()))) {
            moving = true;
        }else{
            left = false;
        }
        if (up && canHeroMove(new Position(bomberman.getPosition().getX(), bomberman.getPosition().getY()- 1))) {
            moving = true;
        }else{
            up = false;
        }
        if (down && canHeroMove(new Position(bomberman.getPosition().getX(), bomberman.getPosition().getY() + 1))) {
            moving = true;
        }else{
            down = false;
        }


        //code

        if (moving) {

            if (right) {
                bomberman.moveRight();
            } else if (left) {
                bomberman.moveLeft();
            } else if (up) {
                bomberman.moveUp();
            } else if (down) {
                bomberman.moveDown();
            }
        }
    return true;
    }

}

