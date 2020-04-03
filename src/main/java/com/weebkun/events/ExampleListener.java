package com.weebkun.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

/**
 * @author weebkun
 * @version 1.1
 * @since 1.0
 */
public class ExampleListener implements EventListener {

    /**
     * Implements onEvent method from EventListener.
     * onEvent checks whether event e is of type MessageReceivedEvent, then checks if the message is "!ping".
     * if "!ping", send "pong!" back to channel.
     *
     * @param e - GenericEvent event
     */
    @Override
    public void onEvent(GenericEvent e) {

        if(e instanceof MessageReceivedEvent){
            if(((MessageReceivedEvent) e).getAuthor().isBot()) return;
            String content = ((MessageReceivedEvent) e).getMessage().getContentRaw();
            if(content.equals("!ping")) {
                MessageChannel channel = ((MessageReceivedEvent) e).getChannel();
                channel.sendMessage("Pong!").queue();
            }
        }
    }

}
