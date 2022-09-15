package dmit2015.streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbertaCovid19DataManagerTest {

    @Test
    void constructor_readDataFromCSV_ReturnsCorrectSize() {
        // Arrange
        AlbertaCovid19DataManager dataManager = new AlbertaCovid19DataManager();
        // Act and Assert
        // Check the number of records read
        assertEquals(132, dataManager.getDataList().size());
        // Verify that first record is Edmonton Eastwood
        AlbertaCovid19Data firstData = dataManager.getDataList().get(0);
        assertTrue(firstData.getLocation().contains("Edmonton - Eastwood"));
        assertEquals(72038, firstData.getPopulationVaccinationData());
    }

}