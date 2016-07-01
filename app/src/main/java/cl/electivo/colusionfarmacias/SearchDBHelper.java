package cl.electivo.colusionfarmacias;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jorge Herrera on 10-06-16.
 */
public class SearchDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "precios.db";
    private static final String TAG = "SQLiteOpenHelper";

    //"CREATE TABLE sqlite_sequence(name,seq);" +
    private static final String SQL_CREATE_FARMACIA =
            "CREATE TABLE farmacia (" +
            "	id	INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	nombre	TEXT," +
            "	direccion	TEXT," +
            "	telefono	TEXT," +
            "	x	TEXT," +
            "	y	TEXT" +
            "	placeid	TEXT" +
            ");";

    private static final String SQL_CREATE_MEDICAMENTO =
            "CREATE TABLE medicamento (" +
            "	id	INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	nombre	TEXT" +
            ");";

    private static final String SQL_CREATE_PRECIO =
            "CREATE TABLE precio (" +
            "	id_medicamento	INTEGER," +
            "	id_farmacia	INTEGER," +
            "	precio	REAL," +
            "	PRIMARY KEY(id_medicamento,id_farmacia)" +
            ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS farmacia;" +
            "DROP TABLE IF EXISTS medicamento;" +
            "DROP TABLE IF EXISTS precio;";


    public SearchDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_FARMACIA);
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('Cruz Verde', 'Av. Libertador Bernardo O Higgins 3479', '80080 28 00', '-33.4518445', '-70.6827685', 'EmBBdiBMaWJlcnRhZG9yIEJlcm5hcmRvIE8nSGlnZ2lucyAzNDc5LCBTYW50aWFnbywgRXN0YWNpw7NuIENlbnRyYWwsIFJlZ2nDs24gTWV0cm9wb2xpdGFuYSwgQ2hpbGU');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('Belgo Chilena', 'Av. Libertador Bernardo O Higgins 3700', '2776 5182', '-33.453406', '-70.6878551', 'EmBBdiBMaWJlcnRhZG9yIEJlcm5hcmRvIE8nSGlnZ2lucyAzNzAwLCBTYW50aWFnbywgRXN0YWNpw7NuIENlbnRyYWwsIFJlZ2nDs24gTWV0cm9wb2xpdGFuYSwgQ2hpbGU');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('Salcobrand', 'Av. Libertador Bernardo O Higgins 3770', '2422 7100', '-33.4533806', '-70.6871762', 'ChIJwwVC3_XEYpYRk7qRjWRQWSg');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('Dr. Simi', 'Av. Libertador Bernardo O Higgins 4102', '2361 0321', '-33.4542976', '-70.6921995', 'EmBBdiBMaWJlcnRhZG9yIEJlcm5hcmRvIE8nSGlnZ2lucyA0MTAyLCBTYW50aWFnbywgRXN0YWNpw7NuIENlbnRyYWwsIFJlZ2nDs24gTWV0cm9wb2xpdGFuYSwgQ2hpbGU');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('Ahumada', 'Av. Libertador Bernardo O Higgins 4103', '2631 3815', '-33.4540477', '-70.6923085', 'EmBBdiBMaWJlcnRhZG9yIEJlcm5hcmRvIE8nSGlnZ2lucyA0MTAzLCBTYW50aWFnbywgRXN0YWNpw7NuIENlbnRyYWwsIFJlZ2nDs24gTWV0cm9wb2xpdGFuYSwgQ2hpbGU');");

        db.execSQL(SQL_CREATE_MEDICAMENTO);
        db.execSQL("INSERT INTO medicamento(nombre) VALUES('paracetamol');");
        db.execSQL("INSERT INTO medicamento(nombre) VALUES('ibuprofeno');");
        db.execSQL("INSERT INTO medicamento(nombre) VALUES('diclofenaco');");
        db.execSQL("INSERT INTO medicamento(nombre) VALUES('nitrendipino');");
        db.execSQL("INSERT INTO medicamento(nombre) VALUES('metformina');");

        db.execSQL(SQL_CREATE_PRECIO);
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(1, 1, 1000);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(1, 2, 1100);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(1, 3, 1200);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(2, 3, 2000);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(2, 4, 2200);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(2, 5, 2400);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(3, 1, 5000);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(3, 2, 5200);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(3, 5, 5400);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(4, 3, 2000);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(4, 4, 2200);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(4, 1, 2400);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(5, 1, 7000);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(5, 3, 7500);");
        db.execSQL("INSERT INTO precio(id_medicamento, id_farmacia, precio) VALUES(5, 5, 7900);");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    public Cursor findFarmaciasByMedicamento(SQLiteDatabase db, String medicamento)
    {
        Cursor cursor;
        cursor = db.rawQuery("select f.id as _id, f.nombre, p.precio from farmacia as f join precio as p on f.id = p.id_farmacia join medicamento as m on m.id = p.id_medicamento  where m.nombre = ? ORDER BY p.precio ASC;", new String[] {medicamento});
        return cursor;
    }

    public Farmacia findFarmaciaById(SQLiteDatabase db, int id)
    {
        Cursor cursor;
        cursor = db.rawQuery("select id, nombre, direccion, telefono, x, y, placeid from farmacia where id = ?;", new String[] {String.valueOf(id)});

        if (cursor != null)
            cursor.moveToFirst();

        Farmacia f = new Farmacia();
        f.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        f.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
        f.setDireccion(cursor.getString(cursor.getColumnIndexOrThrow("direccion")));
        f.setTelefono(cursor.getString(cursor.getColumnIndexOrThrow("telefono")));
        f.setX(cursor.getLong(cursor.getColumnIndexOrThrow("x")));
        f.setY(cursor.getLong(cursor.getColumnIndexOrThrow("y")));
        f.setPlaceid(cursor.getString(cursor.getColumnIndexOrThrow("placeid")));


        cursor.close();

        return f;
    }
}

// AIzaSyBy6BcevJtkDvBC8SJbuiMg1r4ee7M3Qv8