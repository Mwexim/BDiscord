package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

/**
 * The author of a given message.
 * The author is returned as a member.
 * Members and users are interchangeable, but since members offer more utility, we chose to return that.
 *
 * @name Message Author
 * @type EXPRESSION
 * @pattern [the] author of %message%
 * @pattern %message%'[s] author
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageAuthor extends PropertyExpression<Member, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageAuthor.class,
				Member.class,
				true,
				"message",
				"author");
	}

	@Override
	public Member[] getProperty(Message[] owners) {
		return new Member[] {owners[0].getMember()};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "author of " + getOwner().toString(ctx, debug);
	}
}