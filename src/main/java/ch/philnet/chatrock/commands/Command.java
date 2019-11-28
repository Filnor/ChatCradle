package ch.philnet.chatrock.commands;

import org.slf4j.Logger;
import org.sobotics.chatexchange.chat.Room;

import java.util.regex.Pattern;

/**
 * Parent Class for Commands
 */
public abstract class Command {
    String commandPattern;
    Room room;
    Logger logger;

    /**
     * Test if the supplied command string has a valid pattern for the command instance
     * @param command Command string to test
     */
    public boolean testCommandPattern(String command) {
        return Pattern.matches(commandPattern, command);
    }

    /**
     * Run a specific command
     * @param messageId The message id
     */
    public abstract void run(long messageId);
}
