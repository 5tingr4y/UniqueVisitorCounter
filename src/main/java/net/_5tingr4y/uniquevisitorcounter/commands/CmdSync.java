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
import org.spongepowered.api.text.format.TextStyles;

import javax.annotation.Nonnull;

class CmdSync implements CommandExecutor {

    @Override
    @Nonnull
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        int res = UniqueVisitorCounter.get().getVisitorManager().sync(args.hasAny("f"));

        switch(res) {
            case 0:
                src.sendMessage(Text.of(TextColors.GREEN, "Internal visitor counter was already synchronized."));
                src.sendMessage(Text.of(TextColors.GREEN, "You can enforce synchronization by calling ",
                        TextStyles.ITALIC, "/uvc sync -f"));
                return CommandResult.success();
            case 1:
                src.sendMessage(Text.of(TextColors.GREEN, "Internal visitor counter successfully synchronized."));
                return CommandResult.success();
            default:
                src.sendMessage(Text.of(TextColors.RED, "Unable to synchronize visitor counter; please try again in a few minutes."));
                src.sendMessage(Text.of(TextColors.RED, "If this problem persists, try restarting the server."));
                return CommandResult.empty();
        }
    }
}
