/**
 * File: Health.java
 * Author: Lukas Dunbar
 * Version: 1.0
 * Date: April 29, 2024
 * Description: Class to track the health of a single party member in a game simulation.
 *
 * @author Lukas Dunbar
 * @version 1.0
 */
package oregonTrail;
import javax.swing.*;

/**
 * The {@code Health} class tracks the health of a single party member in a game simulation.
 * It includes methods to update the general health based on various factors and terminates
 * the program if the health reaches a critical level.
 *
 * @author Lukas Dunbar
 * @version 1.0
 */
public class Health {

    private int generalHealth; // The general health of the party member

    /**
     * Constructs a new {@code Health} object and initializes the general health to 0.
     */
    public Health() {
        generalHealth = 0;
    }

    /**
     * Gets the general health of the party member.
     *
     * @return The general health of the party member.
     */
    public int getGeneralHealth() {
        return generalHealth;
    }

    /**
     * Updates the general health of the party member based on various factors.
     *
     * @param weatherCondition      The weather condition (0: very hot, 1: hot, 2: cool or warm, 3: cold, 4: very cold).
     * @param clothingSetsPerPerson The number of clothing sets per person.
     * @param foodRations           The food rations level (0: filling, 1: meager, 2: bare bones, 3: out of food).
     * @param freezeStarveFactor    The freeze/starve factor.
     * @param paceLevel             The pace level (0: resting, 1: steady, 2: strenuous, 3: grueling).
     * @param numSickOrInjured      The number of sick or injured party members.
     * @param randomEventFactor     The random event factor.
     */
    public void updateHealth(int weatherCondition, int clothingSetsPerPerson, int foodRations,
                             double freezeStarveFactor, int paceLevel, int numSickOrInjured, int randomEventFactor) {
        // Update general health based on weather condition
        switch (weatherCondition) {
            case 0:
                generalHealth += 2; // Very hot
                break;
            case 1:
                generalHealth += 1; // Hot
                break;
            case 3:
                generalHealth -= 1; // Cold
                break;
            case 4:
                generalHealth -= 2; // Very cold
                break;
        }

        // Update general health based on clothing
        if (clothingSetsPerPerson == 0) {
            generalHealth += 2;
        } else if (clothingSetsPerPerson < 2) {
            generalHealth += 1;
        }

        // Update general health based on food rations
        generalHealth += foodRations * 2;

        // Update general health based on freeze/starve factor
        generalHealth += (int) freezeStarveFactor;

        // Update general health based on pace level
        generalHealth += paceLevel * 2;

        // Update general health based on number of sick or injured party members
        generalHealth += numSickOrInjured;

        // Update general health based on random event factor
        generalHealth += randomEventFactor;

        // Ensure general health stays within bounds (0 to 140)
        if (generalHealth < 0) {
            generalHealth = 0;
        } else if (generalHealth >= 140) {
            // If health reaches 140 or more, end the game and terminate the program
            JOptionPane.showMessageDialog(null, "You have died! Game over.");
            System.exit(0);
        }
    }

    /**
     * Gets the general health of the party member.
     *
     * @return The general health of the party member.
     */
    public int getGeneralHealth1() {
        return generalHealth;
    }


    /**
     * Returns the general health as a string.
     * 
     * @return The general health as a string.
     */
    public String getGeneralHealthAsString() {
        return Integer.toString(generalHealth);
    }
}
