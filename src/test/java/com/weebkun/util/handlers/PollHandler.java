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
import com.weebkun.util.VoteCounter;
import com.weebkun.util.models.Poll;
import com.weebkun.util.PollStatus;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.util.*;

/**
 * Handler that keeps track of Polls for each server/guild, hence each guild will be attached to a unique Handler.
 * @see Poll
 * <br>
 * Each PollHandler instance keeps a reference to the Guild that it is attached to.
 * @author weebkun
 */
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

    /**
     * Method used to start a poll.
     * @param poll - Poll object that will be started.
     */
    public void startPoll(Poll poll){
        polls.add(poll);
        poll.status = PollStatus.ACTIVE;
        Application.getLogger().info("Started Poll {}: {}", poll.getId().toString(), poll.getQuestion());
    }

    /**
     * Method used to end a poll.
     * @param poll - Poll obj that will be ended.
     */
    public void endPoll(Poll poll) {
        polls.remove(poll);
        poll.status = PollStatus.ENDED;
        Application.getLogger().info("Ended Poll {}: {}", poll.getId().toString(), poll.getQuestion());
        // count and return votes.
        Map<String, Integer> total = VoteCounter.count(poll.getVotes(), poll.getChoices());

        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(poll.getAuthor().getEffectiveName()).setTitle("results for poll:").setDescription("'" + poll.getQuestion() + "'").setColor(0xcc55d3);
        total.forEach((choice, count) -> builder.addField(choice, count.toString() + (count == 1 ? " vote" : " votes"), false));
        poll.getChannel().sendMessage(builder.build()).queue();

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
