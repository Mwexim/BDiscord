package com.github.mwexim.bdiscord.effects;

import com.github.mwexim.bdiscord.BDiscord;
import com.github.mwexim.bdiscord.utils.BDiscordListener;
import com.github.mwexim.bdiscord.utils.Bot;
import io.github.syst3ms.skriptparser.lang.Effect;
import io.github.syst3ms.skriptparser.lang.Expression;
import io.github.syst3ms.skriptparser.lang.TriggerContext;
import io.github.syst3ms.skriptparser.parsing.ParseContext;
import io.github.syst3ms.skriptparser.util.SkriptDate;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;

import javax.security.auth.login.LoginException;

/**
 * Login with a Discord bot using its unique token.
 * Make sure to never reveal that token to anyone!
 * Only one bot can be logged in at a time.
 *
 * @name Login
 * @type EFFECT
 * @pattern login with [[the] token] %string%
 * @since ALPHA
 * @author Mwexim
 */
public class EffLogin extends Effect {

    static {
        BDiscord.getDiscordRegistration().addEffect(
            EffLogin.class,
            "login with [[the] token] %string%"
        );
    }

    private Expression<String> token;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ParseContext parseContext) {
        token = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public void execute( TriggerContext ctx) {
        var t = token.getSingle(ctx);
        if (t.isEmpty()) {
            return;
        } else if (BDiscord.getBot() != null) {
            System.out.println("Tried to login while another bot was already active. Shutdown that bot first before logging in with this one.");
            return;
        }

        JDA api;
        try {
            api = JDABuilder
                    .createDefault(t.get())
                    .setEventManager(new AnnotatedEventManager())
                    .addEventListeners(new BDiscordListener())
                    .build();

            Bot bot = new Bot(api);
            bot.setLoginTime(SkriptDate.now().getTimestamp());
            BDiscord.setBot(bot);

            api.awaitReady();
        } catch (LoginException ex) {
            System.out.println("There was an exception when logging in. Are you using the right token?");
            ex.printStackTrace();
        } catch (InterruptedException ignored) {}
    }

    @Override
    public String toString(TriggerContext ctx, boolean debug) {
        return "login with token " + token.toString(ctx, debug);
    }
}
