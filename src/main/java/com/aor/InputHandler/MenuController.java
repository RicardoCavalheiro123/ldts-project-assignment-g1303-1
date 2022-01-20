package com.aor.InputHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuController extends KeyAdapter {

    public boolean up, down,Enter;
    public MenuController(){
        down = false;
        up = false;
        Enter = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Enter = true;
        }
    }
}
