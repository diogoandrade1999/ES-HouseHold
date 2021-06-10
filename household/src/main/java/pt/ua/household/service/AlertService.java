package pt.ua.household.service;


import pt.ua.household.model.Alert;

import java.util.List;

public interface AlertService {

    Alert getAlertById(long id);

    Alert saveAlert(Alert alert);

    void removeAlert(Alert alert);

    List<Alert> getAlertsByUserId(long userId);
}
