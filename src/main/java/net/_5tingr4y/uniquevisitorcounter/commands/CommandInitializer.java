/*
 * Copyright (c) 2017 Raymond Kampmann <https://5tingr4y.net/>
 *
 * This file is part of UniqueVisitorCounter, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package net._5tingr4y.uniquevisitorcounter.commands;

import net._5tingr4y.uniquevisitorcounter.UniqueVisitorCounter;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandInitializer {

    public static void initCommands() {
        //child commands
        CommandSpec sync = CommandSpec.builder()
                .description(Text.of("Synchronizes the internal visitor list with Minecraft's player list"))
                .permission("uvc.commands.sync")
                .arguments(GenericArguments.flags().flag("f").buildWith(GenericArguments.none()))
                .executor(new CmdSync())
                .build();

        CommandSpec list = CommandSpec.builder()
                .description(Text.of("Lists all players seen on the server so far"))
                .permission("uvc.commands.list")
                .executor(new CmdList())
                .build();

        CommandSpec count = CommandSpec.builder()
                .description(Text.of("Returns the number of individual visitors to the server so far"))
                .permission("uvc.commands.count")
                .executor(new CmdCount())
                .build();

        //main command
        CommandSpec uvc = CommandSpec.builder()
                .description(Text.of("Parent command for all UVC commands; prints a list of all child commands"))
                .permission("uvc.commands.base")
                .executor(new CmdUniqueVisitorCounter())
                .child(sync, "sync")
                .child(list, "list", "ls")
                .child(count, "count", "number", "seen")
                .build();

        Sponge.getCommandManager().register(UniqueVisitorCounter.get(), uvc, "uniquevisitorcounter", "uvc");
    }
}
