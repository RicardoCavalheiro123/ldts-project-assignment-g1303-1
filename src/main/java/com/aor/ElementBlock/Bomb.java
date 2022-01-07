package com.aor.ElementBlock;

import com.aor.Positions.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bomb extends ElementBlock {
    long start;
    boolean exploded;
    public Bomb(int x, int y) {
        super(x, y);
        bomb = true;
        exploded = false;
        start = System.currentTimeMillis();
    }

    public Bomb(Position position) {
        super(position);
        start = System.currentTimeMillis();
        exploded = false;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().getX(), super.getPosition().getY()), "W");
    }
    public long getTime(){
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        return timeElapsed;
    }

    public void setExploded() {
        this.exploded = true;
    }

    public boolean isExploded() {
        return exploded;
    }
}
