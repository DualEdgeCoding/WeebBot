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

package com.weebkun.commands;

import com.weebkun.Util.Exceptions.NullMemberException;
import com.weebkun.Util.Exceptions.NullRoleException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

/**
 * event listener class for adding role command.
 * @author weebkun
 * @version 1.2
 * @since 1.2
 */
public class AddRole extends ListenerAdapter {

    // syntax: !addrole [role] [member]
    // expect: 2 arguments role and member.
    // member arg is nickname of user
    // if role name contains spaces, separate with dashes.

    /**
     * gets message from event and splits into respective string arguments.
     * arguments for command are role and member. role is name of role and member is username and tag of user.
     * @param e - guild message received event object
     */
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) throws IllegalArgumentException {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        Guild guild = e.getGuild();
        try {
            if (!e.getAuthor().isBot() && msg[0].equalsIgnoreCase("!addrole")) {
                // TODO clean up this mess
                Member member = null;
                List<Member> members = guild.getMembers();
                for(Member mem : members){
                    if(mem.getEffectiveName().equalsIgnoreCase(msg[2])){
                        member = mem;
                    }
                }

                if(member == null) throw new NullMemberException();
                // search for role with name provided by looping through each role in guild.
                for (Role r : guild.getRoles()) {
                    if (r.getName().equalsIgnoreCase(msg[1].replace("-", " "))) {
                        guild.addRoleToMember(member, r).queue();
                        e.getMessage().getChannel().sendMessage(String.format("%s now has Role %s.✔", member.getNickname(), r.getName())).queue();
                        return;
                    }
                }
                e.getMessage().getChannel().sendMessage("❌ Sorry. Couldn't find the role specified. Remember to replace the spaces in the role with hyphens. Syntax:`!addrole [role] [member]`").queue();
            }
        } catch (IllegalArgumentException | NullRoleException | NullMemberException exp) {
            System.out.println("Addrole command encountered wrong argument.");
            System.out.println(exp);
            e.getMessage().getChannel().sendMessage("❌ An Internal Exception was caught:\n" + exp).queue();
        }
    }
}
