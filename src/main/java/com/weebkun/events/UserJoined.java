package com.weebkun.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * listener for GuildJoinEvent.
 * sends welcome message to user that just joined.
 * @author weebkun
 */
public class UserJoined extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setColor(65280).setThumbnail(e.getUser().getAvatarUrl()).setTitle("Welcome!").setDescription(e.getUser().getName());
        e.getGuild().getDefaultChannel().sendMessage(builder.build()).queue();
    }
}
