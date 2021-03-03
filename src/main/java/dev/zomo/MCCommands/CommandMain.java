package dev.zomo.MCCommands;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.command.PluginCommand;

public class CommandMain extends Command {

    /**
     * Constructor for the main command
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param command          PluginCommand value for the command itself
     * @param setCommandMethod Method this command will run
     */
    public CommandMain(PluginCommand command, InterfaceExecutor setCommandMethod) {
        super("", setCommandMethod);
        command.setExecutor(new PaperExecutor(this));
        command.setTabCompleter(new PaperTabCompleter(this));
    }

    /**
     * Gets a Command based on the current arguments
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param args     ArrayList of current arguments
     * @param isTyping is the player still typing
     */
    public GetCommandReturn getCommand(ArrayList<String> args, boolean isTyping) {
        int maxIndex = args.size();
        if (isTyping)
            maxIndex--;
        return getCommand(args, 0, maxIndex, this);
    }

    /**
     * Gets a Command based on the current arguments
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param args ArrayList of current arguments
     */
    public GetCommandReturn getCommand(ArrayList<String> args) {
        return getCommand(args, 0, args.size(), this);
    }

    /**
     * Gets a Command based on the current arguments
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param args     array of current arguments
     * @param isTyping is the player still typing
     */
    public GetCommandReturn getCommand(String[] args, boolean isTyping) {
        ArrayList<String> nArgs = new ArrayList<String>();
        Collections.addAll(nArgs, args);
        int maxIndex = nArgs.size();
        if (isTyping)
            maxIndex--;
        return getCommand(nArgs, 0, maxIndex, this);
    }

    /**
     * Gets a Command based on the current arguments
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param args array of current arguments
     */
    public GetCommandReturn getCommand(String[] args) {
        ArrayList<String> nArgs = new ArrayList<String>();
        Collections.addAll(nArgs, args);
        return getCommand(nArgs, 0, nArgs.size(), this);
    }

    /**
     * Gets a Command based on the current arguments
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param args     ArrayList of current arguments
     * @param index    current level of recursion
     * @param maxIndex max depth of recursion
     * @param command  current Command
     */
    private GetCommandReturn getCommand(ArrayList<String> args, int index, int maxIndex, Command command) {

        ArrayList<String> nArgs = new ArrayList<String>();

        if (index >= maxIndex) { //at end of args
            for (int i = index; i < args.size(); i++)
                nArgs.add(args.get(i));
            return new GetCommandReturn(command, nArgs);
        }

        for (int i = 0; i < command.subcommandsArr.size(); i++)
            if (command.subcommandsArr.get(i).name.equals(args.get(index)))
                return getCommand(args, index+1, maxIndex, command.subcommandsArr.get(i));

        for (int i = index; i < args.size(); i++)
            nArgs.add(args.get(i));
        
        return new GetCommandReturn(command, nArgs);

    }

    /*public csPaperExecutor executor() {
        return new csPaperExecutor(this);
    }

    public csPaperTabCompleter tabCompleter() {
        return new csPaperTabCompleter(this);
    }*/

}
