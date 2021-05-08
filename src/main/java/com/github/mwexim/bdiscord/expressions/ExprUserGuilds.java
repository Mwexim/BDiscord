package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

/**
 * All the guilds of a given user.
 * Note: only the guilds that the current bot and this user have in common will be accounted.
 *
 * @name User Guilds
 * @type EXPRESSION
 * @pattern [the] [discord] guilds of %user%
 * @pattern %user%'[s] [discord] guilds
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserGuilds extends PropertyExpression<Guild, User> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserGuilds.class,
				Guild.class,
				false,
				"user",
				"[discord] guilds");
	}

	@Override
	public Guild[] getProperty(User[] owners) {
		return owners[0].getMutualGuilds().toArray(Guild[]::new);
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "guilds of " + getOwner().toString(ctx, debug);
	}
}