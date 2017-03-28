package maffre.com.outerspacemanager.outerspacemanager.network;

import maffre.com.outerspacemanager.outerspacemanager.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by maffreh on 06/03/2017.
 */

public interface SignUpInterface {


    @POST("/api/v1/auth/create")
    Call<User> createUser(@Body User user);



    @POST("/api/v1/auth/login")
    Call<User> connectUser( @Body User user);

}
