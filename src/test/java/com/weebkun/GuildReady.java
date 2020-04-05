package com.weebkun;

import com.weebkun.util.handlers.PollHandler;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildReady extends ListenerAdapter {

    public void onGuildReady(GuildReadyEvent e) {
        Application.pollHandlers.add(new PollHandler(e.getGuild()));
        System.out.printf("added pollhandler for %s\n", e.getGuild().getName());
    }
}
