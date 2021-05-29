package ua.pt.light.services;

import ua.pt.light.entities.Light;

public interface LightService {

    Iterable<Light> listAllLights();

    Light getLightById(long id);

    Light saveLight(Light light);

    void deleteLightById(long id);

    long count();

    Light getRecentLuminosity(long houseId, long roomId);
}
