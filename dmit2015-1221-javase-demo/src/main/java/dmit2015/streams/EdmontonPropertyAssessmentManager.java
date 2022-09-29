package dmit2015.streams;

import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EdmontonPropertyAssessmentManager {

    private static EdmontonPropertyAssessmentManager INSTANCE;

    private EdmontonPropertyAssessmentManager() {
        try {
            Path csvPath = Path.of("/home/user2015/Downloads/Property_Assessment_Data__Current_Calendar_Year_.csv");
            try ( Stream<String> linesStream = Files.lines(csvPath) ) {
                propertyAssessments = linesStream
                        .skip(1)
                        .map(line -> EdmontonPropertyAssessment.parseCsv(line).orElse(null))
                        .filter(Objects::nonNull)
                        .toList();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            propertyAssessments = new ArrayList<>();
        }
    }

    public static EdmontonPropertyAssessmentManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EdmontonPropertyAssessmentManager();
        }
        return INSTANCE;
    }

    private List<EdmontonPropertyAssessment> propertyAssessments;

    public int count() {
        return propertyAssessments.size();
    }

    /**
     * Return a list of unique non empty ward name sorted ascending
     * @return
     */
    public List<String> wards() {
        return propertyAssessments.stream()
                .map(EdmontonPropertyAssessment::getWard)
//                .map(item -> item.getWard().replace(" Ward",""))
                .distinct()
                .filter(item -> !item.isBlank() )    // include only ward names that is only empty
                .map(wardName -> wardName.replace(" Ward", ""))
                .sorted()
                .toList();
    }

    /**
     * Return a list of EdmontonPropertyAssessment for the given neighourhood and
     * the assessedValue is between minAssessedValue and maxAssessedValue.
     * Sort the results by streetName, houseNumber, and suite.
     * @param neighbourhood
     * @param minAssessedValue
     * @param maxAssessedValue
     * @return
     */
    public List<EdmontonPropertyAssessment> findByNeighbourhoodAndAssessmentRange(
            String neighbourhood,
            int minAssessedValue,
            int maxAssessedValue
    )
    {
        return null;
    }
}
