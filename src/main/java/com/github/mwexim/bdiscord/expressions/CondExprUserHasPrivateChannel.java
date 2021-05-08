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
 * Checks if the current bot has a private channel opened with given users.
 *
 * @name User Has Private Channek
 * @type CONDITION
 * @pattern %users% ((has|have)|do[es]( not|n't) have) [a] [private] channel
 * @since ALPHA
 * @author Mwexim
 */
public class CondExprUserHasPrivateChannel extends ConditionalExpression {

    static {
        BDiscord.getDiscordRegistration().addExpression(
                CondExprUserHasPrivateChannel.class,
                Boolean.class,
                true,
                "%users% ((has|have)|1:do[es]( not|n't) have) [a] [private] channel");
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
        boolean allMatch = Arrays.stream(users.getValues(ctx)).allMatch(User::hasPrivateChannel);
        return isNegated() != allMatch;
    }

    @Override
    public String toString(TriggerContext ctx, boolean debug) {
        return users.toString(ctx, debug) + (isNegated() ? " has not " : " has ") + "a private channel";
    }
}
