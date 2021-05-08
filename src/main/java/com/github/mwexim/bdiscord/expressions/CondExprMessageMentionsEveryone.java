package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.base.ConditionalExpression;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Check if the given message mentions everyone.
 * This is done by using <b>@everyone</b> or <b>@here</b>.
 * All the values have to be mention everyone in order to return true.
 *
 * @name Message Mentions Everyone
 * @type CONDITION
 * @pattern %messages% [do[es](n't| not)] mention[s] everyone
 * @since ALPHA
 * @author Mwexim
 */
public class CondExprMessageMentionsEveryone extends ConditionalExpression {

    static {
        BDiscord.getDiscordRegistration().addExpression(
                CondExprMessageMentionsEveryone.class,
                Boolean.class,
                true,
                "%messages% [1:do[es](n't| not)] mention[s] everyone");
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
        boolean allMatch = Arrays.stream(message.getValues(ctx)).allMatch(Message::mentionsEveryone);
        return isNegated() != allMatch;
    }

    @Override
    public String toString(TriggerContext ctx, boolean debug) {
        return message.toString(ctx, debug) + (isNegated() ? " does not mention " : " mentions ") + "everyone";
    }
}
