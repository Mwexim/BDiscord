package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.User;

/**
 * The avatar URL of a user.
 * This is the link to the image the given user uses as avatar.
 * If the user has no image set, this will return its default avatar url.
 * Discord applies a default avatar to all of its users. Mostly, this is just a coloured logo
 * (like yellow or blue). When the user does not have an avatar, it uses that default url.
 *
 * @name User Avatar URL
 * @type EXPRESSION
 * @pattern [the] [discord] avatar url of %user%
 * @pattern %user%'[s] [discord] avatar url
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserAvatarURL extends PropertyExpression<String, User> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserAvatarURL.class,
				String.class,
				true,
				"user",
				"[discord] avatar url");
	}

	@Override
	public String[] getProperty(User[] owners) {
		return new String[] {owners[0].getEffectiveAvatarUrl()};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "avatar url of " + getOwner().toString(ctx, debug);
	}
}