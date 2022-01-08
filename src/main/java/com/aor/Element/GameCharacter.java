package com.aor.Element;

import com.aor.Positions.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public interface GameCharacter {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void draw(TextGraphics graphics);
    Position getPosition();
    void setPosition(Position position);
}
