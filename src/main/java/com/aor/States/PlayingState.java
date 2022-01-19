package com.aor.States;

import com.aor.BomberMan;
import com.aor.Element.Hero;
import com.aor.ElementBlock.*;
import com.aor.Element.Robot;
import com.aor.GameLogic.EndGameLogic.Loser;
import com.aor.GameLogic.EndGameLogic.NotifyEndGame;
import com.aor.GameLogic.EndGameLogic.Winner;
import com.aor.InputHandler.InputHandler;
import com.aor.LanternaGui.LanternaGUI;
import com.aor.Music.MusicPlayer;
import com.aor.Positions.Position;

import com.aor.Strategy.FollowHeroMovement;
import com.aor.Strategy.RandomMovement;
import com.aor.Strategy.Strategy;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;



import java.awt.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;


public class PlayingState extends GameState {
    InputHandler inputHandler = new InputHandler();

    private Strategy strategy;
    Hero bomberman;
    Door door;
    NotifyEndGame notifyEndGame;

    ArrayList<GameBlock> blocks = new ArrayList<>();

    ArrayList<Robot> robots = new ArrayList<>();
    ArrayList<Bomb> bombs = new ArrayList<>();

    //MusicPlayer music;

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

    public PlayingState(BomberMan superb) throws IOException, FontFormatException {
        super(superb);
        super.bomberMan.terminal.addKeyListener(inputHandler);
        //music = new MusicPlayer();
    }
    @Override
    public void start(){
        readMap();
        //music.startMusic();
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

    @Override
    public void update(BomberMan bomberMan) throws IOException, InterruptedException {
        startTime = System.currentTimeMillis();
        boolean flag = true;
        Long l = System.currentTimeMillis();
        Long l1;
        while(flag) {
            l = System.currentTimeMillis();
            super.bomberMan.screen.clear();
            flag = update();
            super.bomberMan.screen.setCursorPosition(null);
            draw(super.bomberMan.screen.newTextGraphics());
            l1 = System.currentTimeMillis();
            if(l1-l<(1000/20)){
                Thread.sleep((1000/20)-(l1-l));
            }
            super.bomberMan.screen.refresh();

        }
        //music.endMusic();
        super.bomberMan.screen.clear();
        super.bomberMan.screen.newTextGraphics().setBackgroundColor(TextColor.Factory.fromString("#006400"));
        time = (System.currentTimeMillis()-startTime)/1000;
        if(notifyEndGame.lost()){
            //music.startLoseMusic();
            String s = "YOU LOST IN : "+ time + " seconds";
            super.bomberMan.screen.newTextGraphics().putString(new TerminalPosition(10,3),s );
            super.bomberMan.screen.refresh();
            Thread.sleep(2000);
        }
        if(notifyEndGame.won()){
            //music.startWinMusic();
            String s = "YOU WON IN : "+ time+" seconds";
            super.bomberMan.screen.newTextGraphics().putString(new TerminalPosition(10,3),s );
            super.bomberMan.screen.refresh();
            Thread.sleep(2000);

        }
        super.bomberMan.screen.refresh();
        super.bomberMan.screen.close();
        super.bomberMan.terminal.close();
        super.changeState(null);
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
        easyRobots();
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
            //music.startFootstep();
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
            //music.startBombMusic();
        }
    }
    private void CheckExplodedBomb() {
        for(Bomb bomb : bombs) {
            if(!bomb.isExploded()){
                if(bomb.getTime()>4000){
                    bomb.setExploded();
                    destroyBlocks(bomb.getPosition());
                    //music.startBombExplosionMusic();
                }
            }

        }
    }

    private boolean verifyRPositionBomb(Position p){
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

    public void easyRobots() {
        this.strategy = new RandomMovement();
        strategy.moveRobot(this);
    }

    public void hardRobots() {
        this.strategy = new FollowHeroMovement();
        strategy.moveRobot(this);
    }

    public Hero getHero() {
        return bomberman;
    }
}

