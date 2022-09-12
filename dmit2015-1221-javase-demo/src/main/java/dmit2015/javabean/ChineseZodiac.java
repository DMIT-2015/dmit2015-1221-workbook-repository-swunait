package dmit2015.javabean;

/**
 * This class contains a class level method that returns the animal name for a given birth year
 * according to the Chinese Zodiac.
 *
 * @author Sam Wu
 * @version 2022.09.12
 */
public class ChineseZodiac {

    /**
     * Returns the animal name for the given birthYear
     * @param birthYear the birth to use
     * @return the animal name according to the Chinese Zodiac
     */
    public static String animal(int birthYear) {
        String animalName = "Unknown";
        String[] names = {
                "Rat",
                "Ox",
                "Tiger",
                "Rabbit",
                "Dragon",
                "Snake",
                "Horse",
                "Goat",
                "Monkey",
                "Rooster",
                "Dog",
                "Pig"
        };
        int offsetIndex = (birthYear - 1900) % 12;
        animalName = names[offsetIndex];

        return animalName;
    }
}
