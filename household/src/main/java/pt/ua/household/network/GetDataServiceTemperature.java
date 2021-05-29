package pt.ua.household.network;

import pt.ua.household.model.Temperature;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetDataService {

    @GET("{id}/")
    Call<Temperature> getRecentTemperature(@Path("id") long id);

}
