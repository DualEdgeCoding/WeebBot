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

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Class for "hungry" commands
 * @author weebkun
 * @version 1.1
 * @since 1.0
 */
public class Hungry extends ListenerAdapter {

    //syntax: !hungry [optional: name]

    public void onMessageReceived(MessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        if (!e.getAuthor().isBot()) {
            if (msg[0].equalsIgnoreCase("!hungry") && msg.length == 1) {
                e.getMessage().getChannel().sendMessage("Hi hungry im dad").queue();
            } else if (msg.length == 2 && msg[0].equals("!hungry")){
                e.getMessage().getChannel().sendMessage("Hi hungry im " + msg[1]).queue();
            }

            if (msg[0].equals("!imback")) {
                e.getMessage().getChannel().sendMessage("Hi back.").queue();
            }
        }
    }
}
