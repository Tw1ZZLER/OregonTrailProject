package oregonTrail;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Class that handles talking to locals and other important dialogue
 * @author Corbin Hibler
 * @date 2024-05-06
 * @filename Dialogue.java
 */
public class Dialogue {
	
	private OregonTrail oregonTrail;
    private Random rand = new Random();
    private boolean shotgunEventOccurred;
	
	/**
	 * Constructor to create a Dialogue object
	 * @param oregonTrail Game object to access the rest of the game
	 * @date 2024-05-06
	 */
	public Dialogue(OregonTrail oregonTrail) {
		this.oregonTrail = oregonTrail;
	}
	
	/**
	 * Displays the dialogue / conversation message to the user
	 * @author Corbin Hibler
	 * @date 2024-05-06
	 */
	public void display(String message) {
		// Message dialogs like to show long strings in one huge line across the screen.
		// Let's instead parse this string and add newline characters using a StringBuilder,
		// to split it up into a sizable paragraph.
		StringBuilder splitMessage = new StringBuilder();
		int charCount = 0;
		
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            splitMessage.append(c);
            charCount++;
            
            // Only add new lines for spaces, which all avoid cutting into words
            if (charCount >= 50 && c != ' ') {
                int lastSpaceIndex = splitMessage.lastIndexOf(" ");
                if (lastSpaceIndex != -1) {
                	splitMessage.replace(lastSpaceIndex, lastSpaceIndex + 1, "\n");
                    charCount = splitMessage.length() - lastSpaceIndex;
                }
            // If the character is a newline, reset charCount
            } else if (c == '\n') {
                charCount = 0;
            }
        }
		
		String title = "Dialogue";
		JOptionPane.showMessageDialog(oregonTrail.frame, splitMessage.toString(), title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Contains all conversations for talking with locals in a Landmark
	 * @author Corbin Hibler
	 * @date 2024-05-06
	 */
	public void talkToLandmarkLocals() {
		String message = "-1";
		
        // Generate a random number between 0 and 9
        int option = rand.nextInt(10);
        
        switch(option) {
        case 0:
			message = "(You're met by Uncle Hugh, of Ruth Shackleford's party) Look at all "
					+ "those reeds, it almost makes me want to set up camp here, it's a "
					+ "shame its so wide open though. I wouldn't make it a week!";
			break;
        case 1:
			message = "Well I'm sure glad we managed to stock up on water and supplies but this"
					+ " walk across the desert will surely test our wits. I wish we’d still have some leftover mustard"
					+ " greens from last night, they sure were good. - Mrs. Kirkland";
			break;
        case 2:
			message = "These horses are the most stubborn and unruly animals I ever met, I can't"
					+ " get the darn harness on 'em, and we’re already late!. -Frank";
			break;
        case 3:
			message ="I can’t believe those damn herders drove away two of my best damn horses,"
					+ " and then still made you pay 2 dollars for your troubles, absolutely ridiculous! -Mrs. Kirkland";
			break;
        case 4:
			message ="They stole my revolver!! Who in the hell stole it!! I swear I’ll kill that"
					+ " rat!\"-John Smith, “Oh yeah with what gun (Laughter ensues from the crowd of soldiers)?!”-"
					+ " nameless Soldier";
			break;
		case 5:
			message = "A traveler named Ruth Shackleford approaches you:"
					+ "“Good afternoon! I know it can get hectic out there, but it’s important to always pay attention to what is going on. "
					+"The weather especially has an effect on how your travels are going to turn out. "
					+"If it’s too hot, make sure to take frequent rest stops, and if it’s too cold, make sure you’ve got enough clothes to wear more layers. "
					+"Otherwise, folks might get sick.”";
			break;
		case 6:
			message = "You come across a woman, her name is Ruth. Ruth says:"
					+ " “Make sure to take time to rest if anyone you travel with falls ill. "
					+"One of our friends, Mr. Kirkland, became very sick, but since we stopped to recover, he is still with us. "
					+"Illnesses can become a problem for your party very quickly unless you take care of it. "
					+"We had a real issue with smallpox earlier in our journey because we wouldn’t stop, don’t make that same mistake!”\r\n";
			break;
		case 7:
			message = "A woman named Ruth Shackleford greets you,\r\n"
					+ "“While you’re out on the Trail, if you spot some wild cattle or other similarly big animal, hunting can be a great, cheap way to bring in a lot of food."
					+" We stopped and brought down a buffalo the other day, and now we almost have enough beef to last us through the next leg of the trip!”\r\n";
			break;
		case 8:
			message = "A traveler, Ruth Shackleford, gives you a word of advice:\r\n"
					+ "“If you can manage it, I would suggest trying to get on a ferry or pay for someone more experienced to help you make your way across a river. "
					+"Our group always tries to avoid crossing without any assistance, just to keep safe.”";
			break;
		case 9:
			message = "As you help your party make preparations for crossing a river, a Native American man approaches you."
					+ "“Be wary when crossing the rivers, they can sweep you away if you are not careful. "
					+"A good rule is that if the water is higher than your waist, you should wait for it to go down. "
					+"If you must cross, right away, though, you could try floating your wagon…”";
			break;
        default:
			message = "No one wanted to talk with you (Probably cause you stink)";
			break;
        }
        
        display(message);
	}
	
	/**
	 * Contains all conversations for talking with locals in a Fort
	 * @author Corbin Hibler
	 * @date 2024-05-06
	 */
	public void talkToFortLocals() {
		String message = "";

        // Generate a random number between 0 and 9
        int option = rand.nextInt(10);        
		
        if (!shotgunEventOccurred && option == 0) {
			// Show special event dialogue box
			message = "As you were making breakfast, Dick Delay, a member of Ruth Shackleford's party,"
					+ "accidentally steps on his shotgun shooting Mr. Ridgel, the shop owner."
					+ " You decide it's probably best to leave town.";
			// Continue the game
			oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
			shotgunEventOccurred = true; // Set to true to indicate the special event has occurred
		} else {
			// Normal dialogue
			switch (option) {
			case 0:
				message = "(You're met by a short young girl with blonde hair, "
						+ "you think her name might be Alice from locals chatting) "
						+ "Did you see all those indians last night? I swear there "
						+ "must have been hundreds of 'em!";
				break;
			case 2:
				message = "My family and I are on the move towards the great Southern"
						+ " California. We have 6 families and over 10 wagons in our "
						+ "party, coming from Union City, Missouri. We departed "
						+ "about 12 days ago. - Ruth Shackleford";
				break;
			case 3:
				message = "Many families here attempt to caulk their wagons "
						+ "across this river, only to end up losing a wagon "
						+ "and many items. We will wait until the tide lowers "
						+ "before attempting a cross. - Ruth Shackleford.";
				break;
			case 4:
				message = "My husband Frank, a carpenter, has built one of "
						+ "our dead party members a lovely coffin. We will bury "
						+ "her tomorrow morning. I believe her name was Nellie Kerfoot."
						+ " It is hard for me to remember or spell her surname. - Ruth Shackleford";
				break;
			case 5:
				message = "This trail is nothing easy. My daughter, Mary, just passed "
						+ "the other day. We buried her on the banks of the Bear River. "
						+ "She looked very natural and at peace. I hope she rests easy. - Ruth Shackleford";
				break;
			case 6:
				message = "A wizened old man from a nearby wagon party approaches you."
						+ "“Always make sure you’ve got enough clothes to go around! "
						+"It can be expensive, but when the weather turns foul, you’ll be glad to have ‘em! Stay warm, stay healthy!”\r\n";
				break;
			case 7:
				message = "A nearby traveler plucks softly on a guitar as he addresses you."
						+ "“Howdy there! I may seem jolly, but I’m quite stuck here. "
						+ "Just goes to show, wagon parts can break on you at any moment. "
						+ "Keep spare parts on hand at all times, lest you suffer a similar fate!”";
				break;
			case 8:
				message = "A woman with a steely gaze spots you walking past and offers a piece of advice."
						+ "“‘Morning. My name is Ruth Shackleford, and those folks over there are my traveling party. "
						+ "Looking at your group, it seems there’s a lot in common. Make sure that you take time to "
						+ "rest and recover every once in a while. Some folks might protest, acting all tough, "
						+ "but sometimes you’ve got to put your foot down to make sure everyone’s making it to your destination.”"
						+ "";
				break;
			case 9:
				message = "You overhear Mr. Shackleford and Mr. Kirkland (of Ruth Shackleford’s Party) having a conversation."
						+ "“I know we’ve come a long way, but Mr. Kirkland, our success on this journey is going to "
						+ "be a close thing. Our supplies are running low, and we have spent a great deal of money on food and passage at rivers.”";
				break;
			default:
				message = "No one wanted to talk with you (Probably cause you stink)";
				break;
			}
		}
        display(message);
	}
}
