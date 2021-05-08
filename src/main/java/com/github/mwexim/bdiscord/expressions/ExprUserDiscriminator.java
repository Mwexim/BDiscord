package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.User;

/**
 * The discriminator of a user.
 * Each user has a tag, e.g. <i>Mwexim#3563</i>. In this case, the discriminator would be 3563.
 * It always has 4 digits but is not unique for each user.
 *
 * @name User Discriminator
 * @type EXPRESSION
 * @pattern [the] [discord] discriminator of %user%
 * @pattern %user%'[s] [discord] discriminator
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserDiscriminator extends PropertyExpression<String, User> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserDiscriminator.class,
				String.class,
				true,
				"user",
				"[discord] discriminator");
	}

	@Override
	public String[] getProperty(User[] owners) {
		return new String[] {owners[0].getDiscriminator()};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "discriminator of " + getOwner().toString(ctx, debug);
	}
}