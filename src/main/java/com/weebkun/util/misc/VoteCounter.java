/*
Copyright 2020 Weebkun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.weebkun.util.misc;

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
