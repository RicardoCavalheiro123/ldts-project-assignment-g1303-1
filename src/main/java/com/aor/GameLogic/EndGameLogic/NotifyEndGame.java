package com.aor.GameLogic.EndGameLogic;

public interface NotifyEndGame {
    void NotifyWinner();
    void NotifyLoser();
    long getTimeNotify();
    boolean lost();
    boolean won();
}
