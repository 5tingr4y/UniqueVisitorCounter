/*
 * Copyright (c) 2017 Raymond Kampmann <https://5tingr4y.net/>
 *
 * This file is part of UniqueVisitorCounter, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
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

class CmdCount implements CommandExecutor {

    @Override
    @Nonnull
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        int count = UniqueVisitorCounter.get().getVisitorManager().getUsers().size();

        src.sendMessage(Text.of(TextColors.GREEN, "We had " + count +
                " individual visitor" + (count == 1 ? "" : "s") + " so far."));

        return CommandResult.success();
    }
}
