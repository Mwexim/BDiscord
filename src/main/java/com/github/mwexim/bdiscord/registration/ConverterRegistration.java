package com.github.mwexim.bdiscord.registration;

import com.github.mwexim.bdiscord.BDiscord;
import com.github.mwexim.bdiscord.utils.Bot;
import io.github.syst3ms.skriptparser.registration.SkriptRegistration;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.Optional;

/**
 * Registrar for all the converters.
 */
public class ConverterRegistration {

	public static void register() {
		SkriptRegistration registration = BDiscord.getDiscordRegistration();

		registration.addConverter(Bot.class, User.class, val -> Optional.of(val.getSelfUser()));
		registration.addConverter(Member.class, User.class, val -> Optional.of(val.getUser()));

		registration.register(); // Ignoring logs here, we control the input.
	}

}
