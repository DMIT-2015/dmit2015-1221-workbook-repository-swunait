package dmit2015.restclient;

import dmit2015.openweatherapi.OpenWeatherApi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient //(baseUri = "https://api.openweathermap.org/data/2.5")
public interface OpenWeatherMapMpRestClient {

    @GET
    @Path("/weather")
    OpenWeatherApi findWeatherForCity(
            @QueryParam("q") String city,
            @QueryParam("appid") String appid,
            @QueryParam("units") String units);

}
