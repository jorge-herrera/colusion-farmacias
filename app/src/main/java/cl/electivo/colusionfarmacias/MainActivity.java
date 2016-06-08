package cl.electivo.colusionfarmacias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText medicineText = (EditText) findViewById(R.id.medicineText);
        final Button buttonBuscarMedicamento = (Button) findViewById(R.id.searchMedicineButton);

        buttonBuscarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResultSearchActivity.class);
                intent.putExtra("medicamento", medicineText.getText().toString());
                startActivity(intent);
            }
        });

    }

    /*public void onClickBuscar (){

        Intent intent = new Intent(MainActivity.this,ResultSearchActivity.class);
        startActivity(intent);
    }*/
}
