package com.aor.States;

import com.aor.BomberMan;

import java.io.IOException;

public abstract class GameState {
    protected final BomberMan bomberMan;

    public GameState(BomberMan game) {
        this.bomberMan = game;
    }
    public abstract void start();
    public abstract void update(BomberMan bomberMan) throws IOException, InterruptedException;
    public void changeState(GameState gameState){
        this.bomberMan.setGameState(gameState);
    }
    public BomberMan getGame(){
        return bomberMan;
    }
}
