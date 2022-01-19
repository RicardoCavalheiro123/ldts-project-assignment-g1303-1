package com.aor.States;

import com.aor.BomberMan;
import com.aor.InputHandler.MenuController;
import com.aor.LanternaGui.LanternaGUI;
import com.aor.Models.PowerUpModel.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class ShopState extends GameState{
    MenuController menuController = new MenuController();
    PowerUpModel skin,fast,slow,resume;
    public ShopState(BomberMan game) {
        super(game);
        skin = new ChangeSkin();
        fast = new IncreaseSpeed();
        slow = new SlowTime();
        resume = new ResumeGamePowerup();
    }

    @Override
    public void start() {
        super.bomberMan.terminal.addKeyListener(menuController);
        skin.setSelected();
    }

    @Override
    public void update(BomberMan bomberMan) throws IOException, InterruptedException {
        super.bomberMan.screen.clear();
        TextGraphics t = super.bomberMan.screen.newTextGraphics();
        t.setBackgroundColor(TextColor.Factory.fromString("#CBE1EF"));
        t.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(LanternaGUI.width, LanternaGUI.height), ' ');
        skin.draw(t);
        fast.draw(t);
        slow.draw(t);
        resume.draw(t);
        Selectable();
        super.bomberMan.screen.refresh();
    }

    private void Selectable(){
        if(menuController.down){
            menuController.down = false;
            if(skin.isSelected()){
                skin.setUnselected();
                fast.setSelected();
            }
            else if(fast.isSelected()){
                fast.setUnselected();
                slow.setSelected();
            }
            else if(slow.isSelected()){
                slow.setUnselected();
                resume.setSelected();
            }
        }
        if(menuController.up){
            menuController.up = false;
            if(resume.isSelected()){
                resume.setUnselected();
                slow.setSelected();
            }
            else if(slow.isSelected()){
                slow.setUnselected();
                fast.setSelected();
            }
            else if(fast.isSelected()){
                fast.setUnselected();
                skin.setSelected();
            }
        }
        if(menuController.Enter){
            menuController.Enter = false;
            doAction();
        }
    }

    private void doAction() {
        if(resume.isSelected()){
            super.bomberMan.terminal.removeKeyListener(menuController);
            super.bomberMan.setLastGameState();
            return;
        }
        if(skin.isSelected()){
            if(super.bomberMan.user.getBalence()>skin.getPrice()){
                super.bomberMan.user.takeFromBalence(skin.getPrice());
                super.bomberMan.user.addPowerUp(new ChangeSkin());
            }
        }
        if(fast.isSelected()){
            if(super.bomberMan.user.getBalence()>fast.getPrice()){
                super.bomberMan.user.takeFromBalence(fast.getPrice());
                super.bomberMan.user.addPowerUp(new IncreaseSpeed());
            }
        }
        if(slow.isSelected()){
            if(super.bomberMan.user.getBalence()>slow.getPrice()){
                super.bomberMan.user.takeFromBalence(slow.getPrice());
                super.bomberMan.user.addPowerUp(new SlowTime());
            }
        }
    }
}
