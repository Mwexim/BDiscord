package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import io.github.syst3ms.skriptparser.util.SkriptDate;
import net.dv8tion.jda.api.entities.Message;

/**
 * The time this message was last edited.
 * If there were no edits yet, this will return nothing.
 *
 * @name Message Time Edited
 * @type EXPRESSION
 * @pattern [the] [last] edit[ed] time of %message%
 * @pattern %message%'[s] [last] edit[ed] time
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageTimeEdited extends PropertyExpression<SkriptDate, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageTimeEdited.class,
				SkriptDate.class,
				true,
				"message",
				"[last] edit[ed] time");
	}

	@Override
	public SkriptDate[] getProperty(Message[] owners) {
		if (owners[0].getTimeEdited() == null)
			return new SkriptDate[0];
		return new SkriptDate[] {SkriptDate.of(owners[0].getTimeEdited().toInstant().toEpochMilli())};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "last edit time of " + getOwner().toString(ctx, debug);
	}
}