package maffre.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;

public class MenuActivity extends Activity implements View.OnClickListener {

    private SharedPreferences settings;
    private TextView points;
    private TextView username;
    private Button deconnect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);




        username = (TextView) findViewById(R.id.username);
        points = (TextView) findViewById(R.id.points);
        deconnect = (Button) findViewById(R.id.deconnect);
        deconnect.setOnClickListener(this);

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
        Call<User> request= service.getUser(currentAccessToken);


        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                username.append(response.body().getUsername());
                points.append(String.valueOf(response.body().getPoints()));


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deconnect:

                // Current Access Token
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("accessToken", "");

                break;
        }
    }
}
