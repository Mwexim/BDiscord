package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

/**
 * All of the mentions in a message.
 * Note: only direct mentions are counted. Role mentions and mentions through @everyone are ignored.
 *
 * @name Message User Mentions
 * @type EXPRESSION
 * @pattern [the] mention(s|ed [discord] user[s]) of %message%
 * @pattern %message%'[s] mention(s|ed [discord] user[s])
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageMentionsUser extends PropertyExpression<User, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageMentionsUser.class,
				User.class,
				false,
				"message",
				"mention(s|ed [discord] user[s])");
	}

	@Override
	public User[] getProperty(Message[] owners) {
		return owners[0].getMentionedUsers().toArray(User[]::new);
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "mentioned users of " + getOwner().toString(ctx, debug);
	}
}