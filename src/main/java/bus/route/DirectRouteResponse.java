package bus.route;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Provides response entity containing information about direct connection existence between departure and arrival bus
 * stations.
 *
 * @author Nikita Shvinagir
 */
public class DirectRouteResponse {
    @JsonProperty("dep_sid")
    private int departureStationId;
    @JsonProperty("arr_sid")
    private int arrivalStationId;
    @JsonProperty("direct_bus_route")
    private boolean isDirect;
    private String message;

    /**
     * Constructs new response entity with parameters provided.
     * By default route is considered as non-direct.
     *
     * @param departureStationId departure station id.
     * @param arrivalStationId   arrival station id.
     */
    public DirectRouteResponse(int departureStationId, int arrivalStationId) {
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
    }

    /**
     * @return {@code int} value representing departure station id.
     */
    public int getDepartureStationId() {
        return departureStationId;
    }

    /**
     * @return {@code int} value representing arrival station id.
     */
    public int getArrivalStationId() {
        return arrivalStationId;
    }

    /**
     * @return {@code true} if route between departure and arrival stations is direct. Otherwise returns {@code false}.
     */
    public boolean getDirect() {
        return isDirect;
    }

    /**
     * Sets the {@code boolean} value tha defines if route between departure and arrival stations is direct.
     *
     * @param direct {@code boolean} value representing if route is direct.
     */
    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    /**
     * @return {@code String} value containing response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message {@code String} value representing the response message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
