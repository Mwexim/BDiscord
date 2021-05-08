package com.github.mwexim.bdiscord.events;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.SkriptEvent;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.Nullable;

/**
 * The message received event.
 * Activated when a message is received by the bot.
 * The bot listens to all messages at default,
 * but you can choose to only listen to private or guild messages.
 *
 * @name Message Received Event
 * @type EVENT
 * @pattern [on] [(private|guild)] message receive
 * @since ALPHA
 * @author Mwexim
 */
public class EvtMessageReceived extends SkriptEvent {
	static {
		BDiscord.getDiscordRegistration().newEvent(EvtMessageReceived.class, "[on] [(1:private|2:guild)] message receive")
				.setHandledContexts(CtxMessageReceived.class)
				.addContextValue(CtxMessageReceived.class, Message.class, true, "message", ctx -> new Message[] {ctx.getMessage()})
				.register();
	}

	int parseMark;

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
		parseMark = parseContext.getParseMark();
		return true;
	}

	@Override
	public boolean check( TriggerContext ctx) {
		if (!(ctx instanceof CtxMessageReceived)) return false;
		CtxMessageReceived evt = (CtxMessageReceived) ctx;

		if (parseMark == 1)
			return evt.getChannel().getType() == ChannelType.PRIVATE;
		else if (parseMark == 2)
			return evt.getChannel().getType() == ChannelType.TEXT;
		return true;
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return (parseMark == 1 ? "private " : (parseMark == 2 ? "guild " : "")) + "message receiving";
	}
}
