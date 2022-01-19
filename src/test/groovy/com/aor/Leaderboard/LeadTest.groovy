package com.aor.Leaderboard

import spock.lang.Specification

class LeadTest extends Specification {
    private lead;

    def setup(){
        lead = new Lead("D",1)
    }
    def "GetName"() {
        expect:
            lead.getName() == "D"
    }

    def "GetTime"() {
        expect:
            lead.getTime() == 1
    }

    def "Equals"() {
        given:
            def leadAux = new Lead("D",1)
        expect:
            lead.equals(leadAux)
    }
}
