package dev.zomo.MCCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class PaperTabCompleter implements TabCompleter {

    CommandMain commandSystem = null;

    PaperTabCompleter(CommandMain setCS) {
        commandSystem = setCS;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        GetCommandReturn cmd = commandSystem.getCommand(args, true);

        if (cmd.args.size() > 1) //tab completion only works for the current subcommand, if the args are longer than 1 then this is a sub sub command and isn't supported by this system for tab completion
            return new ArrayList<String>();

        return cmd.command.tabCompletion(cmd.args);
    }

}
