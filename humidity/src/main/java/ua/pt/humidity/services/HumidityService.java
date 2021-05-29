package ua.pt.humidity.services;

import ua.pt.humidity.entities.Humidity;

public interface HumidityService {

    Iterable<Humidity> listAllHumiditys();

    Humidity getHumidityById(long id);

    Humidity saveHumidity(Humidity humidity);

    void deleteHumidityById(long id);

    long count();

    Humidity getRecentHumidity(long houseId, long roomId);
}
