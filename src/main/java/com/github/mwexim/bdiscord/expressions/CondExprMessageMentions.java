package com.github.mwexim.bdiscord.expressions;

import com.github.mwexim.bdiscord.BDiscord;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.lang.base.ConditionalExpression;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Check if the given users/roles/textchannels are mentioned in a given message.
 * All the values have to be mentioned in all messages in order to return true.
 *
 * @name Message Mentions
 * @type CONDITION
 * @pattern %messages% [do[es](n't| not)] mention[s] %users/roles/textchannels%
 * @since ALPHA
 * @author Mwexim
 */
public class CondExprMessageMentions extends ConditionalExpression {

    static {
        BDiscord.getDiscordRegistration().addExpression(
                CondExprMessageMentions.class,
                Boolean.class,
                true,
                4,
                "%messages% [1:do[es](n't| not)] mention[s] %users%",
                "%messages% [1:do[es](n't| not)] mention[s] %roles%",
                "%messages% [1:do[es](n't| not)] mention[s] %textchannels%");
    }

    private Expression<Message> message;
    private Expression<?> mentioned;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
        message = (Expression<Message>) expressions[0];
        mentioned = expressions[1];
        setNegated(parseContext.getParseMark() == 1);
        return true;
    }

    @Override
    public boolean check( TriggerContext ctx) {
        boolean allMatch = Arrays.stream(message.getValues(ctx)).allMatch(msg -> Arrays.stream(mentioned.getValues(ctx)).allMatch(ment -> {
            if (ment instanceof User) return msg.isMentioned((User) ment, Message.MentionType.USER);
            if (ment instanceof Role) return msg.isMentioned((Role) ment, Message.MentionType.ROLE);
            if (ment instanceof TextChannel) return msg.isMentioned((TextChannel) ment, Message.MentionType.CHANNEL);
            return false;
        }));
        return isNegated() != allMatch;
    }

    @Override
    public String toString(TriggerContext ctx, boolean debug) {
        return message.toString(ctx, debug) + (isNegated() ? " does not mention " : " mentions ") + message.toString();
    }
}
