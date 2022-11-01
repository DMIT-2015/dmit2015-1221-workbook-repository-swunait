package dmit2015.view;

import dmit2015.openweatherapi.OpenWeatherApi;
import dmit2015.restclient.OpenWeatherMapMpRestClient;
import dmit2015.restclient.Todo;
import dmit2015.restclient.TodoMpRestClient;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;


@Named("currentTodoListController")
@ViewScoped
public class TodoListController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    @RestClient
    private TodoMpRestClient _todoMpRestClient;

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

    @Getter
    private Map<String, Todo> todoMap;

    @Getter
    private String feedbackMessage;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            todoMap = _todoMpRestClient.findAll();

            OpenWeatherApi apiResponse = _openWeatherMapRestClient.findWeatherForCity(
                    "Edmonton",openweathermapAppId,openweathermapUnits);
            String message = String.format("The current weather in Edmonton is %s", apiResponse.getMain().getTemp());
            System.out.println(message);
            //Messages.addGlobalInfo(message);
            feedbackMessage = message;
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}