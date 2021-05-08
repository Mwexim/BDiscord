package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import net.dv8tion.jda.api.entities.Message;

/**
 * The content of a message.
 * Note: at default, the content is stripped. This means that all the Markdown symbols are removed.
 * You can choose to return the display of the content. This will include Markdown symbols.
 * If you choose to output the raw version,
 * all mentions (like <b>@Mwexim</b>) will be resolved to <b><@id></b> instead.
 *
 * @name Message Content
 * @type EXPRESSION
 * @pattern [the] [(1:raw|2:display[ed])] content of %message%
 * @pattern %message%'[s] [(1:raw|2:display[ed])] content
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageContent extends PropertyExpression<String, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageContent.class,
				String.class,
				true,
				"message",
				"[(1:raw|2:display[ed])] content");
	}

	int parseMark;

	@Override
	public String[] getProperty(Message[] owners) {
		switch (parseMark) {
			case 1:
				return new String[] {owners[0].getContentRaw()};
			case 2:
				return new String[] {owners[0].getContentDisplay()};
			default:
				return new String[] {owners[0].getContentStripped()};
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
		setOwner((Expression<Message>) expressions[0]);
		parseMark = parseContext.getParseMark();
		return true;
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "content of " + getOwner().toString(ctx, debug);
	}
}