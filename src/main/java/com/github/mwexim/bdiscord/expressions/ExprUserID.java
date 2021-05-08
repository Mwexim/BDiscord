package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.User;

import java.math.BigInteger;

/**
 * The ID of a user.
 * This is a certain enumeration of digits that is unique for each bot and user.
 *
 * @name User ID
 * @type EXPRESSION
 * @pattern [the] [discord] id of %user%
 * @pattern %user%'[s] [discord] id
 * @since ALPHA
 * @author Mwexim
 */
public class ExprUserID extends PropertyExpression<Number, User> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprUserID.class,
				Number.class,
				true,
				"user",
				"[discord] id");
	}

	@Override
	public Number[] getProperty(User[] owners) {
		return new BigInteger[] {BigInteger.valueOf(owners[0].getIdLong())};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "id of " + getOwner().toString(ctx, debug);
	}
}