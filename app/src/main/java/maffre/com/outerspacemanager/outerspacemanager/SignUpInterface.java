package maffre.com.outerspacemanager.outerspacemanager;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by maffreh on 06/03/2017.
 */

public interface SignUpInterface {


    @POST("/api/v1/auth/create")
    Call<User> createUser( @Body User user);

}
