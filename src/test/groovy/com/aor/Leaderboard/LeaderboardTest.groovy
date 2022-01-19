package com.aor.Leaderboard

import spock.lang.Specification

class LeaderboardTest extends Specification {
    private leaderboard

    def setup(){
        leaderboard = new Leaderboard("src/main/resources/leaderboard/leaderboard.txt")
    }
    def "GetLeaderboardsList"() {
        when:
            def l = leaderboard.getLeaderboardsList()
        then:
            l.get(0).getName() == "Diogo"
            l.get(0).getTime() == 10
            l.get(1).getName() == "Ricardo"
            l.get(1).getTime() == 50
            l.get(2).getName() == "Joao"
            l.get(2).getTime() == 212
    }
}
