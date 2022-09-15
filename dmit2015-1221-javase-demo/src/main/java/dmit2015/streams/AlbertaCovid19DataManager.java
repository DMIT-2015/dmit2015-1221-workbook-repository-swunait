package dmit2015.streams;

import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AlbertaCovid19DataManager {

    @Getter
    private List<AlbertaCovid19Data> dataList;

    public AlbertaCovid19DataManager() {
        try {
            Path csvPath = Path.of(getClass().getClassLoader().getResource("data/csv/COVID-19_in_Alberta__Current_cases_by_local_geographic_area.csv").toURI());
//            Path csvPath = Path.of("/home/user2015/Downloads/COVID-19_in_Alberta__Current_cases_by_local_geographic_area.csv");
            Stream<String> linesStream = Files.lines(csvPath);
            dataList = linesStream
                    .skip(1)
                    .map(line -> AlbertaCovid19Data.parseCsv(line).orElse(null))
                    .filter(Objects::nonNull)
                    .toList();
        } catch (Exception e) {
            dataList = new ArrayList<>();
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }

    public int totalCityPopulationVaccinationData(String city) {
        int totalPopulationVaccinationData = 0;

        totalPopulationVaccinationData = dataList
                .stream()
                .filter(item -> item.getLocation().contains(city))
                //.map(AlbertaCovid19Data::getPopulationVaccinationData)
                .mapToInt(AlbertaCovid19Data::getPopulationVaccinationData)
                .sum();

        return totalPopulationVaccinationData;
    }
}
