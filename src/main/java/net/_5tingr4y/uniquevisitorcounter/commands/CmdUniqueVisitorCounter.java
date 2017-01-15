package net._5tingr4y.uniquevisitorcounter.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;

class CmdUniqueVisitorCounter implements CommandExecutor {

    @Override
    @Nonnull
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(src instanceof CommandBlockSource) return CommandResult.success();

        //TODO: print command list
        src.sendMessage(Text.of("<Soon-to-be command list>"));

        return CommandResult.success();
    }
}
