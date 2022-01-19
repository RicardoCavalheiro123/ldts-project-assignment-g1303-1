package com.aor.Models.MenuModels;

import com.googlecode.lanterna.graphics.TextGraphics;

public interface MenuModel {
    void draw(TextGraphics graphics);
    boolean isSelected();
}
