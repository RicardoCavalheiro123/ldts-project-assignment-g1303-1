package com.aor.States;

import com.aor.BomberMan;
import com.aor.InputHandler.MenuController;
import com.aor.LanternaGui.LanternaGUI;
import com.aor.Models.MenuModels.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


import java.io.IOException;
import java.util.List;

public class MenuState extends GameState{
    MenuModel play,shop,leaderboard,exit;
    MenuController menuController = new MenuController();

    public MenuState(BomberMan game) {
        super(game);
        play = new Play();
        shop = new ShopMenu();
        leaderboard = new LeaderBoard();
        exit = new ExitMenu();
    }

    @Override
    public void start() {
        super.bomberMan.terminal.addKeyListener(menuController);
    }

    @Override
    public void update(BomberMan bomberMan) throws IOException {
        super.bomberMan.screen.clear();
        TextGraphics t = super.bomberMan.screen.newTextGraphics();
        t.setBackgroundColor(TextColor.Factory.fromString("#CBE1EF"));
        t.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(LanternaGUI.width, LanternaGUI.height), ' ');
        t.enableModifiers(SGR.BOLD);
        t.putString(new TerminalPosition(10, 10), "PLAY");
        t.putString(new TerminalPosition(20, 20), "SHOP");
        t.putString(new TerminalPosition(30, 30), "LEADERBOARD");
        t.putString(new TerminalPosition(40, 40), "EXIT");
        super.bomberMan.screen.refresh();
    }
    private void Selectable(){
        if(menuController.down){

        }
    }
}
