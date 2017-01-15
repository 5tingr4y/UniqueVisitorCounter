package net._5tingr4y.uniquevisitorcounter.commands;

import net._5tingr4y.uniquevisitorcounter.UniqueVisitorCounter;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nonnull;
import java.util.Optional;

class CmdList implements CommandExecutor {

    @Override
    @Nonnull
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        Optional<PaginationService> paginationServiceOptional = Sponge.getServiceManager().provide(PaginationService.class);

        if(paginationServiceOptional.isPresent()) {
            //use paginationService
            PaginationList.Builder builder = paginationServiceOptional.get().builder()
                    .title(Text.of("All seen players"))
                    .padding(Text.of("="));

            for(User user: UniqueVisitorCounter.get().getVisitorManager().getUsers()) {
                builder.contents(Text.of(user.isOnline() ? TextColors.GREEN : TextColors.GRAY, user.getName()));
            }

            builder.sendTo(src);

            return CommandResult.success();
        } else {
            //no paginationservice available
            UniqueVisitorCounter.get().getLogger().warn("PaginationService unavailable; /list not usable...");
            src.sendMessage(Text.of(TextColors.RED, "PaginationService unavailable."));

            return CommandResult.empty();
        }
    }
}
