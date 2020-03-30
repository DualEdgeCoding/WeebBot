package com.weebkun;

import com.weebkun.events.ExampleListener;
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

        jda.addEventListener(new ExampleListener());

    }
}
