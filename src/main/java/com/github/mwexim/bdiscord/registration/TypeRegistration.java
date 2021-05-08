package com.github.mwexim.bdiscord.registration;

import com.github.mwexim.bdiscord.BDiscord;
import com.github.mwexim.bdiscord.utils.Bot;
import io.github.syst3ms.skriptparser.registration.SkriptRegistration;
import net.dv8tion.jda.api.entities.*;

/**
 * Registrar for all the types.
 */
public class TypeRegistration {

	public static void register() {
		SkriptRegistration registration = BDiscord.getDiscordRegistration();

		// Classes
		registration.newType(Bot.class, "bot", "bot@s")
				.toStringFunction(o -> o.getSelfUser().getAsTag())
				.register();
		registration.newType(Member.class, "member", "member@s")
				.toStringFunction(o -> o.getUser().getAsTag())
				.register();
		registration.newType(User.class, "user", "user@s")
				.toStringFunction(User::getAsTag)
				.register();

		registration.newType(Role.class, "role", "role@s")
				.toStringFunction(Role::getName)
				.register();

		registration.newType(Message.class, "message", "message@s")
				.toStringFunction(Message::getContentStripped)
				.register();

		registration.newType(Guild.class, "guild", "guild@s")
				.toStringFunction(Guild::getName)
				.register();

		registration.newType(TextChannel.class, "textchannel", "textchannel@s")
				.toStringFunction(TextChannel::getName)
				.register();
		registration.newType(VoiceChannel.class, "voicechannel", "voicechannel@s")
				.toStringFunction(VoiceChannel::getName)
				.register();
		registration.newType(Category.class, "category", "categor@y@ies")
				.toStringFunction(Category::getName)
				.register();
		registration.newType(MessageChannel.class, "messagechannel", "messagechannel@s")
				.toStringFunction(MessageChannel::getName)
				.register();
		registration.newType(GuildChannel.class, "channel", "channel@s")
				.toStringFunction(GuildChannel::getName)
				.register();

		// Enumerations
		registration.newType(ChannelType.class, "channeltype", "channeltype@s")
				.literalParser(s -> {
					if (s.toLowerCase().equals(s))
						try {
							return ChannelType.valueOf(s.toUpperCase());
						} catch (IllegalArgumentException ex) {
							return null;
						}
					return null;
				})
				.toStringFunction(o -> o.name().toLowerCase())
				.register();

		registration.register(); // Ignoring logs here, we control the input.
	}

}
