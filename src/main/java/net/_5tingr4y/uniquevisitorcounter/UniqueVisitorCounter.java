package net._5tingr4y.uniquevisitorcounter;

import com.google.inject.Inject;
import net._5tingr4y.uniquevisitorcounter.commands.CommandInitializer;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

@Plugin(
        id = "uniquevisitorcounter",
        name = "UniqueVisitorCounter",
        description = "A simple plugin that tracks the number of individual visitors to a server.",
        authors = {
                "5tingr4y"
        }
)
public class UniqueVisitorCounter {

    @Inject
    private Logger logger;

    private VisitorManager visitorManager;

    public UniqueVisitorCounter() {
        instance = this;
    }


    //getters
    public Logger getLogger() {
        return logger;
    }

    public VisitorManager getVisitorManager() {
        return visitorManager;
    }


    //listeners
    @Listener
    public void onInitialization(GameInitializationEvent event) {
        //TODO: load config
    }


    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        logger.info("Server starting, initializing UVC commands");

        CommandInitializer.initCommands();

        logger.info("Initializing visitor manager");

        visitorManager = new VisitorManager();
        visitorManager.sync();
    }


    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @Getter("getTargetEntity") Player player) {
        // The text message could be configurable, check the docs on how to do so!
        player.sendMessage(Text.of(TextColors.AQUA, TextStyles.BOLD, "Hi " + player.getName()));
    }


    //static
    private static UniqueVisitorCounter instance;

    public static UniqueVisitorCounter get() {
        return instance;
    }
}
