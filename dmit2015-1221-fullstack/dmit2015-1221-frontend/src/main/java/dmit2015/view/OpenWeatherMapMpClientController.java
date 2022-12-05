package dmit2015.view;

import dmit2015.openweatherapi.OpenWeatherApi;
import dmit2015.restclient.OpenWeatherMapMpRestClient;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

import java.io.Serializable;

@Named("currentOpenWeatherMapMpClientController")
@ViewScoped
public class OpenWeatherMapMpClientController implements Serializable {

    @Getter @Setter
    private String city;

    @Inject
    @RestClient
    private OpenWeatherMapMpRestClient _openWeatherMapRestClient;

    @Inject
    @ConfigProperty(name = "OPENWEATHERMAP_APPID")
    @Getter @Setter
    private String openweathermapAppId;

    @Inject
    @ConfigProperty(name = "openweathermap.units")
    @Getter @Setter
    private String openweathermapUnits;

    public void onGetWeather() {
        try {
            OpenWeatherApi apiResponse = _openWeatherMapRestClient.findWeatherForCity(
                    city,openweathermapAppId,openweathermapUnits);
            String message = String.format("The current weather in %s is %s",
                    city,
                    apiResponse.getMain().getTemp());
            Messages.addGlobalInfo(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            Messages.addGlobalError("Error fetching weather information.");
        }
    }
}
