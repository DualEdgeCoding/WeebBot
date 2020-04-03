package com.weebkun.commands;

import com.weebkun.Util.Exceptions.NullMemberException;
import com.weebkun.Util.Exceptions.NullRoleException;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author weebkun
 * @version 1.2
 * @since 1.2
 */
public class RemoveRole extends ListenerAdapter {

    //syntax: !removerole [role] [member]

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) throws IllegalArgumentException {
        String[] msg = e.getMessage().getContentRaw().split(" ");
        Guild guild = e.getGuild();
        try {
            if (!e.getAuthor().isBot() && msg[0].equalsIgnoreCase("!removerole")) {
                Member member = guild.getMemberByTag(msg[2]);
                if(member == null) throw new NullMemberException();
                // check if member currently has role specified.
                for (Role r : member.getRoles()) {
                    if(r.getName().equalsIgnoreCase(msg[1].replace("-", " "))) {
                        guild.removeRoleFromMember(member, r).queue();
                        e.getMessage().getChannel().sendMessage(String.format("Role %s has been successfully removed from %s.✔", r.getName(), member.getNickname())).queue();
                        return;
                    }
                }
                e.getMessage().getChannel().sendMessage("❌ Sorry, the member specified does not have the role.").queue();
            }
        } catch (IllegalArgumentException | NullRoleException | NullMemberException exp) {
            System.out.println("Addrole command encountered wrong argument.");
            System.out.println(exp);
            e.getMessage().getChannel().sendMessage("❌ An Internal Exception was caught:\n" + exp).queue();
        }
    }

}
