package com.github.mwexim.bdiscord.events;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.SkriptEvent;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.parsing.ParseContext;

/**
 * The ready event.
 * Activated when a Discord bot successfully logs in.
 *
 * @name Ready Event
 * @type EVENT
 * @pattern [on] ready
 * @since ALPHA
 * @author Mwexim
 */
public class EvtReady extends SkriptEvent {

	static {
		BDiscord.getDiscordRegistration().newEvent(EvtReady.class, "ready")
				.setHandledContexts(CtxReady.class)
				.register();
	}

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
		return true;
	}

	@Override
	public boolean check( TriggerContext ctx) {
		return ctx instanceof CtxReady;
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "ready";
	}
}
