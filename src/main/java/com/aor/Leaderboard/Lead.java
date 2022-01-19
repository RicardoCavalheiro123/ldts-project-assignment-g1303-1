package com.aor.Leaderboard;

import com.aor.Positions.Position;

public class Lead {
    String playerName;
    Integer time;

    public Lead(String p, Integer t){
        this.playerName = p;
        this.time = t;
    }

    public String getName() {
        return playerName;
    }

    public Integer getTime() {
        return time;
    }
    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()) return false;

        return (this == o) ||
                (this.time == ((Lead) o).time && this.playerName == ((Lead) o).playerName);
    }
}
