package com.aor.ElementBlock

import spock.lang.Specification

class DoorTest extends Specification {
    private d;

    def setup(){
        d = new Door(1,1)
    }

    def "Draw"() {

    }

    def "IsDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == d.IsDestroyed();
    }

    def "SetDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == d.IsDestroyed();
    }
}
