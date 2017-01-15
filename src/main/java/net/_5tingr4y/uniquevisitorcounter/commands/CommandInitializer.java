package net._5tingr4y.uniquevisitorcounter.commands;

import net._5tingr4y.uniquevisitorcounter.UniqueVisitorCounter;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandInitializer {

    public static void initCommands() {
        //TODO: init remaining commands
        CommandSpec uvc = CommandSpec.builder()
                .description(Text.of("Parent command for all UVC commands; prints a list of all child commands"))
                .permission("uvc.commands.base")
                .executor(new CmdUniqueVisitorCounter())
                .build();

        Sponge.getCommandManager().register(UniqueVisitorCounter.get(), uvc, "uniquevisitorcounter", "uvc");
    }
}
