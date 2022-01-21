package com.aor.Leaderboard

import spock.lang.Specification

class LeaderboardFactoryTest extends Specification {
    private leaderboard

    def setup(){
        leaderboard = new LeaderboardFactory("src/main/resources/leaderboard/leaderboard.txt")
    }
    def "GetLeaderboardsList"() {
        when:
            def l = leaderboard.getLeaderboardsList()
        then:
            l.get(0).getName() == "DIOGO"
            l.get(0).getTime() == 10
            l.get(1).getName() == "RICARDO"
            l.get(1).getTime() == 50
            l.get(2).getName() == "JOAO"
            l.get(2).getTime() == 212
    }
}
