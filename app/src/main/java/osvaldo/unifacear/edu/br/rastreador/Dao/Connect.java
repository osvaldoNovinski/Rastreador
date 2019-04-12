package osvaldo.unifacear.edu.br.rastreador.Dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by osval on 01/03/2019.
 */

public class Connect extends SQLiteOpenHelper {
    private static SQLiteDatabase instance;
    public static SQLiteDatabase getInstance() {
        return instance;
    }
    public Connect(Context context,
                   String name,
                   SQLiteDatabase.CursorFactory factory,
                   int version) {
        super(context, name, factory, version);
        instance = getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table veiculo(" +
                "id integer not null primary key autoincrement," +
                " placa string not null," +
                "modelo string not null," +
                "marca string not null)");

        db.execSQL("create table rota(endereco string not null, idveiculo veiculo)");

        }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

