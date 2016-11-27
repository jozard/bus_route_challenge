package bus.route;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * REST service that provides functionality to manage bus routes. Bus routes are located in the file that can be accessed
 * via {@link BusRoutesApplication#getBusRoutes()} method of the {@code BusRoutesApplication} entity.
 *
 * @author Nikita Shvinagir
 */
@RestController
@RequestMapping(path = "/api")
public class BusRouteService {

    /**
     * Checks if bus route with parameters specified is direct and returns response containing result.
     *
     * @param dep {@code Integer} entity representing departure station id. Cannot be null.
     * @param arr {@code Integer} entity representing arrival station id. Cannot be null.
     * @return {@link DirectRouteResponse} entity that contains information about about bus route. Cannot be null.
     */
    @RequestMapping(path = "/direct", method = RequestMethod.GET)
    public DirectRouteResponse getDirectRoute(@RequestParam(value = "dep_sid") Integer dep, @RequestParam(value = "arr_sid") Integer arr) {
        DirectRouteResponse response = new DirectRouteResponse(dep, arr);
        try {
            Stream<String> stream = Files.lines(Paths.get(BusRoutesApplication.getBusRoutes())).skip(1);
            stream.anyMatch(s -> {
                Stream<String> route = Stream.of(s.split(" "));
                List<Integer> routeStationList = route.skip(1).flatMapToInt(
                        str -> IntStream.of(Integer.valueOf(str))).boxed().collect(Collectors.toList());
                int depIndex = routeStationList.indexOf(dep);
                int arrIndex = routeStationList.indexOf(arr);
                /*
                  PLEASE NOTE TO THE LACK IN THE TASK DESCRIPTION!
                  This condition means the route is not reversible and the backwards route has it's own record.
                  Use the '&& arrIndex >= 0' condition instead if one route record is for round bus route.
                 */
                if (depIndex >= 0 && arrIndex > depIndex) {
                    response.setDirect(true);
                    return true;
                }
                return false;
            });
        } catch (FileNotFoundException e) {
            response.setMessage("Bus routes file name was not specified.");
        } catch (IOException e) {
            response.setMessage("Wrong bus routes file format.");
        }
        return response;
    }
}
