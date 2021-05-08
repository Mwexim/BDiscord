package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.base.ConditionalExpression;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Check if the given users are bots.
 * All the values have to be bots in order to return true.
 *
 * @name Is Bot
 * @type CONDITION
 * @pattern %users% (is|are)[( not|n't)] [a] [discord] bot[s]
 * @since ALPHA
 * @author Mwexim
 */
public class CondExprUserIsBot extends ConditionalExpression {

    static {
        BDiscord.getDiscordRegistration().addExpression(
                CondExprUserIsBot.class,
                Boolean.class,
                true,
                "%users% (is|are)[1:( not|n't)] [a] [discord] bot[s]");
    }

    private Expression<User> users;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
        users = (Expression<User>) expressions[0];
        setNegated(parseContext.getParseMark() == 1);
        return true;
    }

    @Override
    public boolean check( TriggerContext ctx) {
        boolean allMatch = Arrays.stream(users.getValues(ctx)).allMatch(User::isBot);
        return isNegated() != allMatch;
    }

    @Override
    public String toString(TriggerContext ctx, boolean debug) {
        return users.toString(ctx, debug) + (isNegated() ? " is not " : " is ") + "a bot";
    }
}
