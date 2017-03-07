package maffre.com.outerspacemanager.outerspacemanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;

public class BuildingsActivity extends AppCompatActivity {


    private ListView buildingsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        buildingsList = (ListView) findViewById(R.id.buildingsList);


        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");



        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<List<Building>> request = service.getBuildings (currentAccessToken);


        request.enqueue(new Callback<List<Building>>() {
            @Override
            public void onResponse(Call<List<Building>> call, Response<List<Building>> response) {

                buildingsList.add(response.body().getName());

            }

            @Override
            public void onFailure(Call<List<Building>> call, Throwable t) {

            }
        });




    }
}
