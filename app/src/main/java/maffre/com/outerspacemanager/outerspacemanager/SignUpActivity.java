package maffre.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maffreh on 06/03/2017.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{

    private EditText password_field;
    private EditText id_field;
    private Button connect_button;
    private TextView fail_txt;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //récupération des éléments du layout
        password_field = (EditText) findViewById(R.id.password_field);
        id_field = (EditText) findViewById(R.id.id_field);
        connect_button = (Button) findViewById(R.id.connect_button);
        fail_txt = (TextView) findViewById(R.id.fail_txt);
        connect_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.connect_button:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                SignUpInterface service = retrofit.create(SignUpInterface.class);
                //Call<User> request= service.;

                //fail_txt.;

                break;
        }
    }

}
