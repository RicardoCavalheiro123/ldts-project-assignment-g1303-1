package com.aor.States;

import com.aor.BomberMan;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LeaderboardState extends GameState{
    private int nrPlayers;
    Map<Integer, String> map = new TreeMap<Integer, String>();
    public LeaderboardState(BomberMan game) {
        super(game);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(BomberMan bomberMan) throws IOException, InterruptedException {

    }
    public void addNewTime(String name, Integer time){
        map.put(time,name);
    }

}
