package com.aor.GameLogic.EndGameLogic;



public class Loser extends LogicEndHandler {
    @Override
    public void NotifyWinner() {

    }

    @Override
    public void NotifyLoser() {
        lose = true;
        timeNotify = System.currentTimeMillis()- timeStart;
    }


}
