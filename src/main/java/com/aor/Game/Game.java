package com.aor.Game;

import com.aor.Element.Hero;
import com.aor.ElementBlock.*;
import com.aor.Element.Robot;
import com.aor.GameLogic.EndGameLogic.Loser;
import com.aor.GameLogic.EndGameLogic.NotifyEndGame;
import com.aor.GameLogic.EndGameLogic.Winner;
import com.aor.InputHandler.InputHandler;
import com.aor.LanternaGui.LanternaGUI;
import com.aor.Positions.Position;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;


import java.awt.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;


public class Game{
    InputHandler inputHandler = new InputHandler();

    AWTTerminalFrame terminal;
    Screen screen;

    Hero bomberman;
    Door door;
    NotifyEndGame notifyEndGame;

    ArrayList<GameBlock> blocks = new ArrayList<>();

    ArrayList<Robot> robots = new ArrayList<>();
    ArrayList<Bomb> bombs = new ArrayList<>();

    long time,startTime;

    int rows = 13;
    int cols = 13*3+6;
    int[][] scene = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 3, 0, 0, 1},
        {1, 0, 1, 3, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 3, 1, 0, 0, 1},
        {1, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 3, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 5, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 0, 0, 3, 3, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 3, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 3, 3, 0, 5, 3, 3, 0, 0, 3, 0, 0, 3, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 3, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 3, 3, 3, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 8, 0, 3, 0, 0, 0, 0, 1},
        {1, 3, 1, 3, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 5, 0, 0, 0, 3, 0, 3, 0, 5, 3, 3, 0, 0, 0, 3, 5, 0, 0, 0, 3, 0, 3, 0, 5, 3, 3, 0, 0, 0, 3, 5, 0, 0, 0, 3, 0, 3, 0, 5, 3, 3, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public ArrayList<GameBlock> getBlocks() {
        return blocks;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public Game() throws IOException, FontFormatException {
        terminal = LanternaGUI.AWTTerminalFrameFactory();
        screen = new TerminalScreen(terminal);
        terminal.setLocationRelativeTo(null);
        terminal.setVisible(true);
        terminal.setFocusable(true);
        terminal.requestFocusInWindow();
        terminal.addKeyListener(inputHandler);
        terminal.pack();
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        terminal.pack();

    }
    public void start(){
        readMap();
    }

    public void readMap() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(scene[i][j] == 1) {
                    ConcreteBlock block = new ConcreteBlock(j,i);
                    blocks.add(block);
                }
                if(scene[i][j] == 2) {
                    bomberman = new Hero(j,i);
                }if(scene[i][j] == 3) {
                    DestructableBlock block = new DestructableBlock(j,i);
                    blocks.add(block);
                }
                if(scene[i][j] == 5) {
                    Robot robot = new Robot(j,i);
                    robots.add(robot);
                }
                if(scene[i][j] == 8) {
                    door = new Door(j,i);
                }
            }
        }
    }

    public boolean canMove(Position position) {

        for(GameBlock block : blocks) {
            if(block.IsDestroyed()){
                continue;
            }
            if(block.getPosition().equals(position)) {
                return false;
            }
        }
        for(Robot robot : robots) {
            if(robot.getPosition().equals(bomberman.getPosition())){
                return false;
            }
            if(robot.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }


    public void run() throws IOException, InterruptedException {
        startTime = System.currentTimeMillis();
        boolean flag = true;
        Long l = System.currentTimeMillis();
        Long l1;
        while(flag) {
            l = System.currentTimeMillis();
            screen.clear();
            flag = update();
            draw(screen.newTextGraphics());
            l1 = System.currentTimeMillis();
            if(l1-l<(1000/20)){
                Thread.sleep((1000/20)-(l1-l));
            }
            screen.refresh();

        }
        screen.clear();
        screen.newTextGraphics().setBackgroundColor(TextColor.Factory.fromString("#006400"));
        time = (System.currentTimeMillis()-startTime)/1000;
        if(notifyEndGame.lost()){
            String s = "YOU LOST IN : "+ time + " seconds";
            screen.newTextGraphics().putString(new TerminalPosition(10,3),s );
            screen.refresh();
            Thread.sleep(2000);
        }
        if(notifyEndGame.won()){
            String s = "YOU WON IN : "+ time+" seconds";
            screen.newTextGraphics().putString(new TerminalPosition(10,3),s );
            screen.refresh();
            Thread.sleep(2000);

        }
        screen.refresh();
        screen.close();
        terminal.close();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#006400"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(LanternaGUI.width, LanternaGUI.height), ' ');
        for(GameBlock block : blocks) {
            if(block.IsDestroyed())
                continue;
            block.draw(graphics);
        }
        for(Bomb bomb : bombs) {
            if(bomb.isExploded()){
                continue;
            }
            bomb.draw(graphics);
        }
        bomberman.draw(graphics);
        for(Robot robot : robots) {
            robot.draw(graphics);
        }
        door.draw(graphics);
    }



    private boolean update(){
        inputHandler.moving = false;
        if(inputHandler.end){
            notifyEndGame = new Loser();
            return false;
        }
        if (inputHandler.right && canMove(new Position(bomberman.getPosition().getX() + 1, bomberman.getPosition().getY()))) {
            inputHandler.moving = true;
        }else{
            inputHandler.right = false;
        }
        if (inputHandler.left && canMove(new Position(bomberman.getPosition().getX() - 1, bomberman.getPosition().getY()))) {
            inputHandler.moving = true;
        }else{
            inputHandler.left = false;
        }
        if (inputHandler.up && canMove(new Position(bomberman.getPosition().getX(), bomberman.getPosition().getY()- 1))) {
            inputHandler.moving = true;
        }else{
            inputHandler.up = false;
        }
        if (inputHandler.down && canMove(new Position(bomberman.getPosition().getX(), bomberman.getPosition().getY() + 1))) {
            inputHandler.moving = true;
        }else{
            inputHandler.down = false;
        }

        CheckAddBomb();
        CheckExplodedBomb();
        movementRobots();
        if(!bomberman.isAlive()){
            notifyEndGame = new Loser();
            notifyEndGame.NotifyLoser();
            time = notifyEndGame.getTimeNotify();
            return false;
        }
        if(robots.size()== 0){
            notifyEndGame = new Winner();
            notifyEndGame.NotifyWinner();
            time = notifyEndGame.getTimeNotify();
            return false;
        }
        for(Robot robot : robots){
            if(robot.getPosition().equals(bomberman.getPosition())){
                notifyEndGame = new Loser();
                notifyEndGame.NotifyLoser();
                time = notifyEndGame.getTimeNotify();
                return false;
            }
        }


        if ( inputHandler.moving) {

            if ( inputHandler.right) {
                bomberman.moveRight();
            } else if ( inputHandler.left) {
                bomberman.moveLeft();
            } else if ( inputHandler.up) {
                bomberman.moveUp();
            } else if ( inputHandler.down) {
                bomberman.moveDown();
            }
        }
        if(bomberman.getPosition().equals(door.getPosition())){
            notifyEndGame = new Winner();
            notifyEndGame.NotifyWinner();
            time = notifyEndGame.getTimeNotify();
            return false;
        }
    return true;
    }

    private void CheckAddBomb() {
        if( inputHandler.setBomb){
            inputHandler.setBomb = false;
            Bomb b = new Bomb(new Position(bomberman.getPosition().getX(),bomberman.getPosition().getY()));
            bombs.add(b);
        }
    }
    private void CheckExplodedBomb() {
        for(Bomb bomb : bombs) {
            if(!bomb.isExploded()){
                if(bomb.getTime()>4000){
                    bomb.setExploded();
                    destroyBlocks(bomb.getPosition());
                }
            }

        }
    }

    boolean verifyRPositionBomb(Position p){
        for(Robot r:robots){
            if(r.getPosition().equals(p)){
                r.setAsDead();
                robots.remove(r);
                return true;
            }
        }
        return false;
    }
    private void destroyBlocks(Position p){
        boolean up,down,left,right;
        up = true;
        down = true;
        left = true;
        right = true;
        if(bomberman.getPosition().equals(p)){
            bomberman.setAsDead();
        }
        for(int x = 1; x<5;x++){

            if(right) {
                if (bomberman.getPosition().equals(new Position(p.getX() + (x), p.getY()))) {
                    bomberman.setAsDead();
                }
                verifyRPositionBomb(new Position(p.getX() + (x), p.getY()));
            }
            if(left){
                if(bomberman.getPosition().equals(new Position(p.getX()-(x),p.getY()))){
                    bomberman.setAsDead();
                }
                verifyRPositionBomb(new Position(p.getX()-(x),p.getY()));
            }
            if(down) {
                if (bomberman.getPosition().equals(new Position(p.getX(), p.getY() + x))) {
                    bomberman.setAsDead();
                }
                verifyRPositionBomb(new Position(p.getX(), p.getY() + (x)));
            }
            if(up) {
                if (bomberman.getPosition().equals(new Position(p.getX(), p.getY() - x))) {
                    bomberman.setAsDead();
                }
                verifyRPositionBomb(new Position(p.getX(), p.getY() - (x)));
            }

            for(GameBlock block : blocks) {
                if(block.IsDestroyed()){
                    continue;
                }
                if(block.getPosition().equals(new Position(p.getX()+(x),p.getY())) && right) {
                    if(!block.isDestructableBlock()){
                        right = false;
                        continue;
                    }
                    right = false;
                    block.setDestroyed();
                }else if(block.getPosition().equals(new Position(p.getX()-(x),p.getY())) && left){
                    if(!block.isDestructableBlock()){
                        left = false;
                        continue;
                    }
                    left = false;
                    block.setDestroyed();
                }else if(block.getPosition().equals(new Position(p.getX(),p.getY()+(x))) && down){
                    if(!block.isDestructableBlock()){
                        down = false;
                        continue;
                    }
                    down = false;
                    block.setDestroyed();
                }else if(block.getPosition().equals(new Position(p.getX(),p.getY()-(x))) && up){
                    if(!block.isDestructableBlock()){
                        up = false;
                        continue;
                    }
                    up = false;
                    block.setDestroyed();
                }
            }
        }
    }

    public void movementRobots() {
        for(Robot temp : robots) {
            Random rand = new Random();
            int num = rand.nextInt(4)+1;
            if(num == 1 && canMove(new Position(temp.getPosition().getX() + 1, temp.getPosition().getY()))) {
                temp.moveRight();
            }
            else if(num == 2 && canMove(new Position(temp.getPosition().getX() - 1, temp.getPosition().getY()))) {
                temp.moveLeft();
            }
            else if(num == 3 && canMove(new Position(temp.getPosition().getX(), temp.getPosition().getY() - 1))) {
                temp.moveUp();
            }
            else if(num == 3 && canMove(new Position(temp.getPosition().getX(), temp.getPosition().getY() + 1))) {
                temp.moveDown();
            }
        }
    }

}

