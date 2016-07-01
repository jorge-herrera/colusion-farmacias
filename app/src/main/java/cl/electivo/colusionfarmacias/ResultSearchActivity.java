package cl.electivo.colusionfarmacias;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class ResultSearchActivity extends AppCompatActivity {

    TextView titulo;
    ListView lista;
    SQLiteDatabase db;
    SearchDBHelper sdbh;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        Intent intent = getIntent();
        String medicamento = intent.getStringExtra("medicamento");

        setTitle(medicamento);

        titulo = (TextView) findViewById(R.id.tituloView);
        lista  = (ListView) findViewById(R.id.listFarmaciasView);

        sdbh = new SearchDBHelper(getApplicationContext());
        db   = sdbh.getReadableDatabase();

        cursor = sdbh.findFarmaciasByMedicamento(db, medicamento);

        DBCursorAdapter adapter = new DBCursorAdapter(getApplicationContext(),cursor,0);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long idFarmacia) {

                Intent intent = new Intent(ResultSearchActivity.this,DetailDrugStore.class);
                intent.putExtra("farmacia", String.valueOf(idFarmacia));
                startActivity(intent);
            }
        });
    }

    class DBCursorAdapter extends android.widget.CursorAdapter{

        public DBCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item_farmacia, parent, false);

        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tvFarmacia = (TextView) view.findViewById(R.id.tvNFarmacia);
            TextView tvPrecio   = (TextView) view.findViewById(R.id.tvPrecio);
            //Button   btnVer     = (Button) view.findViewById(R.id.btnVer);

            String  farmacia = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            Float   precio   = cursor.getFloat(cursor.getColumnIndexOrThrow("precio"));
            //Integer id       = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

            tvFarmacia.setText(farmacia);

            String sprecio = "$ "+String.valueOf(precio);
            tvPrecio.setText(sprecio);
        }
    }
}
