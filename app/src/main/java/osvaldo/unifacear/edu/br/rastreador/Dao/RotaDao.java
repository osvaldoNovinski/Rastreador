package osvaldo.unifacear.edu.br.rastreador.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import osvaldo.unifacear.edu.br.rastreador.Entity.Rota;

/**
 * Created by osval on 28/03/2019.
 */

public class RotaDao {

    SQLiteDatabase conn = Connect.getInstance();

    public void inserir (Rota rota){
        ContentValues values = new ContentValues();
        values.put("endereco", rota.getEndereco());
        values.put("idveiculo", rota.getIdveiculo());

        conn.insert("rota",null, values);
    }

    public List<Rota> listar(){
        Cursor c = conn.query("rota", new String[]{"endereco", "idveiculo"},null,null,null,null,null);

        List<Rota> rotas = new ArrayList<Rota>();


        if(c.moveToFirst()){
            do {
                Rota rota = new Rota();
                rota.setEndereco(c.getString(c.getColumnIndex("endereco")));
                rota.setIdveiculo(c.getInt(c.getColumnIndex("idveiculo")));
                rotas.add(rota);
            }while(c.moveToNext());
        }



        return rotas;
    }

}
