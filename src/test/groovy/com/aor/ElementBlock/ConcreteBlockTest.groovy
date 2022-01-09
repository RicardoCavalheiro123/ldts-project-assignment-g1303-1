package com.aor.ElementBlock

import spock.lang.Specification

class ConcreteBlockTest extends Specification {
    private b;

    def setup(){
        b = new ConcreteBlock(1,1)
    }

    def "Draw"() {
    }

    def "IsDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == b.IsDestroyed();
    }

    def "SetDestroyed"() {
        when:
        def destroyed = false;

        then:
        destroyed == b.setDestroyed();
    }
}
