package com.github.mwexim.bdiscord.events;

import io.github.syst3ms.skriptparser.lang.TriggerContext;

/**
 * The context for a ReadyEvent.
 */
public class CtxReady implements TriggerContext {
	@Override
	public String getName() {
		return "ready";
	}
}
