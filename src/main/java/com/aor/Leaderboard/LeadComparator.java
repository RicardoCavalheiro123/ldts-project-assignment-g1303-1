package com.aor.Leaderboard;

import java.util.Comparator;

class LeadComparator implements Comparator<Lead> {

    public int compare(Lead s1, Lead s2)
    {
        if (s1.getName() == s2.getName() && s1.getTime() == s2.getTime())
            return 0;
        else if (s1.getTime() > s2.getTime())
            return 1;
        else if (s1.getTime() == s2.getTime())
            return 1;
        else
            return -1;
    }
}