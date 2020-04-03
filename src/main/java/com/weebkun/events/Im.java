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

    public void onMessageReceived(MessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        String[] newMsgList = new String[msg.length - 1];
        StringJoiner joiner = new StringJoiner(" ");
        if (msg[0].equals("im") && msg.length > 1 && !e.getAuthor().isBot()) {
            for (int i = 1; i <= msg.length - 1; i++) {
                newMsgList[i - 1] = msg[i];
                joiner.add(newMsgList[i-1]);
            }
            String result = joiner.toString();
            e.getMessage().getChannel().sendMessage("Hi " + result + " I'm WeebBot.").queue();
        }
    }

}
