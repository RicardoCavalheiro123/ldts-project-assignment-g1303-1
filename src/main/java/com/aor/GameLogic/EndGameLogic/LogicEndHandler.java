package com.aor.GameLogic.EndGameLogic;

public abstract class LogicEndHandler implements NotifyEndGame {

    long timeStart;
    long timeNotify;
    boolean win,lose;

    public LogicEndHandler(){
        win =false;
        lose= false;
        timeStart = System.currentTimeMillis();
        timeNotify = System.currentTimeMillis();
    }
    @Override
    public long getTimeNotify(){
        return timeNotify;
    }
    public boolean lost(){
        return lose;
    }
    public boolean won(){
        return win;
    }

}
