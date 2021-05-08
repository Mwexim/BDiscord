package com.github.mwexim.bdiscord;

import com.github.mwexim.bdiscord.events.EvtDisconnect;
import com.github.mwexim.bdiscord.events.EvtMessageReceived;
import com.github.mwexim.bdiscord.events.EvtReady;
import io.github.syst3ms.skriptparser.lang.SkriptEvent;
import io.github.syst3ms.skriptparser.lang.Trigger;
import io.github.syst3ms.skriptparser.registration.SkriptAddon;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link SkriptAddon} representing the Discord addon for Skript.
 */
public class BDiscordAddon extends SkriptAddon {

    private static final List<Trigger> triggers = new ArrayList<>();

    @Override
    public void handleTrigger(Trigger trigger) {
        SkriptEvent event = trigger.getEvent();

        if (!canHandleEvent(event))
            return;

        if (event instanceof EvtReady)
            triggers.add(trigger);
        else if (event instanceof EvtDisconnect)
            triggers.add(trigger);
        else if (event instanceof EvtMessageReceived)
            triggers.add(trigger);
        
    }

    @Override
    public void finishedLoading() { /* Nothing */ }

    public static List<Trigger> getTriggers() {
        return triggers;
    }
}
