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

    public void onMessageReceived(MessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        if (!e.getAuthor().isBot()) {
            if (msg[0].equals("!hungry") && msg.length == 1) {
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
