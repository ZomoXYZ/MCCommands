package dev.zomo.MCCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PaperExecutor implements CommandExecutor {

    CommandMain commandSystem = null;

    PaperExecutor(CommandMain setCS) {
        commandSystem = setCS;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        GetCommandReturn cmd = commandSystem.getCommand(args);

        return cmd.command.run(sender, cmd.args);
    }

}
