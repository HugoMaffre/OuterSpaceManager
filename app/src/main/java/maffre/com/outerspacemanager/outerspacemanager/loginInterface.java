package maffre.com.outerspacemanager.outerspacemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by mac2 on 07/03/2017.
 */

public interface loginInterface {

    @GET("/api/v1/users/get")
    Call<User> getUser (@Header("x-access-token") String token);


    @GET("/api/v1/users/0/11")
    Call<Users> getUsers (@Header("x-access-token") String token);


    @GET("/api/v1/buildings/list")
    Call<Buildings> getBuildings (@Header("x-access-token") String token);

    @GET("/api/v1/searches/list")
    Call<Researches> getResearches (@Header("x-access-token") String token);

    @GET("/api/v1/fleet/list")
    Call<Ships> getFleet (@Header("x-access-token") String token);


    @GET("/api/v1/ships")
    Call<Ships> getShips (@Header("x-access-token") String token);

    @POST("/api/v1/buildings/create/{buildingId}")
    Call<Building> createBuilding (@Header("x-access-token") String token, @Path("buildingId") int buildingId);

    @POST("/api/v1/searches/create/{searchId}")
    Call<Research> createResearch (@Header("x-access-token") String token, @Path("searchId") int searchId);

    @POST("/api/v1/ships/create/{shipId}")
    Call<Ship> createShip (@Header("x-access-token") String token, @Path("shipId") int shipId, @Body shipQueryObject amount);



}
