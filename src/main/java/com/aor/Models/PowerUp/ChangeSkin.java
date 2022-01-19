package com.aor.Models.PowerUp;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ChangeSkin implements PowerUp {
    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public void startTimer() {

    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean slowTime() {
        return false;
    }

    @Override
    public boolean changeSkin() {
        return true;
    }

    @Override
    public boolean increaseSpeed() {
        return false;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ADD8E6"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(20,20 ), "r");
    }

}
