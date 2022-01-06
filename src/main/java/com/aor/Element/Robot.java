package com.aor.Element;

import com.aor.game.Position;

public class Robot extends Element{
    public Robot(int x, int y) {
        super(x, y);
        robot = true;
    }
    @Override
    public void moveUp() {
        position.setY(position.getY()-1);
    }

    @Override
    public void moveDown() {
        position.setY(position.getY()+1);
    }

    @Override
    public void moveLeft() {
        position.setX(position.getX()-1);
    }

    @Override
    public void moveRight() {
        position.setX(position.getX()+1);
    }
}