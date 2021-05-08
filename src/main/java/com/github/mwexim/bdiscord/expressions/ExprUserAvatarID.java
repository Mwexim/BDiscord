package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.User;

/**
 * The avatar ID of a user.
 * This is a certain enumeration of digits that is unique for each avatar.
 * If the user has no image set, this will return its default avatar id.
 * Discord applies a default avatar to all of its users. Mostly, this is just a coloured logo
 * (like yellow or blue). When the user does not have an avatar, it uses that default id.
 *
 * @name User Avatar ID
 * @type EXPRESSION
 * @pattern [the] [discord] avatar id of %user%
 * @pattern %user%'[s] [discord] avatar id
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserAvatarID extends PropertyExpression<String, User> {
	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserAvatarID.class,
				String.class,
				true,
				"user",
				"[discord] avatar id");
	}

	@Override
	public String[] getProperty(User[] owners) {
		return new String[] {owners[0].getAvatarId() == null
				? owners[0].getDefaultAvatarId()
				: owners[0].getAvatarId()
		};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "avatar id of " + getOwner().toString(ctx, debug);
	}
}