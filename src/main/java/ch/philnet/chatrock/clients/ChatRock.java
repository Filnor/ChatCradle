package ch.philnet.chatrock.clients;

import org.sobotics.chatexchange.chat.ChatHost;
import org.sobotics.chatexchange.chat.Room;
import org.sobotics.chatexchange.chat.StackExchangeClient;
import org.sobotics.chatexchange.chat.User;

import ch.philnet.chatrock.services.BotService;
import ch.philnet.chatrock.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;


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
            
            while(true) {
                Utils.LOGGER.info("Checking for chat messages in the last 24 hours.");
                List<User> users = room.getCurrentUsers();
                ArrayList<Boolean> lastMessageOutrunned = new ArrayList<Boolean>();

                //Checking latest message for each user
                for(User user : users) {
                    long secondsSinceLastMessage = Duration.between(user.getLastMessageDate(), Instant.now()).get(ChronoUnit.SECONDS);

                    //If last message was more than 24h ago, outrunned will be set to true
                    lastMessageOutrunned.add(secondsSinceLastMessage >= 86400L);
                }

                //Checking if all messages are outrunned
                if(Utils.areAllTrue(lastMessageOutrunned)) {
                    Utils.LOGGER.info("Sending quote.");
                    room.send(String.format("[ [ChatRock](https://git.io/Je1fg) ] %s.", getRandomQuote()));
                } else {
                    Utils.LOGGER.info("No bump needed.");
                }

                Thread.sleep(86400L);
            }

        } catch (InterruptedException e) {
            Utils.LOGGER.error("Thread sleeping was interrupted. See details in stack trace:");
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    /**
     * Get random quote
     * @return random quote string
     */
    public static String getRandomQuote() {
        //Generate random number
        Random r = new Random();
        int index = r.nextInt(49);
        
        return getQuotes()[index];
    }

    /**
     * Returns Array with all possible quotes
     * @return Array with quotes
     */
    private static final String[] getQuotes() {
        String[] quotes = {
            "The elevator to success is out of order. You’ll have to use the stairs, one step at a time. - Joe Girard",
            "People often say that motivation doesn’t last. Well, neither does bathing – that’s why we recommend it daily. - Zig Ziglar",
            "I always wanted to be somebody, but now I realise I should have been more specific. - Lily Tomlin",
            "I am so clever that sometimes I don’t understand a single word of what I am saying. - Oscar Wilde",
            "People say nothing is impossible, but I do nothing every day. - Winnie the Pooh",
            "Life is like a sewer… what you get out of it depends on what you put into it. - Tom Lehrer",
            "You can’t have everything. Where would you put it? - Steven Wright",
            "Work until your bank account looks like a phone number. - Unknown ",
            "Change is not a four letter word… but often your reaction to it is! - Jeffrey Gitomer",
            "If you think you are too small to make a difference, try sleeping with a mosquito. - Dalai Lama",
            "Bad decisions make good stories. - Ellis Vidler",
            "I’ll probably never fully become what I wanted to be when I grew up, but that’s probably because I wanted to be a ninja princess. - Cassandra Duffy",
            "When life gives you lemons, squirt someone in the eye. - Cathy Guisewite",
            "A clear conscience is a sure sign of a bad memory. - Mark Twain",
            "Well-behaved women seldom make history. - Laurel Thatcher Ulrich",
            "I didn’t fail the test. I just found 100 ways to do it wrong. - Benjamin Franklin",
            "I used to think I was indecisive, but now I’m not so sure. - Unknown",
            "Don’t worry about the world coming to an end today. It’s already tomorrow in Australia. - Charles Schulz",
            "Think like a proton. Always positive. - Unknown",
            "Be happy – it drives people crazy. - Unknown",
            "Optimist: someone who figures that taking a step backward after taking a step forward is not a disaster, it’s more like a cha-cha. - Robert Brault",
            "The question isn’t who is going to let me, it’s who is going to stop me. - Ayn Rand",
            "You’re only given a little spark of madness. You mustn’t lose it. - Robin Williams",
            "I am an early bird and a night owl… so I am wise and I have worms - Michael Scott",
            "If you let your head get too big, it’ll break your neck. - Elvis Presley",
            "The road to success is dotted with many tempting parking spaces. - Will Rogers",
            "Leadership is the art of getting someone else to do something you want done because he wants to do it. - Dwight D. Eisenhower",
            "Live each day like it’s your second to the last. That way you can fall asleep at night. - Jason Love",
            "Even a stopped clock is right twice every day. After some years, it can boast of a long series of successes. - Marie Von Ebner-Eschenbach",
            "Honest criticism is hard to take, particularly from a relative, a friend, an acquaintance, or a stranger. - Franklin P. Jones",
            "I believe that if life gives you lemons, you should make lemonade… And try to find somebody whose life has given them vodka, and have a party. - Ron White",
            "Opportunity is missed by most people because it is dressed in overalls and looks like work. - Thomas Eddison",
            "A diamond is merely a lump of coal that did well under pressure. - Unknown",
            "Nothing is impossible, the word itself says “I’m possible! - Audrey Hepburn",
            "Friendship is like peeing on yourself: everyone can see it, but only you get the warm feeling that it brings. - Robert Bloch",
            "Women who seek to be equal with men lack ambition. - Marilyn Monroe",
            "By working faithfully eight hours a day you may eventually get to be boss and work twelve hours a day. - Robert Frost",
            "The trouble with having an open mind, of course, is that people will insist on coming along and trying to put things in it. - Terry Pratchett",
            "Age is of no importance unless you’re a cheese. - Billie Burke",
            "When tempted to fight fire with fire, remember that the Fire Department usually uses water. - Unknown",
            "Trying is the first step toward failure. - Homer Simpson",
            "Happiness is just sadness that hasn’t happened yet. - Unknown",
            "The best things in life are actually really expensive. - Unknown",
            "Every tattoo is temporary, because we’re all slowly dying. - Unknown",
            "A few harmless flakes working together can unleash an avalanche of destruction. - Justin Sewell",
            "Dreams are like rainbows. Only idiots chase them. - Unknown",
            "It could be that your purpose in life is to serve as a warning to others. -  Ashleigh Brilliant",
            "If the world didn’t suck we’d all fly into space. - Unknown",
            "The light at the end of the tunnel has been turned off due to budget cuts. - Unknown",
            "Always remember that you are unique – just like everybody else. - Unknown"
        };
        return quotes;
    }
}
