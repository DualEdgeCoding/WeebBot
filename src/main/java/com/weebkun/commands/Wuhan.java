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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Event listener for Coronavirus command.
 * @author weebkun
 * @version 1.1
 * @since 1.0
 */
public class Wuhan extends ListenerAdapter {

    //syntax: !corona
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {
            if (e.getMessage().getContentRaw().equalsIgnoreCase("!corona")) {

                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(0x0040C0).setImage("https://www.cmmonline.com/wp-content/uploads/2018-08-on-a-roll-detail-1200x627.jpg");
                e.getMessage().getChannel().sendMessage(builder.build()).queue();
            }
        }
    }
}
