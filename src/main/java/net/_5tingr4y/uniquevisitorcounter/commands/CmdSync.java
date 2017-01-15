package net._5tingr4y.uniquevisitorcounter.commands;

import net._5tingr4y.uniquevisitorcounter.UniqueVisitorCounter;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nonnull;

class CmdSync implements CommandExecutor {

    @Override
    @Nonnull
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(UniqueVisitorCounter.get().getVisitorManager().sync()) {
            src.sendMessage(Text.of(TextColors.GREEN, "Internal visitor counter successfully synchronized."));
            return CommandResult.success();
        }
        src.sendMessage(Text.of(TextColors.RED, "Unable to synchronize visitor counter; please try again in a few minutes."));
        src.sendMessage(Text.of(TextColors.RED, "If this problem persists, try restarting the server."));
        return CommandResult.empty();
    }
}
