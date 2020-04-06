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

package com.weebkun.commands;

import com.weebkun.Application;
import com.weebkun.util.models.Poll;
import com.weebkun.util.handlers.PollHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PollCommand extends ListenerAdapter {
    //syntax: !poll [qn] [choices]
    //qn separated by hyphens.
    //choices separated by comma(,). min. 2 choices.

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        if(!e.getAuthor().isBot() && msg[0].equalsIgnoreCase("!poll")) {
            String[] choices = msg[2].split(",");
            if(choices.length < 2) e.getMessage().getChannel().sendMessage("❌ Error. must have at least 2 choices.").queue();
            Poll poll = new Poll(String.join(" ", msg[1].split("-")), choices, e.getJDA(), e.getGuild(), e.getChannel(), e.getMember());

            //build embed
            EmbedBuilder builder = new EmbedBuilder();
            String embedChoices = String.join("\n", poll.getChoices());
            builder.setTitle(poll.getQuestion()).setDescription("Please vote with `!vote [choice]`\nID: " + poll.getId().toString()).setColor(0xeeee11).addField("Choices:", embedChoices, true).setAuthor(poll.getAuthor().getEffectiveName());

            //send poll as message
            e.getMessage().getChannel().sendMessage(builder.build()).queue();

            //register poll to poll handler
            PollHandler handler = Application.getHandler(e.getGuild());
            if(handler != null){
                handler.startPoll(poll);
            } else{
                handler = new PollHandler(e.getGuild());
                handler.startPoll(poll);
                Application.getLogger().error("Handler not found.");
            }
        }
    }
}
