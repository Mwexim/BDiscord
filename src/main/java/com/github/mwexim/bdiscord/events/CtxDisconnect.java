package com.github.mwexim.bdiscord.events;

import io.github.syst3ms.skriptparser.lang.TriggerContext;

/**
 * The context for a DisconnectEvent.
 */
public class CtxDisconnect implements TriggerContext {
	@Override
	public String getName() {
		return "disconnect";
	}
}
