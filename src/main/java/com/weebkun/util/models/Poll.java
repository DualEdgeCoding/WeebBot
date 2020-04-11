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

package com.weebkun.util.models;

import com.weebkun.util.misc.PollStatus;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Poll class for creating strawpolls.
 * Keeps a reference to the instance of the Guild and TextChannel it is in.
 * @author weebkun
 */
public class Poll {
    private String question;
    private String[] choices;
    private Member author;

    public PollStatus status;

    private JDA jda;
    private Guild guild;
    private TextChannel channel;
    private List<Vote> votes = new ArrayList<>();
    private UUID id;

    /**
     * gets question of strawpoll.
     * @return question of poll
     */
    public String getQuestion() {
        return question;
    }

    /**
     * gets list of choices.
     * @return choices
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * gets author of poll
     * @return author - Member instance of author
     */
    public Member getAuthor() {
        return author;
    }

    /**
     * jda instance of bot.
     * @return jda instance passed to constructor.
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * instance of guild that the poll was setup in.
     * @return Guild instance.
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * channel that command was sent in.
     * @return Channel instance.
     */
    public TextChannel getChannel() {
        return channel;
    }

    /**
     * get UUID of poll.
     * @return Id of poll as UUID.
     */
    public UUID getId() {
        return id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void vote(Member voter, String choice){
        if(this.status == PollStatus.ENDED){
            channel.sendMessage("❌ Sorry the poll has already ended.").queue();
            return;
        }
        for(String ch : this.choices){
            if(choice.equalsIgnoreCase(ch)){
                this.votes.add(new Vote(voter, choice));
            }
        }
    }

    public Poll(String qn, String[] choices, JDA jda, Guild guild, TextChannel channel, Member author) {
        this.question = qn;
        this.choices = choices;
        this.jda = jda;
        this.guild = guild;
        this.channel = channel;
        this.author = author;
        this.id = UUID.randomUUID();
    }

}
