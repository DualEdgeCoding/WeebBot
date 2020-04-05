package com.weebkun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PollCommand extends ListenerAdapter {
    //syntax: !poll [qn] [choices]
    //qn separated by hyphens.
    //choices separated by comma(,). min. 2 choices.

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        if(!e.getAuthor().isBot() && msg[0].equalsIgnoreCase("!poll")) {
            String[] choices = msg[2].split(",");
            if(choices.length < 2) e.getMessage().getChannel().sendMessage("❌ Error. must have at least 2 choices.").queue();
            Poll poll = new Poll(String.join(" ", msg[1].split("-")), choices, e.getJDA(), e.getGuild(), e.getChannel(), e.getMember());

            //build embed
            EmbedBuilder builder = new EmbedBuilder();

            String embedChoices = String.join("\n", poll.choices);

            builder.setTitle(poll.question).setDescription("Please vote with `!vote [choice]`").setColor(0xeeee11).addField("Choices:", embedChoices, true).setAuthor(poll.author.getEffectiveName());

            //send poll as message
            e.getMessage().getChannel().sendMessage(builder.build()).queue();
        }
    }
}
