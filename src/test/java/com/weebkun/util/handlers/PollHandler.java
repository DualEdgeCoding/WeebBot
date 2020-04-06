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

package com.weebkun.util.handlers;

import com.weebkun.Application;
import com.weebkun.util.models.Poll;
import com.weebkun.util.PollStatus;
import net.dv8tion.jda.api.entities.Guild;

import java.util.*;

public class PollHandler {

    private Guild guild;
    private List<Poll> polls = new ArrayList<>();

    public PollHandler(Guild g){
        this.guild = g;
    }

    public Guild getGuild() {
        return guild;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void startPoll(Poll poll){
        polls.add(poll);
        poll.status = PollStatus.ACTIVE;
        Application.getLogger().info("Started Poll {}: {}", poll.getId().toString(), poll.getQuestion());
    }

    public void endPoll(Poll poll) {
        polls.remove(poll);
        poll.status = PollStatus.ENDED;
        Application.getLogger().info("Ended Poll {}: {}", poll.getId().toString(), poll.getQuestion());
        // todo count and return votes.
    }

    /**
     * gets and returns Poll with provided id. may return null.
     * @param id - id of poll
     * @return Poll object with corresponding id.
     */
    public Poll getPoll(String id){
        for(Poll p : this.polls){
            if(p.getId().toString().equalsIgnoreCase(id)) return p;
        }
        return null;
    }
}
