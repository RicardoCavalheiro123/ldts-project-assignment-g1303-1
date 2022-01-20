package com.aor.States;

import com.aor.BomberMan;
import com.aor.InputHandler.MenuController;
import com.aor.LanternaGui.LanternaGUI;
import com.aor.Models.EndGameModel.EndGameModel;
import com.aor.Models.EndGameModel.MenuEnd;
import com.aor.Models.EndGameModel.PlayAgainEnd;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.io.IOException;

public class EndGame extends GameState{
    EndGameModel play,menu;
    MenuController menuController = new MenuController();
    public EndGame(BomberMan game) {
        super(game);
        play = new PlayAgainEnd();
        menu = new MenuEnd();
    }

    @Override
    public void start() {
        super.bomberMan.terminal.addKeyListener(menuController);
        play.setSelected();
    }

    @Override
    public void update(BomberMan bomberMan) throws IOException {
        super.bomberMan.screen.clear();
        TextGraphics t = super.bomberMan.screen.newTextGraphics();
        t.setBackgroundColor(TextColor.Factory.fromString("#CBE1EF"));
        t.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(LanternaGUI.width, LanternaGUI.height), ' ');
        play.draw(t);
        menu.draw(t);
        Selectable();
        super.bomberMan.screen.refresh();
    }

    private void Selectable() {
        if(menuController.down){
            menuController.down = false;
            if(play.isSelected()){
                play.setUnselected();
                menu.setSelected();
            }
        }
        if(menuController.up){
            menuController.up = false;
            if(menu.isSelected()){
                menu.setUnselected();
                play.setSelected();
            }
        }
        if(menuController.Enter){
            menuController.Enter = false;
            doAction();
        }
    }

    private void doAction() {
        if(menu.isSelected()){
            super.bomberMan.terminal.removeKeyListener(menuController);
            changeState(new MenuState(super.bomberMan));
            return;
        }
        if(play.isSelected()){
            super.bomberMan.terminal.removeKeyListener(menuController);
            try {
                changeState(new PlayingState(super.bomberMan));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
