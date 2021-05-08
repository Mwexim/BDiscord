package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.base.ConditionalExpression;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import net.dv8tion.jda.api.entities.Message;

import java.util.Arrays;

/**
 * Check whether the given message is edited or not.
 *
 * @name Message Is Edited
 * @type CONDITION
 * @pattern %messages% (is|are)[( not|n't)] edited
 * @since ALPHA
 * @author Mwexim
 */
public class CondExprMessageIsEdited extends ConditionalExpression {

    static {
        BDiscord.getDiscordRegistration().addExpression(
                CondExprMessageIsEdited.class,
                Boolean.class,
                true,
                "%messages% (is|are)[1:( not|n't)] edited");
    }

    private Expression<Message> message;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
        message = (Expression<Message>) expressions[0];
        setNegated(parseContext.getParseMark() == 1);
        return true;
    }

    @Override
    public boolean check( TriggerContext ctx) {
        boolean allMatch = Arrays.stream(message.getValues(ctx)).allMatch(Message::isEdited);
        return isNegated() != allMatch;
    }

    @Override
    public String toString(TriggerContext ctx, boolean debug) {
        return message.toString(ctx, debug) + (isNegated() ? " is not " : " is ") + "edited";
    }
}
