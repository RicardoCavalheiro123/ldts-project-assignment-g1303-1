package com.aor.GameLogic

import com.aor.GameLogic.EndGameLogic.Winner
import spock.lang.Specification

class WinnerTest extends Specification {
    private w;

    def setup(){
        w = new Winner()
    }
    def "NotifyWinner"() {
        when:
        def win = true;
        w.NotifyWinner();
        then:
        win == w.won();
    }
}
