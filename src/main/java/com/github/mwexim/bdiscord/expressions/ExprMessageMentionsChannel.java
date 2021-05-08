package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.properties.PropertyExpression;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * All of the channel mentions in a message.
 *
 * @name Message Channel Mentions
 * @type EXPRESSION
 * @pattern [the] mentioned [discord] channel[s] of %message%
 * @pattern %message%'[s] mentioned [discord] channel[s]
 * @since ALPHA
 * @author Mwexim
 */
public class ExprMessageMentionsChannel extends PropertyExpression<TextChannel, Message> {

	static {
		BDiscord.getDiscordRegistration().addPropertyExpression(
				ExprMessageMentionsChannel.class,
				TextChannel.class,
				false,
				"message",
				"mentioned [discord] channel[s]");
	}

	@Override
	public TextChannel[] getProperty(Message[] owners) {
		return owners[0].getMentionedChannels().toArray(new TextChannel[0]);
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "mentioned channels of " + getOwner().toString(ctx, debug);
	}
}