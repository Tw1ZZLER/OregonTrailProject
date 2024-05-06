package oregonTrail;

import java.util.Random;

import javax.swing.JOptionPane;

import oregonTrail.landmark.Landmark;

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
		String message;
		
        // Generate a random number between 1 and 20
        int randomNumber = rand.nextInt(20) + 1;
        
        if(randomNumber>=2) {
	    	if(!Landmark.ASH_HOLLOW.isVisited()) {
	    		message = "(You're met by Uncle Hugh) Look at all those reeds, it almost "
	    				+ "makes me want to set up camp here, its a shame its so wide open"
	    				+ " though. I wouldn't make it a week!";
	    	}
	    	else if(!Landmark.CHIMNEY_ROCK.isVisited()) {
	    		message = "Well I'm sure glad we managed to stock up on water and supplies but this"
	    				+ "walk across the desert will surely test our wits. I wish we’d still have some leftover mustard"
	    				+ "greens from last night they sure were good.-Mrs. Kirkland";
	    	}
	    	else if(!Landmark.THE_DALLES.isVisited()) {
	    		message = "These horses are the most stubborn and unruly animals I ever met, I can't"
	    				+ "get the darn harness on em and we’re already late!. -Frank";
	    	}
	    	else if(!Landmark.BLUE_MOUNTAINS.isVisited()) {
	    		message ="I can’t believe those damn herders drove away two of my best damn horses"
	    				+ "and then still made you pay 2 dollars for your troubles absolutely ridiculous -Mr. Kirkland";
	    	}
	    	else if(!Landmark.SODA_SPRINGS.isVisited()) {
	    		message ="They stole my revolver!! Who in the hell stole it!! I swear I’ll kill that"
	    				+ "rat!\"-John Smith, “Oh yeah with what gun (Laughter ensues from the crowd of soldiers)?!”-"
	    				+ "nameless Solider";
	    	}
	    	else {
	    		message = "No one wanted to talk with you (Probably cause you stink)";
	    	}
        }
    	else {
    		message = "No one wanted to talk with you (Probably cause you stink)";
    	}
        
        display(message);
	}
	
	/**
	 * Contains all conversations for talking with locals in a Fort
	 * @author Corbin Hibler
	 * @date 2024-05-06
	 */
	public void talkToFortLocals() {
		String message;

        // Generate a random number between 1 and 10
        int randomNumber = rand.nextInt(10) + 1;        
		
        if (!shotgunEventOccurred) {
            if (randomNumber == 1) {
                // Show special event dialogue box
                message = "As you were making breakfast, Dick Delay accidentally "
                		+ "steps on his shotgun shooting Mr. Ridgel, the shop owner."
                		+ " You decide it's probably best to leave town.";
                // Continue the game
                oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
                shotgunEventOccurred = true; // Set to true to indicate the special event has occurred
            } else {
                // Normal dialogue
                if (!Landmark.FORT_LARAMIE.isVisited()) {
                    message = "(You're met by a short young girl with blonde hair, "
                    		+ "you think her name might be Alice from locals chatting) "
                    		+ "Did you see all those indians last night? I swear there "
                    		+ "must have been hundreds of em";
                } else if (!Landmark.OREGON_CITY.isVisited()) {
                    message = "(You're met by God?) So you finally made it, congratulations,"
                    		+ " we lost nearly all of our family and it looks like you almost "
                    		+ "shared the same fate. Now are you gonna play again?";
                } else {
                    message = "No one wanted to talk with you (Probably cause you stink)";
                }
            }
        } else {
            // All further conversations after the special event
            message = "You've already had your conversation. No one wants to talk with you again.";
        }
        
        display(message);
	}
}
