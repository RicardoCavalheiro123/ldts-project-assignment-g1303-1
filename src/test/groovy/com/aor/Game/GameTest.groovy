package com.aor.Game

import com.aor.ElementBlock.DestructableBlock
import com.aor.ElementBlock.Door
import com.aor.Positions.Position
import spock.lang.Specification

class GameTest extends Specification {

    private game;

    def setup(){
        game = new Game()
    }

    def "ReadMap"() {
        given:
            game = Mock(Game.class)
            def b
        when:
            b = game.canMove(new Position(0,0))
        then:
            b == true
    }

    def "Run"() {
    }

    def "Draw"() {
    }

    def "VerifyRPositionBomb"() {
    }

    def "MovementRobots"() {
    }
}
