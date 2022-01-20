package com.aor;

import com.aor.LanternaGui.LanternaGUI;

import com.aor.States.MenuState;

import com.aor.States.GameState;

import com.aor.User.User;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.io.IOException;

public class BomberMan {
    public User user = new User("Unknown");
    public Screen screen;
    private static BomberMan bomberMan = null;
    private GameState gameState,LastGameState = null,LastBeforeShop = null;
    public AWTTerminalFrame terminal = LanternaGUI.AWTTerminalFrameFactory();

    public BomberMan() throws IOException {
        terminal.setLocationRelativeTo(null);
        terminal.setVisible(true);
        terminal.pack();
        terminal.pack();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        this.gameState = new MenuState(this);
    }
    public void setGameState(GameState gameState) {
        this.LastGameState = this.gameState;
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }
    public void setLastGameState() {
        this.gameState = this.LastGameState;
        if (gameState != null)
            this.gameState.start();
    }
    public void restoreBeforeShop(){
        this.gameState = this.LastGameState;
        this.LastGameState = this.LastBeforeShop;
        if (gameState != null)
            this.gameState.start();

    }
    public void setGameStateBeforeShop(GameState gameState) {
        this.LastBeforeShop = gameState;

    }
    public GameState getGameState(){
        return this.gameState;
    }
    public GameState getGameLastState(){
        return this.LastGameState;
    }
    public static BomberMan getInstance() throws IOException, FontFormatException {
        if (bomberMan == null) {
            bomberMan = new BomberMan();
        }
        return bomberMan;
    }
    public void start() throws IOException, InterruptedException {
        this.gameState.start();

        while (gameState != null){
            long startTime = System.currentTimeMillis();

            gameState.update(this);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = 1000 /60 - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
        }
        screen.close();
        terminal.close();

        return;

    }
}
