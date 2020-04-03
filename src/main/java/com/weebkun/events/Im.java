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

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * special event listener
 * @author weebkun
 * @version 1.1
 * @since 1.1
 */
public class Im extends ListenerAdapter {

    // expect: im [name]

    public void onMessageReceived(MessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        String[] newMsgList = new String[msg.length - 1];
        StringJoiner joiner = new StringJoiner(" ");
        if (msg[0].equalsIgnoreCase("im") && msg.length > 1 && !e.getAuthor().isBot()) {
            for (int i = 1; i <= msg.length - 1; i++) {
                newMsgList[i - 1] = msg[i];
                joiner.add(newMsgList[i-1]);
            }
            String result = joiner.toString();
            e.getMessage().getChannel().sendMessage("Hi " + result + " I'm WeebBot.").queue();
        }
    }

}
