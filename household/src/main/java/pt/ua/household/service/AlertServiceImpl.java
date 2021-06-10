package pt.ua.household.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.household.model.Alert;
import pt.ua.household.repositories.AlertRepository;

import java.util.List;

@Service
@Transactional
public class AlertServiceImpl implements AlertService{

    @Autowired
    private AlertRepository alertRepository;


    @Override
    public Alert getAlertById(long id) {
        return alertRepository.findById(id).orElse(null);
    }

    @Override
    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    @Override
    public void removeAlert(Alert alert) {
        alertRepository.delete(alert);
    }

    @Override
    public List<Alert> getAlertsByUserId(long userId) {
        return alertRepository.findByUserId(userId);
    }
}
