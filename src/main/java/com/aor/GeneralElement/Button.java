package com.aor.GeneralElement;

import com.aor.Positions.Position;

public class Button extends GeneralElement {
    private final int width;
    private final int height;
    private final String text;

    public Button(Position position, int width, int height, String text) {
        super(position);
        this.width = width;
        this.height = height;
        this.text = text;
    }

}
