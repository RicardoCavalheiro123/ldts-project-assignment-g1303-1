package com.aor.LanternaGui;

import com.aor.InputHandler.InputHandler;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class LanternaGUI extends InputHandler {
    protected SwingTerminalFrame terminal;
    protected Screen screen;
    protected SwingTerminalFontConfiguration font;
    int width = 720;
    int height = 480;

    public LanternaGUI() throws FontFormatException {
        super();
        try {
            //GetFont("digital-7 (mono).ttf");
            TerminalSize terminalSize = new TerminalSize(width, height);
            //DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminal = new SwingTerminalFrame("Bomberman",terminalSize,new TerminalEmulatorDeviceConfiguration(),font,
                    TerminalEmulatorColorConfiguration.newInstance(TerminalEmulatorPalette.DEFAULT),TerminalEmulatorAutoCloseTrigger.CloseOnEscape);
            terminal.setSize(width,height);
            terminal.setPreferredSize(new Dimension(width,height));
            terminal.setMaximumSize(new Dimension(width,height));
            terminal.setMinimumSize(new Dimension(width,height));
            terminal.pack();
            terminal.exitPrivateMode();
            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected Font GetFont(String name) throws IOException, FontFormatException {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(name);
        Font f = Font.createFont(Font.TRUETYPE_FONT,stream).deriveFont(48f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(f);
        Font lf = f.deriveFont(Font.PLAIN,25);
        return lf;
    }
    protected void setFont(Font lf){
        font = SwingTerminalFontConfiguration.newInstance(lf);
    }
    protected void setFont(String lf) throws IOException, FontFormatException {
        font = SwingTerminalFontConfiguration.newInstance(GetFont(lf));
    }

}
