package com.aor.GameLogic

import com.aor.GameLogic.EndGameLogic.Loser
import spock.lang.Specification

class LoserTest extends Specification {
    private Loser l;

    def setup(){
        l = new Loser()
    }
    def "NotifyLoser"() {
        when:
            def lose = true;
            l.NotifyLoser()
        then:
            l.lost() == lose;
    }
}
