package com.aor.Models.PowerUp;

import com.googlecode.lanterna.graphics.TextGraphics;

public interface PowerUp {
    int getPrice();
    void startTimer();
    boolean isExpired();
    boolean slowTime();
    boolean changeSkin();
    boolean increaseSpeed();
    void draw(TextGraphics graphics);
}
