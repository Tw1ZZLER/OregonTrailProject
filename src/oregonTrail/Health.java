package oregonTrail;

import java.util.ArrayList;
import java.util.List;
/**
 * Class to track the health of a single party member.
 */
public class Health {

    private int generalHealth;

    /**
     * Constructor to initialize the health of the party member.
     */
    public Health() {
        // Initialize general health to ideal value (0)
        generalHealth = 0;
    }

    /**
     * Method to get the general health of the party member.
     * @return The general health of the party member
     */
    public int getGeneralHealth() {
        return generalHealth;
    }

    /**
     * Method to update the general health of the party member based on various factors.
     * @param weatherCondition The weather condition (0: very hot, 1: hot, 2: cool or warm, 3: cold, 4: very cold)
     * @param clothingSetsPerPerson The number of clothing sets per person
     * @param foodRations The food rations level (0: filling, 1: meager, 2: bare bones, 3: out of food)
     * @param freezeStarveFactor The freeze/starve factor
     * @param paceLevel The pace level (0: resting, 1: steady, 2: strenuous, 3: grueling)
     * @param numSickOrInjured The number of sick or injured party members
     * @param randomEventFactor The random event factor
     */
    public void updateHealth(int weatherCondition, int clothingSetsPerPerson, int foodRations,
                             double freezeStarveFactor, int paceLevel, int numSickOrInjured, int randomEventFactor) {
        // Update general health based on weather condition
        switch (weatherCondition) {
            case 0: generalHealth += 2; break; // Very hot
            case 1: generalHealth += 1; break; // Hot
            case 2: break; // Cool or warm (no change)
            case 3: generalHealth -= 1; break; // Cold
            case 4: generalHealth -= 2; break; // Very cold
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
        } else if (generalHealth > 140) {
            generalHealth = 140;
        }
    }
}
