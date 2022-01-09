package com.aor.ElementBlock

import com.aor.Element.Robot
import spock.lang.Specification

class BombTest extends Specification {
    private bomb;

    def setup(){
        bomb = new Bomb(1,1)
    }

    def "Draw"() {
    }

    def "IsDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == bomb.IsDestroyed();
    }

    def "SetDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == bomb.setDestroyed();
    }

    def "GetTime"() {
    }

    def "SetExploded"() {
        when:
        def exploded = true;

        then:
        bomb.setExploded();
        exploded == bomb.isExploded();
    }

    def "IsExploded"() {
        when:
        def exploded = false;

        then:
        exploded == bomb.isExploded();
    }
}
