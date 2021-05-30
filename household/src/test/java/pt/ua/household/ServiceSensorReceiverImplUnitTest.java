package pt.ua.household;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pt.ua.household.model.*;
import pt.ua.household.service.ServiceSensorReceiverImpl;

import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ServiceSensorReceiverImplUnitTest {

    @Spy
    private ServiceSensorReceiverImpl serviceSensorReceiver =new ServiceSensorReceiverImpl();

    @BeforeEach
    public void setUp() {

    }
    @Test
    public void whenTemperatureIsBiggerOrEqualTo20_ThenACShouldBeTurnedOn(){

        Temperature temp = new Temperature(20, new Date(), 1,1);
        Humidity humidity = new Humidity();
        Luminosity luminosity = new Luminosity();
        Mockito.when(serviceSensorReceiver.resolveTemperatureState(1,1)).thenReturn(temp);
        Mockito.when(serviceSensorReceiver.resolveHumidityState(1,1)).thenReturn(humidity);
        Mockito.when(serviceSensorReceiver.resolveLightState(1,1)).thenReturn(luminosity);

        serviceSensorReceiver.getStates();

        House house = serviceSensorReceiver.getHouseInformation();

        Room changedRoom = null;
        for (Room r: house.getRooms()){
            if (r.getRoomId() == 1){
                changedRoom = r;

                break;
            }
        }
        assertThat(changedRoom.isAirConditionerOn()).isEqualTo(true);

    }

    @Test
    public void whenTemperatureIsLowerThan5_IfACWasOnThenACShouldBeOff(){
        House house;

        house = serviceSensorReceiver.getHouseInformation();

        for (Room r: house.getRooms()){
            if (r.getRoomId() == 1){
                r.setAirConditionerOn(true);
            }
        }
        Temperature temp = new Temperature(3, new Date(), 1,1);
        Humidity humidity = new Humidity();
        Luminosity luminosity = new Luminosity();
        Mockito.when(serviceSensorReceiver.resolveTemperatureState(1,1)).thenReturn(temp);
        Mockito.when(serviceSensorReceiver.resolveHumidityState(1,1)).thenReturn(humidity);
        Mockito.when(serviceSensorReceiver.resolveLightState(1,1)).thenReturn(luminosity);

        serviceSensorReceiver.getStates();

        house = serviceSensorReceiver.getHouseInformation();

        Room changedRoom = null;
        for (Room r: house.getRooms()){
            if (r.getRoomId() == 1){
                changedRoom = r;
                break;
            }
        }
        assertThat(changedRoom.isAirConditionerOn()).isEqualTo(false);
    }

    @Test
    public void whenTemperatureIsLowerThan5_IfACWasOffThenACShouldBeOff(){

        Temperature temp = new Temperature(3, new Date(), 1,1);
        Humidity humidity = new Humidity();
        Luminosity luminosity = new Luminosity();
        Mockito.when(serviceSensorReceiver.resolveTemperatureState(1,1)).thenReturn(temp);
        Mockito.when(serviceSensorReceiver.resolveHumidityState(1,1)).thenReturn(humidity);
        Mockito.when(serviceSensorReceiver.resolveLightState(1,1)).thenReturn(luminosity);

        serviceSensorReceiver.getStates();

        House house = serviceSensorReceiver.getHouseInformation();

        Room changedRoom = null;
        for (Room r: house.getRooms()){
            if (r.getRoomId() == 1){
                changedRoom = r;
                break;
            }
        }
        assertThat(changedRoom.isAirConditionerOn()).isEqualTo(false);
    }

}
