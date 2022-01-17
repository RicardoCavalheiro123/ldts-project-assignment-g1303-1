package com.aor.States;

import com.aor.BomberMan;
import com.aor.Game.Game;


import java.awt.*;
import java.io.IOException;

public class PlayingState extends GameState{
    private Game game = new Game();
    public PlayingState(BomberMan bomberMan) throws IOException, FontFormatException {
        super(bomberMan);
    }

    @Override
    public void start() {
        game.start();
    }

    @Override
    public void update(BomberMan bomberMan) throws IOException {
        try {
            game.run();
            this.changeState(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
