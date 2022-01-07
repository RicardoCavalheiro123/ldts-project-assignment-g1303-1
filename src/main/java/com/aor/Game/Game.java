package com.aor.Game;

import com.aor.Element.Hero;
import com.aor.ElementBlock.Bomb;
import com.aor.ElementBlock.ConcreteBlock;
import com.aor.ElementBlock.DestructableBlock;
import com.aor.Element.Robot;
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
import java.util.Random;


public class Game extends LanternaGUI implements KeyListener {
    int speed = 0;
    Hero bomberman;
    Font font;
    ArrayList<ConcreteBlock> blocks = new ArrayList<ConcreteBlock>();
    ArrayList<DestructableBlock> blocksd = new ArrayList<DestructableBlock>();
    ArrayList<Robot> robots = new ArrayList<Robot>();
    ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    int width = 720;
    int height = 480;
    int rows = 13;
    int cols = 13;
    int[][] scene = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 3, 0, 3, 3, 3, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 5, 0, 0, 0, 3, 0, 3, 0, 5, 3, 3, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    public Game() throws IOException, FontFormatException {
        super();
        terminal.setLocationRelativeTo(null);
        terminal.setVisible(true);
        terminal.setFocusable(true);
        terminal.requestFocusInWindow();
        terminal.addKeyListener(this);
        terminal.pack();
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
                }if(scene[i][j] == 3) {
                    DestructableBlock block = new DestructableBlock(i,j);
                    blocksd.add(block);
                }
                if(scene[i][j] == 5) {
                    Robot robot = new Robot(i,j);
                    robots.add(robot);
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
        for(DestructableBlock block : blocksd) {
            if(block.IsDestroyed()){
                continue;
            }
            if(block.getPosition().equals(position)) {
                return false;
            }
        }
        for(Robot robot : robots) {
            if(robot.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    private boolean canRobotMove(Position position) {
        for(ConcreteBlock block : blocks) {
            if(block.getPosition().equals(position)) {
                return false;
            }
        }
        for(DestructableBlock block : blocksd) {
            if(block.IsDestroyed()){
                continue;
            }
            if(block.getPosition().equals(position)) {
                return false;
            }
        }

        for(Robot robot : robots) {
            if(robot.getPosition().equals(position)) {
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
        for(DestructableBlock block : blocksd) {
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
        for(Robot robot : robots) {
            robot.draw(graphics);
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

        CheckAddBomb();
        CheckExplodedBomb();
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

    private void CheckAddBomb() {
        if(setBomb){
            setBomb = false;
            Bomb b = new Bomb(new Position(bomberman.getPosition().getX(),bomberman.getPosition().getY()));
            bombs.add(b);
            scene[b.getPosition().getY()][b.getPosition().getX()] = 4;
        }
    }
    private void CheckExplodedBomb() {
        for(Bomb bomb : bombs) {
            if(!bomb.isExploded()){
                if(bomb.getTime()>4000){
                    bomb.setExploded();
                    scene[bomb.getPosition().getY()][bomb.getPosition().getX()] = 0;
                    destroyBlocks(bomb.getPosition());
                }
            }

        }
    }
    private void destroyBlocks(Position p){
        boolean up,down,left,right;
        up = true;
        down = true;
        left = true;
        right = true;
        for(int x = 1; x<5;x++){
            for(DestructableBlock block : blocksd) {
                if(block.IsDestroyed()){
                    continue;
                }
                if(block.getPosition().equals(new Position(p.getX()+(1*x),p.getY())) && right){
                    right = false;
                    block.setDestroyed();
                }else if(block.getPosition().equals(new Position(p.getX()-(1*x),p.getY())) && left){
                    left = false;
                    block.setDestroyed();
                }else if(block.getPosition().equals(new Position(p.getX(),p.getY()+(1*x))) && down){
                    down = false;
                    block.setDestroyed();
                }else if(block.getPosition().equals(new Position(p.getX()-(1*x),p.getY()-(1*x))) && up){
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
            if(num == 1 && canRobotMove(new Position(temp.getPosition().getX() + 1, temp.getPosition().getY()))) {
                temp.moveRight();
            }
            else if(num == 2 && canRobotMove(new Position(temp.getPosition().getX() - 1, temp.getPosition().getY()))) {
                temp.moveLeft();
            }
            else if(num == 3 && canRobotMove(new Position(temp.getPosition().getX(), temp.getPosition().getY() - 1))) {
                temp.moveUp();
            }
            else if(num == 3 && canRobotMove(new Position(temp.getPosition().getX(), temp.getPosition().getY() + 1))) {
                temp.moveDown();
            }
        }
    }

}

