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
import com.weebkun.util.handlers.PollHandler;
import com.weebkun.util.models.Poll;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoteCommand extends ListenerAdapter {

    //!vote [choice]

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String[] msg = e.getMessage().getContentRaw().split(" ");
        if(msg.length < 2 && !e.getAuthor().isBot()) {
            e.getMessage().getChannel().sendMessage("❌ Please specify the choice you want to vote.").queue();
            return;
        }
        if(!e.getAuthor().isBot() && msg[0].equalsIgnoreCase("!vote")) {
            //get this guild's pollhandler
            PollHandler handler = Application.getHandler(e.getGuild());
            for(Poll p : handler.getPolls()){
                if(p.getChannel() == e.getMessage().getChannel()){
                    //add vote to poll
                    p.vote(e.getMember(), msg[1]);
                    //send confirmation msg
                    e.getMessage().getChannel().sendMessage(String.format("☑ %s successfully voted for: %s", e.getAuthor().getName(), msg[1])).queue();
                }
            }
        }
    }
}
