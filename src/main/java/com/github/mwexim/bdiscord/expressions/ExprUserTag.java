package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.User;

/**
 * The tag of a given user.
 * This includes the name, a hashtag (#) and the user's discriminator.
 *
 * @name User Tag
 * @type EXPRESSION
 * @pattern [the] [discord] tag of %user%
 * @pattern %user%'[s] [discord] tag
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserTag extends PropertyExpression<String, User> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserTag.class,
				String.class,
				true,
				"user",
				"[discord] tag");
	}

	@Override
	public String[] getProperty(User[] owners) {
		return new String[] {owners[0].getAsTag()};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "tag of " + getOwner().toString(ctx, debug);
	}
}