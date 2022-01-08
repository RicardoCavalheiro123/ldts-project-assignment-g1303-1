package com.aor.ElementBlock;

import com.aor.Positions.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public interface GameBlock {
    void draw(TextGraphics graphics);
    Position getPosition();
    boolean IsDestroyed();
    boolean setDestroyed();
    boolean isBomb();
    boolean isUndestructableBlock();
    boolean isDestructableBlock();
}
