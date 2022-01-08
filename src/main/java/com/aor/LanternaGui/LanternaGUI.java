package com.aor.LanternaGui;

import com.aor.InputHandler.InputHandler;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class LanternaGUI extends InputHandler {
    protected AWTTerminalFrame terminal;
    protected Screen screen;

    protected int width = 45;
    protected int height = 13;

    public LanternaGUI() throws FontFormatException {
        super();
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).setTerminalEmulatorTitle("BOMBERMAN");
            terminalFactory.setForceAWTOverSwing(true);

            terminalFactory.setTerminalEmulatorFontConfiguration(setFont("src/main/resources/FonteFinal.ttf",80));

            Terminal terminal = terminalFactory.createTerminal();


            this.terminal =(AWTTerminalFrame)terminal;
            this.terminal.setCursor(null);

            terminal.exitPrivateMode();


            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Font GetFont(String name,int size) throws IOException, FontFormatException {
        File fontFile = new File(name);
        Font font = Font.createFont(Font.TRUETYPE_FONT,fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loaded = font.deriveFont(Font.PLAIN,size);
        return loaded;
    }

    protected AWTTerminalFontConfiguration setFont(Font lf){
        return AWTTerminalFontConfiguration.newInstance(lf);
    }

    protected AWTTerminalFontConfiguration setFont(String lf,int size) throws IOException, FontFormatException {
        return AWTTerminalFontConfiguration.newInstance(GetFont(lf,size));
    }

}
