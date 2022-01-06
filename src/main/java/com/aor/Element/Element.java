package com.aor.Element;

import com.aor.game.Position;

public abstract class Element implements GameCharacter {

    protected Position position;
    boolean robot, hero;
    public Element(int x, int y){
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
