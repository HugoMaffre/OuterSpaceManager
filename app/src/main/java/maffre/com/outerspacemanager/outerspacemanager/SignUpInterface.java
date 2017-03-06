package maffre.com.outerspacemanager.outerspacemanager;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maffreh on 06/03/2017.
 */

public interface SignUpInterface {




    /*
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0ODk0MjA0MTkwODcsImlhdCI6MTQ4ODgxNTYxOX0.83sfJ67UbBY-LxvpWGHX3GOUOLkRTpxZqagoxKcmcps",
            "expires": 1489420419087
    }
    */



    @GET("https://outer-space-manager.herokuapp.com/api/v1/users")
    Call<User> listRepos(@Path("user") String user);

}
