package dev.zomo.MCCommands;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public interface InterfaceExecutor {
    public boolean run(CommandSender sender, ArrayList<String> args);
}
