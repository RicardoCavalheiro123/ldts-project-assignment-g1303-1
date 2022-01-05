package com.aor.ElementBlock;

import com.aor.game.Position;

public abstract class ElementBlock implements GameBlock {
    protected Position position;

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
}
