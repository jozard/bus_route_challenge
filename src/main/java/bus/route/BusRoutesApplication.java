package bus.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusRoutesApplication {

    private static String busRoutes;

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Bus routes file name is not specified.");
        }
        busRoutes = args[0];
        SpringApplication.run(BusRoutesApplication.class, args);
    }

    static String getBusRoutes() {
        return busRoutes;
    }
}
