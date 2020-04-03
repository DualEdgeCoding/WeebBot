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

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {
            if (e.getMessage().getContentRaw().equals("!corona")) {

                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(0x0040C0).setImage("https://www.cmmonline.com/wp-content/uploads/2018-08-on-a-roll-detail-1200x627.jpg");
                e.getMessage().getChannel().sendMessage(builder.build()).queue();
            }
        }
    }
}
