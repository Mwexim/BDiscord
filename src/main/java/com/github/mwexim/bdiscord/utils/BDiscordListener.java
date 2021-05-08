package com.github.mwexim.bdiscord.utils;

import com.github.mwexim.bdiscord.BDiscordAddon;
import com.github.mwexim.bdiscord.events.*;
import io.github.syst3ms.skriptparser.lang.SkriptEvent;
import io.github.syst3ms.skriptparser.lang.Statement;
import io.github.syst3ms.skriptparser.lang.Trigger;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

public class BDiscordListener {

	@SubscribeEvent
	public void onReady(ReadyEvent event) {
		runTrigger(EvtReady.class, new CtxReady());
	}

	@SubscribeEvent
	public void onDisconnect(DisconnectEvent event) {
		runTrigger(EvtDisconnect.class, new CtxDisconnect());
	}

	@SubscribeEvent
	public void onMessage(MessageReceivedEvent event) {
		runTrigger(EvtMessageReceived.class, new CtxMessageReceived(event.getMessage()));
	}

	private static void runTrigger(Class<? extends SkriptEvent> event, TriggerContext ctx) {
		for (Trigger trig : BDiscordAddon.getTriggers())
			if (trig.getEvent().getClass().equals(event))
				Statement.runAll(trig, ctx);

	}
}
