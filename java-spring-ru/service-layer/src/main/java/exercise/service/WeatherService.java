package exercise.service;

import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeatherForCity(long id) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        ObjectMapper mapper = new ObjectMapper();

        String responce = client.get("http://weather/api/v2/cities/" + city.getName());

        Map<String, String> result;

        try {
            result = mapper.readValue(responce, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    // END
}
