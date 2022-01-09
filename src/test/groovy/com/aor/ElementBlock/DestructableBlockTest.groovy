package com.aor.ElementBlock

import spock.lang.Specification

class DestructableBlockTest extends Specification {
    private b;

    def setup(){
        b = new DestructableBlock(1,1)
    }

    def "IsDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == b.IsDestroyed();
    }

    def "SetDestroyed"() {
        when:
        def destroyed = true;

        then:
        destroyed == b.setDestroyed();
    }

    def "Draw"() {
    }
}
