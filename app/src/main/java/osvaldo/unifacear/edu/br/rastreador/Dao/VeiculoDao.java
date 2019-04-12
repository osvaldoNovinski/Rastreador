package osvaldo.unifacear.edu.br.rastreador.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import osvaldo.unifacear.edu.br.rastreador.Entity.Veiculo;


/**
 * Created by osval on 15/03/2019.
 */

public class VeiculoDao {



    SQLiteDatabase conn = Connect.getInstance();

    public void inserir(Veiculo veiculo) {
        ContentValues values = new ContentValues();
        values.put("placa",veiculo.getPlaca());
        values.put("modelo",veiculo.getModelo());
        values.put("marca", veiculo.getMarca());

        conn.insert("veiculo",
                null,
                values);
    }



    public List<Veiculo> listar() {
        Cursor c = conn.query("veiculo",
                new String[] {"id","placa","modelo","marca"},
                null,
                null,
                null,
                null,
                "id asc");

        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        if (c.moveToFirst()) {
            do {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(c.getInt(c.getColumnIndex("id")));
                veiculo.setPlaca(c.getString(c.getColumnIndex("placa")));
                veiculo.setModelo(c.getString(c.getColumnIndex("modelo")));
                veiculo.setMarca(c.getString(c.getColumnIndex("marca")));
                veiculos.add(veiculo);
            } while (c.moveToNext());
        }

        return veiculos;
    }

}

