package com.aor.LanternaGui;

import com.aor.InputHandler.InputHandler;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.*;

import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class LanternaGUI extends InputHandler {
    protected AWTTerminalFrame terminal;
    protected Screen screen;

    int width = 100;
    int height = 50;

    public LanternaGUI() throws FontFormatException {
        super();
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            //setFont("oi.ttf");

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);

            terminalFactory.setTerminalEmulatorFontConfiguration(setFont("src/main/resources/Fonte.ttf"));

            Terminal terminal = terminalFactory.createTerminal();

            //terminal = new SwingTerminalFrame("Bomberman",terminalSize,new TerminalEmulatorDeviceConfiguration(),font,TerminalEmulatorColorConfiguration.newInstance(TerminalEmulatorPalette.DEFAULT),TerminalEmulatorAutoCloseTrigger.CloseOnEscape);
            //((AWTTerminalFrame)terminal).setCursor(null);

            this.terminal =(AWTTerminalFrame)terminal;
            this.terminal.setCursor(null);

            terminal.exitPrivateMode();


            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected Font GetFont(String name) throws IOException, FontFormatException {
        File fontFile = new File(name);
        Font font = Font.createFont(Font.TRUETYPE_FONT,fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loaded = font.deriveFont(Font.PLAIN,100);
        return loaded;
    }
    protected AWTTerminalFontConfiguration setFont(Font lf){
        return AWTTerminalFontConfiguration.newInstance(lf);
    }
    protected AWTTerminalFontConfiguration setFont(String lf) throws IOException, FontFormatException {
        return AWTTerminalFontConfiguration.newInstance(GetFont(lf));
    }

}
