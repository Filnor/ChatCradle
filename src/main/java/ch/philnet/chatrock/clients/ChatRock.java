package ch.philnet.chatrock.clients;

import org.sobotics.chatexchange.chat.ChatHost;
import org.sobotics.chatexchange.chat.Room;
import org.sobotics.chatexchange.chat.StackExchangeClient;
import ch.philnet.chatrock.services.BotService;
import ch.philnet.chatrock.services.RockService;
import ch.philnet.chatrock.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ChatRock {
    public static void main(final String[] args) throws IOException {
        final Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("." + File.separator + "properties" + File.separator + "auth.properties"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final StackExchangeClient client = new StackExchangeClient(prop.getProperty("email"),
                prop.getProperty("password"));

        try {
            final Room room = client.joinRoom(ChatHost.STACK_OVERFLOW, 163468);
            new BotService().run(room, prop.getProperty("location"));

            /*TimerTask task = new TimerTask() {
                public void run() {
                    new RockService().run(room);
                }
            };
            new Timer("RockService_Scheduled").scheduleAtFixedRate(task, 0, 5000);*/
            
            while (true) {
                Utils.LOGGER.info("Sending quote.");
                room.send(String.format("[ [ChatRock](https://git.io/Je1fg) ] %s.", new RockService().getRandomQuote()));
                Thread.sleep(86400L);
            }

        } catch (InterruptedException e) {
            Utils.LOGGER.error("Thread sleeping was interrupted. See details in stack trace:");
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
