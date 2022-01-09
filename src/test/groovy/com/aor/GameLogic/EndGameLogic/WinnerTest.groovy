package com.aor.GameLogic.EndGameLogic

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
