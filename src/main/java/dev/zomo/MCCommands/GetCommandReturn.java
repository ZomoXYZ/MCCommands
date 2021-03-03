package dev.zomo.MCCommands;

import java.util.ArrayList;

public class GetCommandReturn {

    Command command = null;
    ArrayList<String> args = null;
    
    /**
     * Object to store information about the currently typed Command
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param retCommand Command itself
     * @param retArgs    arguments following command
     */
    GetCommandReturn(Command retCommand, ArrayList<String> retArgs) {
        command = retCommand;
        args = retArgs;
    }
    
    /**
     * Object to store information about the currently typed Command
     *
     * @author Ashley Zomo
     * @version 1.0.0
     * @since 2020-12-03
     * @param retCommand Command itself
     */
    GetCommandReturn(Command retCommand) {
        command = retCommand;
        args = new ArrayList<String>();
    }

}
