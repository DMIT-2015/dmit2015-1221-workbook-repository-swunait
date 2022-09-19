package dmit2015.streams;

import lombok.Data;

import java.util.Optional;

@Data
public class EdmontonPropertyAssessment {

    private String ward;

    public static Optional<EdmontonPropertyAssessment> parseCsv(String line) {
        Optional<EdmontonPropertyAssessment> optionalEdmontonPropertyAssessment = Optional.empty();
        final String DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] tokens = line.split(DELIMITER, -1);  // The -1 limit allows for any number of fields and not discard trailing empty fields
        if (tokens.length == 18) {
            /*
            The order of the columns are as follows:
            0) accountNumber
            1) suite
            2) houseNumber
            3) streetName
            6) neighbourhood
            7) ward
            8) assessedValue
            11) pointLocation
             */
            EdmontonPropertyAssessment parsedEdmontonPropertyAssessment = new EdmontonPropertyAssessment();
            try {
                String ward = tokens[7];
                parsedEdmontonPropertyAssessment.setWard(ward);
                optionalEdmontonPropertyAssessment = Optional.of(parsedEdmontonPropertyAssessment);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return optionalEdmontonPropertyAssessment;
    }
}
