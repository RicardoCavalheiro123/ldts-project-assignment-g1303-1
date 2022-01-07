package com.aor.LanternaGui;

import com.aor.InputHandler.InputHandler;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class LanternaGUI extends InputHandler {
    protected SwingTerminalFrame terminal;
    protected Screen screen;
    Font font;
    int width = 720;
    int height = 480;

    public LanternaGUI(){
        super();
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("fonte.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            SwingTerminalFontConfiguration fontConfiguration = SwingTerminalFontConfiguration.newInstance(loadedFont);
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfiguration);
            terminal = terminalFactory.createSwingTerminal();
            terminal.setSize(width,height);
            terminal.setPreferredSize(new Dimension(width,height));
            terminal.setMaximumSize(new Dimension(width,height));
            terminal.setMinimumSize(new Dimension(width,height));
            terminal.pack();
            screen = new TerminalScreen(terminal);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

}
