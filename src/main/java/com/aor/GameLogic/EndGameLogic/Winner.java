package com.aor.GameLogic.EndGameLogic;



public class Winner extends LogicEndHandler {

    @Override
    public void NotifyWinner() {
        win = true;
        timeNotify = System.currentTimeMillis()- timeStart;
    }

    @Override
    public void NotifyLoser() {

    }
}
