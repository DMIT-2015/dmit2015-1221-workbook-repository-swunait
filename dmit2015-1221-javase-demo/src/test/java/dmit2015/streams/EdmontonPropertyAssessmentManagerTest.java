package dmit2015.streams;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EdmontonPropertyAssessmentManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPropertyAssessments() {
//        EdmontonPropertyAssessmentManager dataManager = new EdmontonPropertyAssessmentManager();
        EdmontonPropertyAssessmentManager dataManager = EdmontonPropertyAssessmentManager.getInstance();
        assertEquals(416_014, dataManager.count());
    }

    @Test
    void getWards() {
//        EdmontonPropertyAssessmentManager dataManager = new EdmontonPropertyAssessmentManager();
        EdmontonPropertyAssessmentManager dataManager = EdmontonPropertyAssessmentManager.getInstance();
        List<String> wards = dataManager.wards();
        // There should be 12 wards in Edmonton
        assertEquals(12, wards.size());
        // The first ward should be Anirniq
        assertEquals("Anirniq", wards.get(0));
        // The last ward should be tastawiyiniwak
        assertEquals("tastawiyiniwak", wards.get(11));

    }
}