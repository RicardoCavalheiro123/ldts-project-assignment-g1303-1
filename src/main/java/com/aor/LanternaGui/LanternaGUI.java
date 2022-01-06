package com.aor.LanternaGui;

import com.aor.InputHandler.InputHandler;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import java.awt.*;
import java.io.IOException;

public abstract class LanternaGUI extends InputHandler {
    protected SwingTerminalFrame terminal;
    protected Screen screen;
    int width = 720;
    int height = 480;

    public LanternaGUI(){
        super();
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminal = terminalFactory.createSwingTerminal();
            terminal.setSize(width,height);
            terminal.setPreferredSize(new Dimension(width,height));
            terminal.setMaximumSize(new Dimension(width,height));
            terminal.setMinimumSize(new Dimension(width,height));
            terminal.pack();
            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
