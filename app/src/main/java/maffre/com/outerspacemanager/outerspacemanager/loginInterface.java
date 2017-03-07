package maffre.com.outerspacemanager.outerspacemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * Created by mac2 on 07/03/2017.
 */

public interface loginInterface {

    @GET("/api/v1/users/get")
    Call<User> getUser (@Header("x-access-token") String token);



    @GET("/api/v1/buildings/list")
    Call<List<Building>> getBuildings (@Header("x-access-token") String token);

}
