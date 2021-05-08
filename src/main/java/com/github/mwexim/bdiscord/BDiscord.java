package com.github.mwexim.bdiscord;

import com.github.mwexim.bdiscord.registration.ConverterRegistration;
import com.github.mwexim.bdiscord.registration.TypeRegistration;
import com.github.mwexim.bdiscord.utils.Bot;
import io.github.syst3ms.skriptparser.Parser;
import io.github.syst3ms.skriptparser.log.LogEntry;
import io.github.syst3ms.skriptparser.registration.SkriptRegistration;
import io.github.syst3ms.skriptparser.util.FileUtils;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

public class BDiscord {

	private static SkriptRegistration registration;
	@Nullable
	private static Bot bot;

	public static void initAddon() {
		BDiscordAddon addon = new BDiscordAddon();
		registration = new SkriptRegistration(addon);

		// Register the types and converters
		TypeRegistration.register();
		ConverterRegistration.register();

		// Register all other syntax
		try {
			FileUtils.loadClasses(FileUtils.getJarFile(BDiscord.class),
					"com.github.mwexim.bdiscord",
					"effects",
					"events",
					"expressions",
					"sections"
			);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		List<LogEntry> logs = registration.register();
		Parser.printLogs(logs, Calendar.getInstance(), false);
	}

	public static SkriptRegistration getDiscordRegistration() {
		return registration;
	}

	@Nullable
	public static Bot getBot() {
		return bot;
	}

	public static void setBot(Bot bot) {
		BDiscord.bot = bot;
	}
}
