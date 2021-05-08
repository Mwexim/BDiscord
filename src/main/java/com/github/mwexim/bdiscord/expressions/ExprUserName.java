package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.User;

/**
 * The name of a user.
 *
 * @name User Name
 * @type EXPRESSION
 * @pattern [the] [discord] name of %user%
 * @pattern %user%'[s] [discord] name
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserName extends PropertyExpression<String, User> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserName.class,
				String.class,
				true,
				"user",
				"[discord] name");
	}

	@Override
	public String[] getProperty(User[] owners) {
		return new String[] {owners[0].getName()};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "name of " + getOwner().toString(ctx, debug);
	}
}