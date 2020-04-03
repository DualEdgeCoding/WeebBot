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

import com.weebkun.commands.AddRole;
import com.weebkun.commands.Hungry;
import com.weebkun.commands.RemoveRole;
import com.weebkun.commands.Wuhan;
import com.weebkun.events.ExampleListener;
import com.weebkun.events.Im;
import com.weebkun.events.UserJoined;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

/**
 * @author Weebkun
 * @version 1.2
 * @since 1.0
 */
public class Application {

    private static final String BOT_TOKEN = System.getenv("bot_token");

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.create(BOT_TOKEN, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES).disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE).setMemberCachePolicy(MemberCachePolicy.ALL).build();

        //listeners for commands
        jda.addEventListener(new ExampleListener());
        jda.addEventListener(new Hungry());
        jda.addEventListener(new Wuhan());
        jda.addEventListener(new AddRole());
        jda.addEventListener(new RemoveRole());

        //listeners for events
        jda.addEventListener(new UserJoined());
        jda.addEventListener(new Im());

    }
}
