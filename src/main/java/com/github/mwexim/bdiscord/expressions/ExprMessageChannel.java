package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

/**
 * The channel this message was sent in.
 * This will always be a message channel,
 * as this is the only type of channels messages can be send in.
 *
 * @name Message Channel
 * @type EXPRESSION
 * @pattern [the] [discord] channel of %message%
 * @pattern %message%'[s] [discord] channel
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageChannel extends PropertyExpression<MessageChannel, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageChannel.class,
				MessageChannel.class,
				true,
				"message",
				"[discord] channel");
	}

	@Override
	public MessageChannel[] getProperty(Message[] owners) {
		return new MessageChannel[] {owners[0].getChannel()};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "channel of " + getOwner().toString(ctx, debug);
	}
}