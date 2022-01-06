package com.aor.ElementBlock;

import com.aor.Positions.Position;

public abstract class ElementBlock implements GameBlock {
    protected Position position;
    boolean bomb, destructableBlock, undestructableBlock;

    public ElementBlock(int x, int y){
        position = new Position(x,y);
        position.setX(x);
        position.setY(y);
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isBomb(){
        return bomb;
    }
    public boolean isDestructableBlock(){
        return destructableBlock;
    }
    public boolean isUndestructableBlock(){
        return undestructableBlock;
    }
}