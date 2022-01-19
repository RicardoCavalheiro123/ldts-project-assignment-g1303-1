package com.aor.Models.PowerUpModel;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class SlowTime implements PowerUpModel {
    boolean selected,red;
    long timestarted;
    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public void startTimer() {
        timestarted = System.currentTimeMillis();
    }

    @Override
    public boolean isExpired() {
        return (System.currentTimeMillis()-timestarted>5);
    }

    @Override
    public boolean slowTime() {
        return false;
    }

    @Override
    public boolean changeSkin() {
        return false;
    }

    @Override
    public boolean increaseSpeed() {
        return false;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ADD8E6"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(20,20 ), "q");
    }
    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected() {
        selected = true;
    }

    @Override
    public void setUnselected() {
        selected = false;
    }

    @Override
    public void setRed() {
        this.red = true;
    }
}
