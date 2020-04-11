/*
Copyright 2020 Weebkun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.weebkun;

import com.weebkun.commands.*;
import com.weebkun.events.ExampleListener;
import com.weebkun.events.GuildReady;
import com.weebkun.events.Im;
import com.weebkun.events.UserJoined;
import com.weebkun.util.handlers.PollHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weebkun
 * @version 1.2
 * @since 1.0
 */
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    private static List<PollHandler> pollHandlers = new ArrayList<>();

    private static final String BOT_TOKEN = System.getenv("bot_token");

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.create(BOT_TOKEN, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS).disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE).setMemberCachePolicy(MemberCachePolicy.ALL).build();

        //listeners for commands
        jda.addEventListener(new ExampleListener());
        jda.addEventListener(new Hungry());
        jda.addEventListener(new Wuhan());
        jda.addEventListener(new AddRole());
        jda.addEventListener(new RemoveRole());
        jda.addEventListener(new PollCommand());
        jda.addEventListener(new VoteCommand());
        jda.addEventListener(new EndPoll());

        //listeners for events
        jda.addEventListener(new UserJoined());
        jda.addEventListener(new Im());
        jda.addEventListener(new GuildReady());

}

    /**
     * Used to get the PollHandler of a guild. Pass in the target Guild instance to get the PollHandler of that guild.
     * @param guild - Provided to get the PollHandler of said guild.
     * @return Possibly null PollHandler of guild specified
     */
    public static PollHandler getHandler(@Nonnull Guild guild){
        for(PollHandler p : pollHandlers){
            if(p.getGuild().equals(guild)) {
                return p;
            }
        }
        return null;
    }

    public static Logger getLogger(){
        return logger;
    }

    public static List<PollHandler> getPollHandlers() {
        return pollHandlers;
    }
}
