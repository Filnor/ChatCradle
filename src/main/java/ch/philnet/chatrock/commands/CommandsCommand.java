package ch.philnet.chatrock.commands;

import org.slf4j.Logger;
import org.sobotics.chatexchange.chat.Room;

public class CommandsCommand extends Command {
    public CommandsCommand(Room chatRoom, Logger commandLogger) {
        //Allowed Patters:
        // commands
        // help
        commandPattern = "(?i)(commands|help)";
        room = chatRoom;
        logger = commandLogger;
    }

    @Override
    public boolean testCommandPattern(String command) {
        return super.testCommandPattern(command);
    }

    @Override
    public void run(long messageId) {
        logger.info("Printing command list");
        room.send(
            "    ### ChatRock commands ###\n" +
            "    alive, a         - Responds with a message if the bot is running in the current room" +
            "    stop, bye        - Stops the bot. Requires privileges" +
            "    commands, help   - This command. Lists all available commands"
        );
    }
}
