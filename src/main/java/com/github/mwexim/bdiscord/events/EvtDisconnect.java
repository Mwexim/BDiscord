package com.github.mwexim.bdiscord.events;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.SkriptEvent;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.parsing.ParseContext;

/**
 * The disconnect event.
 * Activated when a Discord bot disconnects.
 *
 * @name Disconnect Event
 * @type EVENT
 * @pattern [on] disconnect
 * @since ALPHA
 * @author Mwexim
 */
public class EvtDisconnect extends SkriptEvent {

	static {
		BDiscord.getDiscordRegistration().newEvent(EvtDisconnect.class, "disconnect")
				.setHandledContexts(CtxDisconnect.class)
				.register();
	}

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
		return true;
	}

	@Override
	public boolean check(TriggerContext ctx) {
		return ctx instanceof CtxDisconnect;
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "disconnecting";
	}
}
