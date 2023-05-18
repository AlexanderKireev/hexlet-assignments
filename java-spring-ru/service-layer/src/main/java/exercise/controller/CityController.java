package exercise.controller;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getInfoForCity(@PathVariable long id) {
        return weatherService.getWeatherForCity(id);
    }

//    @GetMapping("/search")
//    public List<Map<String, String>> getCities(@RequestParam(required = false) String name) {
//        List<City> cityList = name.isEmpty() ? cityRepository.findAllByOrderByNameAsc() : cityRepository.findByNameIgnoreCaseStartingWith(name);
//        return cityList.stream().map(
//                t -> {
//                    Map<String, String> map = null;
//                    try {
//                        map = Map.of("name", t.getName(), "temperature", weatherService.getWeatherForCity(t.getId()).get("temperature"));
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                    return map;
//                }).collect(Collectors.toList());
//    }


    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(required = false) String name) {

        List<City> filteredCities;

        if (name == null) {
            filteredCities = cityRepository.findAllByOrderByNameAsc();
        } else {
            filteredCities = cityRepository.findByNameIgnoreCaseStartingWith(name);
        }

        List<Map<String, String>> citiesWithWeather = filteredCities.stream()
                .map(city -> {
                    Map<String, String> weather = weatherService.getWeatherForCity(city.getId());
                    return Map.of(
                            "name", city.getName(),
                            "temperature", weather.get("temperature")
                    );
                })
                .collect(Collectors.toList());

        return citiesWithWeather;
    }
    // END
}

