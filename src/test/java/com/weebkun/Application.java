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

import com.weebkun.commands.PollCommand;
import com.weebkun.util.handlers.PollHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Weebkun
 */
public class Application {

    static List<PollHandler> pollHandlers = new ArrayList<>();

    private static final String BOT_TOKEN = System.getenv("bot_token");

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.create(BOT_TOKEN, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS).disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE).setMemberCachePolicy(MemberCachePolicy.ALL).build();

        jda.addEventListener(new PollCommand());

        for(Guild g : jda.getGuilds()){
            pollHandlers.add(new PollHandler(g));
        }
    }

    /**
     * Used to get the PollHandler of a guild. Pass in the target Guild instance to get the PollHandler of that guild.
     * @param guild - Provided to get the PollHandler of said guild.
     * @return Possibly null PollHandler of guild specified
     */
    public static PollHandler getHandler(@Nonnull Guild guild){
        for(PollHandler p : pollHandlers){
            if(p.getGuild() == guild) {
                return p;
            }
        }
        return null;
    }
}
