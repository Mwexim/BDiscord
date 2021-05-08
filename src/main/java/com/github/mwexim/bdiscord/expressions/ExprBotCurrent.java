package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import com.github.mwexim.bdiscord.utils.Bot;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import org.jetbrains.annotations.Nullable;

/**
 * The bot that is currently logged in.
 * If not bot is logged in, or the bot is shut down, this will return nothing.
 *
 * @name Bot
 * @type EXPRESSION
 * @pattern [the] current [discord] bot
 * @since ALPHA
 * @author Mwexim
 */
public class ExprBotCurrent implements Expression<Bot> {

	static {
		BDiscord.getDiscordRegistration().addExpression(
				ExprBotCurrent.class,
				Bot.class,
				true,
				"[the] current [discord] bot");
	}

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
		return true;
	}

	@Override
	public Bot[] getValues( TriggerContext ctx) {
		Bot b = BDiscord.getBot();
		if (b == null) return new Bot[0];

		return new Bot[] {b};
	}

	@Override
	public String toString(TriggerContext ctx, boolean debug) {
		return "current bot";
	}
}