package com.aor.GeneralElement;

import com.aor.Positions.Position;

public abstract class GeneralElement {
    protected Position position;
    public GeneralElement(Position position){
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
