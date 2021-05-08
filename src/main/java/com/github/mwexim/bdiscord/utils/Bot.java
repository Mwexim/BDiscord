package com.github.mwexim.bdiscord.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.SelfUser;
import org.jetbrains.annotations.NotNull;

public class Bot implements IMentionable, ISnowflake {

	private final JDA jda;
	private final SelfUser selfUser;
	private long uptime;

	public Bot(JDA jda) {
		this.jda = jda;
		this.selfUser = jda.getSelfUser();
	}

	public void setLoginTime(long uptime) {
		this.uptime = uptime;
	}

	// Getters \\
	public JDA getJDA() {
		return this.jda;
	}

	public SelfUser getSelfUser() {
		return selfUser;
	}

	public String getName() {
		return this.selfUser.getName();
	}

	public long getUptime() {
		return uptime;
	}

	@Override
	public String getAsMention() {
		return selfUser.getAsMention();
	}

	@Override
	public long getIdLong() {
		return selfUser.getIdLong();
	}

	@Override
	public String toString() {
		return getName();
	}

}
