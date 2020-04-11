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

import javax.annotation.Nonnull;

public class EndPoll extends ListenerAdapter {

    //syntax: !endpoll [id]
    //only the author of original poll gets to end it.

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        if(!e.getAuthor().isBot() && msg[0].equalsIgnoreCase("!endpoll")){
            try {
                PollHandler handler = Application.getHandler(e.getGuild());
                Poll poll = handler.getPoll(msg[1]);
                handler.endPoll(poll);
            } catch (NullPointerException exp){
                e.getMessage().getChannel().sendMessage("❌ Sorry the id you provided might be incorrect. Please try again.").queue();
            }
        }
    }
}
