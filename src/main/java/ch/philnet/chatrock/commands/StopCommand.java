package ch.philnet.chatrock.commands;

import ch.philnet.chatrock.utils.RoomLeaver;
import org.slf4j.Logger;
import org.sobotics.chatexchange.chat.Room;
import org.sobotics.chatexchange.chat.User;

public class StopCommand extends Command {
    public StopCommand(Room chatRoom, Logger commandLogger) {
        //Allowed Patters:
        // stop
        // bye
        commandPattern = "(?i)(stop|bye)";
        room = chatRoom;
        logger = commandLogger;
    }

    @Override
    public boolean testCommandPattern(String command) {
        return super.testCommandPattern(command);
    }

    @Override
    public void run(long messageId) {
        User messageAuthor = room.getMessage(messageId).getUser();
        //Only allowed to room owners and moderators
        if(messageAuthor.isModerator() || messageAuthor.isRoomOwner()) {
            room.send("Bye!").thenRun(new RoomLeaver(room));
            logger.info("Stopping the bot");
            room.leave();
            System.exit(0);
        }
    }
}
