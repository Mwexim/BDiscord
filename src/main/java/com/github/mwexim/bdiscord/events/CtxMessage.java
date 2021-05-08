package com.github.mwexim.bdiscord.events;

import io.github.syst3ms.skriptparser.lang.TriggerContext;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

/**
 * The context for any GenericMessageEvent and its substitutes (Private and Guild).
 */
public class CtxMessage implements TriggerContext {
	private final long id;
	private final MessageChannel channel;

	public CtxMessage(long id, MessageChannel channel) {
		this.id = id;
		this.channel = channel;
	}

	public long getId() {
		return id;
	}

	public MessageChannel getChannel() {
		return channel;
	}

	public Message getMessage() {
		return channel.retrieveMessageById(id).complete();
	}

	@Override
	public String getName() {
		return "message";
	}
}
