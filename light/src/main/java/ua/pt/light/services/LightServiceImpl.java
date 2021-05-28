package ua.pt.light.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pt.light.entities.Light;
import ua.pt.light.repositories.LightRepository;

@Service
public class LightServiceImpl implements LightService {

    @Autowired
    private LightRepository lightRepository;

    @Override
    public Iterable<Light> listAllLights() {
        return this.lightRepository.findAll();
    }

    @Override
    public Light saveLight(Light light) {
        return this.lightRepository.save(light);
    }

    @Override
    public long count() {
        return this.lightRepository.count();
    }

    @Override
    public Light getLightById(long id) {
        return this.lightRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLightById(long id) {
        this.lightRepository.deleteById(id);
    }
}
