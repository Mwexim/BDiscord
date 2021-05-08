package com.github.mwexim.bdiscord.events;

import net.dv8tion.jda.api.entities.Message;

/**
 * The context for the MessageReceivedEvent and for all its substitutes (Private and Guild).
 */
public class CtxMessageReceived extends CtxMessage {
	private final Message message;

	public CtxMessageReceived(Message message) {
		super(message.getIdLong(), message.getChannel());
		this.message = message;
	}

	@Override
	public Message getMessage() {
		return message;
	}

	@Override
	public String getName() {
		return "message received";
	}
}
