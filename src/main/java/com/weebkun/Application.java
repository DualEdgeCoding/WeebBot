package com.weebkun;

import com.weebkun.commands.Hungry;
import com.weebkun.events.ExampleListener;
import com.weebkun.events.UserJoined;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * @author Weebkun
 * @version 1.0
 */
public class Application {

    private static final String BOT_TOKEN = System.getenv("bot_token");

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.createDefault(BOT_TOKEN).build();

        //listeners for commands
        jda.addEventListener(new ExampleListener());
        jda.addEventListener(new Hungry());

        //listeners for events
        jda.addEventListener(new UserJoined());

    }
}
