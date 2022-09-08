package dmit2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test // 2^3 should be 8
    public void testPowerOperator() {
        //assertEquals( 8, 2^3 );
        assertEquals(8, Math.pow(2, 3));
    }

    @Test // 4/5 should be 0.80
    public void testDivision() {
        //assertEquals( 0.80, 4 / 5, 0 );
        assertEquals(0.8, (double) 4 / 5, 0);
    }

    @Test // can you use == operator to compare strings?
    public void testStringCompare() {
        assertTrue( "dmit2015" == "dmit2015" );
    }

    @Test // is an exception throw when dividing by zero?
    public void testForException() {
//        assertEquals( 0, 3 / 0 );
        Exception ex = assertThrows(ArithmeticException.class, () -> {
            assertEquals(0, 3 / 0);
        });
        assertEquals("/ by zero", ex.getMessage());
    }

    @Test
    void area() {
        Circle circle1 = new Circle();
        circle1.setRadius(10);
        assertEquals(314.16, circle1.area(), 0.005);
        circle1.setRadius(20);
        assertEquals(1256.64, circle1.area(), 0.005);
    }

    @Test
    void circumference() {
        Circle circle1 = new Circle();
        circle1.setRadius(10);
        assertEquals(62.83, circle1.circumference(), 0.005);
        circle1.setRadius(20);
        assertEquals(125.66, circle1.circumference(), 0.005);
    }
}