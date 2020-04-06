package com.weebkun.util;

import com.weebkun.util.models.Vote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteCounter {

    public static Map<String, Integer> count(List<Vote> votes, String[] choices){
        Map<String, Integer> tally = new HashMap<>();
        for(String choice : choices){
            tally.put(choice, 0);
        }
        for(Vote vote : votes){
            tally.put(vote.choice, tally.get(vote.choice) + 1);
        }
        return tally;
    }
}
