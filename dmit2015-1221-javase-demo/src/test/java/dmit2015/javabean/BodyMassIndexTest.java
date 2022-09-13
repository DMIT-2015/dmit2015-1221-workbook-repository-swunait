package dmit2015.javabean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    @ParameterizedTest
    @CsvSource({
            "Underweight Person, 90, 60, 17.6, underweight",
            "Another Underweight, 120, 75, 15.0, underweight"
    })
    void bmiCategory_underweight_underweightCategory(String name, int weight, int height, double expectedBmi, String expectedCategory) {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName(name);
        bmi1.setWeight(weight);
        bmi1.setHeight(height);
        assertEquals(expectedBmi, bmi1.bmi(), 0);
        assertEquals(expectedCategory, bmi1.bmiCategory());
    }

    @ParameterizedTest
    @CsvSource({
            "Normal weight Person, 111, 65, 18.5, normal",
            "Another Normal weight person, 149, 65, 24.8, normal",
            "Third Normal weight person, 146, 70, 20.9, normal",
    })
    void bmiCategory_normalweight_normalweightCategory(String name, int weight, int height, double expectedBmi, String expectedCategory) {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName(name);
        bmi1.setWeight(weight);
        bmi1.setHeight(height);
        assertEquals(expectedBmi, bmi1.bmi(), 0);
        assertEquals(expectedCategory, bmi1.bmiCategory());
    }

    @ParameterizedTest
    @CsvSource({
            "Overweight Person1, 150, 65, 25.0, overweight",
            "Overweight Person2, 179, 65, 29.8, overweight",
            "Overweight Person3, 170, 66, 27.4, overweight",
    })
    void bmiCategory_overweight_overweightCategory(String name, int weight, int height, double expectedBmi, String expectedCategory) {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName(name);
        bmi1.setWeight(weight);
        bmi1.setHeight(height);
        assertEquals(expectedBmi, bmi1.bmi(), 0);
        assertEquals(expectedCategory, bmi1.bmiCategory());
    }

    @ParameterizedTest
    @CsvSource({
            "Obese Person1, 180, 65, 30.0, obese",
            "Obese Person2, 210, 65, 34.9, obese",
            "Obese Person3, 215, 68, 32.7, obese",
    })
    void bmiCategory_obese_obeseCategory(String name, int weight, int height, double expectedBmi, String expectedCategory) {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName(name);
        bmi1.setWeight(weight);
        bmi1.setHeight(height);
        assertEquals(expectedBmi, bmi1.bmi(), 0);
        assertEquals(expectedCategory, bmi1.bmiCategory());
    }

}