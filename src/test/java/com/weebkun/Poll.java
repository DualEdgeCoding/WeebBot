package com.weebkun;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * Poll class for creating strawpolls.
 * @author weebkun
 */
public class Poll {
    /**
     * question of strawpoll.
     */
    String question;
    /**
     * string array of choices. must have at least 2.
     */
    String[] choices;
    /**
     * member instance of author.
     */
    Member author;

    private JDA jda;
    private Guild guild;
    private TextChannel channel;

    /**
     * jda instance of bot.
     * @return jda instance passed to constructor.
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * instance of guild that the poll was setup in.
     * @return Guild instance.
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * channel that command was sent in.
     * @return Channel instance.
     */
    public TextChannel getChannel() {
        return channel;
    }

    public Poll(String qn, String[] choices, JDA jda, Guild g, TextChannel ch, Member author) {
        this.question = qn;
        this.choices = choices;
        this.jda = jda;
        this.guild = g;
        this.channel = ch;
        this.author = author;
    }


}
