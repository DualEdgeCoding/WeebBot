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

package com.weebkun.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

/**
 * listener for GuildJoinEvent.
 * sends welcome message to user that just joined.
 * @author weebkun
 * @version 1.1
 * @since 1.0
 */
public class UserJoined extends ListenerAdapter {

    private static final String[] MESSAGES = {
            "[member] joined. You must construct additional pylons.",
            "Never gonna give [member] up. Never let [member] down!",
            "Hey! Listen! [member] has joined!",
            "Ha! [member] has joined! You activated my trap card!",
            "We've been expecting you, [member].",
            "It's dangerous to go alone, take [member]!",
            "Swoooosh. [member] just landed.",
            "Brace yourselves. [member] just joined the server.",
            "A wild [member] appeared.",
            "Hi [member] i'm dad.",
            "Activate operation [member]-has-joined-the-server!"
    };

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        //instantiates new EmbedBuilder for embed message
        EmbedBuilder builder = new EmbedBuilder();
        //gets random index for messages list
        Random r = new Random();
        int num = r.nextInt(MESSAGES.length);

        builder.setColor(65280).setThumbnail(e.getUser().getAvatarUrl()).setTitle("Welcome!").setDescription(e.getUser().getName()).setFooter(MESSAGES[num].replaceAll("[member]", e.getUser().getAsMention()));
        e.getGuild().getDefaultChannel().sendMessage(builder.build()).queue();
    }
}
