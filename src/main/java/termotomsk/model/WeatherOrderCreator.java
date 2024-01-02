package termotomsk.model;

import java.util.ArrayList;

public final class WeatherOrderCreator {
    public static ArrayList<ServerType> getOrder(Weather weather) {
        ArrayList<ServerType> order = new ArrayList<>();
        order.add(ServerType.Termo);
        if (weather.getServerIao().isActual() && weather.getServerIao().getTemp() != 0) {
            order.add(ServerType.Iao);
            order.add(ServerType.Yandex);
        } else {
            order.add(ServerType.Yandex);
            order.add(ServerType.Iao);
        }
        return order;
    }
}
