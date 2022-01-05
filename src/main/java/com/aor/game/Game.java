package com.aor.game;

import com.aor.Element.Hero;
import com.aor.ElementBlock.ConcreteBlock;
import com.aor.game.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Game implements KeyListener {
    private boolean right, left, up, down,end;
    int speed = 0;
    private boolean moving;
    private SwingTerminalFrame terminal;
    private Screen screen;
    Hero bomberman;
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
    public Game(){
        try {
            TerminalSize terminalSize = new TerminalSize(13, 13);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminal = terminalFactory.createSwingTerminal();
            terminal.setSize(500,400);
            terminal.setPreferredSize(new Dimension(width,height));
            terminal.setMaximumSize(new Dimension(width,height));
            terminal.setMinimumSize(new Dimension(width,height));
            terminal.pack();
            terminal.setLocationRelativeTo(null);
            terminal.setVisible(true);
            terminal.addKeyListener(this);
            terminal.setFocusable(true);
            terminal.requestFocusInWindow();
            screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null);
            screen.doResizeIfNecessary();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int a = 0;
            //place bomb
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            end = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
    }
}

