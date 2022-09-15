package dmit2015.streams;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class AlbertaCovid19Data {

    private LocalDate date;
    private String location;
    private int populationVaccinationData;

    public static Optional<AlbertaCovid19Data> parseCsv(String line) {
        Optional<AlbertaCovid19Data> optionalAlbertaCovid19Data = Optional.empty();

        final String DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] tokens = line.split(DELIMITER, -1);  // The -1 limit allows for any number of fields and not discard trailing empty fields
        if (tokens.length == 15) {
            AlbertaCovid19Data parsedAlbertaCovid19Data = new AlbertaCovid19Data();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            try {
                LocalDate date = LocalDate.parse(tokens[0], dtf);
                String location = tokens[1];
                int populationVaccinationData = tokens[2].isBlank() ? 0
                        : Integer.parseInt(tokens[2]);
                parsedAlbertaCovid19Data.setDate(date);
                parsedAlbertaCovid19Data.setLocation(location);
                parsedAlbertaCovid19Data.setPopulationVaccinationData(populationVaccinationData);

                optionalAlbertaCovid19Data = Optional.of(parsedAlbertaCovid19Data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return optionalAlbertaCovid19Data;
    }
}
