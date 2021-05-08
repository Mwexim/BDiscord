package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

/**
 * All of the role mentions in a message.
 *
 * @name Message Role Mentions
 * @type EXPRESSION
 * @pattern [the] mentioned [discord] role[s] of %message%
 * @pattern %message%'[s] mentioned [discord] role[s]
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageMentionsRole extends PropertyExpression<Role, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageMentionsRole.class,
				Role.class,
				false,
				"message",
				"mentioned [discord] role[s]");
	}

	@Override
	public Role[] getProperty(Message[] owners) {
		return owners[0].getMentionedRoles().toArray(Role[]::new);
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "mentioned roles of " + getOwner().toString(ctx, debug);
	}
}