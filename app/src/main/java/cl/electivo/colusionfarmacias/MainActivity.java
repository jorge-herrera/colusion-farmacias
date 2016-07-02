package cl.electivo.colusionfarmacias;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
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

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#43A047")));

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
}
