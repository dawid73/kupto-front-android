package cloud.dawid.kupto;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Preference extends AppCompatActivity {

    Button btn_save, btn_oczytaj;
    EditText adres_serwera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_oczytaj = (Button) findViewById(R.id.btn_oczytaj);
        adres_serwera = (EditText) findViewById(R.id.adresSerwera);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences(adres_serwera.getText().toString());
            }
        });

        btn_oczytaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPreferences();


            }
        });
    }

    private void savePreferences(String adres){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("opcje", 0);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("adres", adres);
        editor.commit();

        Toast.makeText(this, "Zapisałem adres " + adres, Toast.LENGTH_LONG).show();
    }

    private void readPreferences(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("opcje", 0);
        String wynik = pref.getString("adres", "brak w bazie");

        Toast.makeText(this, "Adres serwera to: " + wynik, Toast.LENGTH_SHORT).show();

    }


}
