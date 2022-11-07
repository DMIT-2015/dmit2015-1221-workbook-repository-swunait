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

    @Getter
    private Map<String, Todo> todoMap;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            todoMap = _todoMpRestClient.findAll();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}