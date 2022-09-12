package dmit2015.javabean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    @Test
    void shouldBeUnderweight() {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName("Underweight Person");
        bmi1.setWeight(100);
        bmi1.setHeight(66);
        assertEquals(16.1, bmi1.bmi(), 0.05);
        assertEquals("underweight", bmi1.bmiCategory());
    }

    @Test
    void shouldBeNormal() {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName("Normal weight Person");
        bmi1.setWeight(140);
        bmi1.setHeight(66);
        assertEquals(22.6, bmi1.bmi(), 0.05);
        assertEquals("normal", bmi1.bmiCategory());
    }

    @Test
    void shouldBeOverweight() {
        BodyMassIndex bmi1 = new BodyMassIndex();
        bmi1.setName("Normal weight Person");
        bmi1.setWeight(175);
        bmi1.setHeight(66);
        assertEquals(28.2, bmi1.bmi(), 0.05);
        assertEquals("overweight", bmi1.bmiCategory());
    }

//    @Test
//    void shouldBeNormalOverweight() {
//        BodyMassIndex bmi1 = new BodyMassIndex();
//        bmi1.setName("Normal weight Person");
//        bmi1.setWeight(175);
//        bmi1.setHeight(66);
//        assertEquals(24.95, bmi1.bmi(), 0.05);
//        assertEquals("overweight", bmi1.bmiCategory());
//    }
}