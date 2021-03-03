package dev.zomo.MCCommands;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public class Command {

    String name = "";
    InterfaceExecutor commandMethod = null;
    ArrayList<Command> subcommandsArr = new ArrayList<Command>();
    ArrayList<String> autocompleteArr = new ArrayList<String>();
    InterfaceTab autocompleteDynamic = null;

    /**
     * Constructor for the Command
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param setName          name of command
     * @param setCommandMethod method command will run
     */
    public Command(String setName, InterfaceExecutor setCommandMethod) {
        name = setName;
        commandMethod = setCommandMethod;
    }

    /**
     * Add a subcommand
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param subcommandObj new Command instance
     */
    public Command subcommand(Command subcommandObj) {
        subcommandsArr.add(subcommandObj);
        return this;
    }

    /**
     * Add a subcommand
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param setName          name of subcommand
     * @param setCommandMethod method subcommand will run
     */
    public Command subcommand(String setName, InterfaceExecutor setCommandMethod) {
        Command subcommandObj = new Command(setName, setCommandMethod);
        subcommandsArr.add(subcommandObj);
        return this;
    }

    /**
     * Add a custom autocomplete entry
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param autocompleteStr value to be able to be autocompleted
     */
    public Command autocomplete(String autocompleteStr) {
        autocompleteArr.add(autocompleteStr);
        return this;
    }

    /**
     * Add custom autocomplete entries
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param autocompleteStr array of values to be able to be autocompleted
     */
    public Command autocomplete(String[] autocompleteStr) {
        for (int i = 0; i < autocompleteStr.length; i++)
            autocompleteArr.add(autocompleteStr[i]);
        return this;
    }

    /**
     * Add custom autocomplete entries
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param autocompleteStr ArrayList of values to be able to be autocompleted
     */
    public Command autocomplete(ArrayList<String> autocompleteStr) {
        for (int i = 0; i < autocompleteStr.size(); i++)
            autocompleteArr.add(autocompleteStr.get(i));
        return this;
    }

    /**
     * Add custom autocomplete entries
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param autocompleteMethod method to dynamically add values to be
     *                           autocompleted
     */
    public Command autocomplete(InterfaceTab autocompleteMethod) {
        autocompleteDynamic = autocompleteMethod;
        return this;
    }

    /**
     * Run this command
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param sender CommandSender of who requested the command
     * @param args   ArrayList of arguments
     */
    public boolean run(CommandSender sender, ArrayList<String> args) {
        return commandMethod.run(sender, args);
    }

    /**
     * ArrayList of values that can be autocompleted
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param current current String typed in
     */
    public ArrayList<String> tabCompletion(String current) {
        
        ArrayList<String> allPossible = new ArrayList<String>();

        for (int i = 0; i < subcommandsArr.size(); i++)
            allPossible.add(subcommandsArr.get(i).name);

        for (int i = 0; i < autocompleteArr.size(); i++)
            allPossible.add(autocompleteArr.get(i));

        if (autocompleteDynamic != null) {
            ArrayList<String> dynamicResults = autocompleteDynamic.run();

            for (int i = 0; i < dynamicResults.size(); i++)
                allPossible.add(dynamicResults.get(i));
        }

        ArrayList<String> narrowPossible = new ArrayList<String>();

        for (int i = 0; i < allPossible.size(); i++)
            if (allPossible.get(i).startsWith(current))
                narrowPossible.add(allPossible.get(i));

        return narrowPossible;
    }

    /**
     * ArrayList of values that can be autocompleted
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param args ArrayList of current arguments
     */
    public ArrayList<String> tabCompletion(ArrayList<String> args) {
        String current = "";
        if (args.size() > 0)
            current = args.get(0);
        return tabCompletion(current);
    }

}
