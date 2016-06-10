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

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "precios.db";
    private static final String TAG = "SQLiteOpenHelper";

    //"CREATE TABLE sqlite_sequence(name,seq);" +
    private static final String SQL_CREATE_FARMACIA =
            "CREATE TABLE farmacia (" +
            "	id	INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	nombre	TEXT," +
            "	x	TEXT," +
            "	y	TEXT" +
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
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('farmacia 1', '0', '0');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('farmacia 2', '0', '0');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('farmacia 3', '0', '0');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('farmacia 4', '0', '0');");
        db.execSQL("INSERT INTO farmacia(nombre, x, y) VALUES('farmacia 5', '0', '0');");

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
}
