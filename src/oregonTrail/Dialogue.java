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
			message = "(You're met by Uncle Hugh) Look at all those reeds, it almost "
					+ "makes me want to set up camp here, it's a shame its so wide open"
					+ " though. I wouldn't make it a week!";
			break;
        case 1:
			message = "Well I'm sure glad we managed to stock up on water and supplies but this"
					+ " walk across the desert will surely test our wits. I wish we’d still have some leftover mustard"
					+ " greens from last night they sure were good.-Mrs. Kirkland";
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
			message = "";
			break;
		case 6:
			message = "";
			break;
		case 7:
			message = "";
			break;
		case 8:
			message = "";
			break;
		case 9:
			message = "";
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
			message = "As you were making breakfast, Dick Delay accidentally "
					+ "steps on his shotgun shooting Mr. Ridgel, the shop owner."
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
						+ "must have been hundreds of em";
				break;
			case 2:
				message = "";
				break;
			case 3:
				message = "";
				break;
			case 4:
				message = "";
				break;
			case 5:
				message = "";
				break;
			case 6:
				message = "";
				break;
			case 7:
				message = "";
				break;
			case 8:
				message = "";
				break;
			case 9:
				message = "";
				break;
			default:
				message = "No one wanted to talk with you (Probably cause you stink)";
				break;
			}
		}
        display(message);
	}
}
