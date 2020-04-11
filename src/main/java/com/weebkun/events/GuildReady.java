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

package com.weebkun.events;

import com.weebkun.Application;
import com.weebkun.util.handlers.PollHandler;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildReady extends ListenerAdapter {

    public void onGuildReady(GuildReadyEvent e) {
        Application.getPollHandlers().add(new PollHandler(e.getGuild()));
        Application.getLogger().info(String.format("Added PollHandler for %s", e.getGuild().getName()));
    }
}
