package osvaldo.unifacear.edu.br.rastreador;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import osvaldo.unifacear.edu.br.rastreador.Dao.Connect;
import osvaldo.unifacear.edu.br.rastreador.Dao.RotaDao;
import osvaldo.unifacear.edu.br.rastreador.Dao.VeiculoDao;
import osvaldo.unifacear.edu.br.rastreador.Entity.Rota;
import osvaldo.unifacear.edu.br.rastreador.Entity.Veiculo;

public class MainActivity extends AppCompatActivity {

    Spinner spinVeiculos;
    Veiculo veicu;
    Boolean para = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        new Connect(getApplicationContext(),
                "veiculo.db",
                null,
                1);
        new Connect(getApplicationContext(),
                "rota.db",
                null,
                1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

      // listar();


        // listarRota();

    }

    public void listar() {
        new Connect(getApplicationContext(),
                "veiculo.db",
                null,
                1);

        //ListView listView = findViewById(R.id.listaBd);

        List<Veiculo> veiculos = new VeiculoDao().listar();
        List<String> placas = new ArrayList<String>();


        if(veiculos.isEmpty()){

        }else {

            ArrayAdapter<Veiculo> arrayAdapterResultado =
                    new ArrayAdapter<Veiculo>(getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            veiculos);
            ArrayAdapter adapter =  new ArrayAdapter(this, android.R.layout.simple_spinner_item, veiculos);


            spinVeiculos = findViewById(R.id.spinVeiculos);
            spinVeiculos.setAdapter(adapter);

        }

        //listView.setAdapter(arrayAdapterResultado);

    }



    public void listarRota() {
        new Connect(getApplicationContext(),
                "rota.db",
                null,
                1);

        ListView listView = findViewById(R.id.posicoes);

        List<Rota> rotas = new RotaDao().listar();

        ArrayAdapter<Rota> arrayAdapterResultado =
                new ArrayAdapter<Rota>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        rotas);

        listView.setAdapter(arrayAdapterResultado);
    }

    public void telaCadastro() {
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // listar();
    }

    public void telaRastreio() {
        setContentView(R.layout.activity_rastreio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listar();
        listarRota();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cadastrar) {
            telaCadastro();
            return true;
        }

        if (id == R.id.rastrear) {
            telaRastreio();
           listar();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void pararRastreio(View v4){
        Button btnParar = findViewById(R.id.btnPara);
        if(btnParar.isPressed()){
            para = true;

            Toast.makeText(getApplicationContext(),"Parou", Toast.LENGTH_LONG).show();
        }
    }

    public void rastrear(View v3) {
        //listar();


            spinVeiculos = findViewById(R.id.spinVeiculos);
       // veicu = new Veiculo();
       // veicu = (Veiculo) spinVeiculos.getSelectedItem();
        LocationManager lm = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                try {
                   // if(findViewById(R.id.rastrear).isPressed()){
                     //   para=false;
                    //}
                    if(para == false) {
                        Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());

                        List<Address> local = gc.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);

                        String endereco = local.get(0).getAddressLine(0);
                        Toast.makeText(getApplicationContext(), "Veiculo: " + ((Veiculo) spinVeiculos.getSelectedItem()).getId(), Toast.LENGTH_LONG).show();


                        Rota rota = new Rota();
                        rota.setEndereco(endereco);
                        //rota.setIdveiculo(veicu.getId());
                        rota.setIdveiculo(((Veiculo) spinVeiculos.getSelectedItem()).getId());
                        RotaDao rotaDao = new RotaDao();
                        rotaDao.inserir(rota);
                        listarRota();
                    }else if(para == true){
                        Toast.makeText(getApplicationContext(), "Rastreamento Cancelado", Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }
        };

        String[] permissoes = {Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION};

        ActivityCompat.requestPermissions(this, permissoes, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(getApplicationContext(), "Sem permissão para localizações", Toast.LENGTH_LONG).show();
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, ll);
        //listarRota();
    }

    public void salvar(View v){
        Button btnSalvar = findViewById(R.id.btnSalvar);


        EditText placa = findViewById(R.id.placa);
        EditText modelo = findViewById(R.id.modelo);
        Spinner spin = findViewById(R.id.marca);

       // Toast.makeText(getApplicationContext(), "Salvou" , Toast.LENGTH_LONG).show();

       Veiculo vei = new Veiculo();

        vei.setPlaca(placa.getText().toString());
        vei.setModelo(modelo.getText().toString());
        vei.setMarca(spin.getSelectedItem().toString());



           //Toast.makeText(getApplicationContext(), "Id: "+vei.getId()+" Placa: "+vei.getPlaca()+" Modelo: "
             //    +vei.getModelo()+" Marca: "+vei.getMarca(), Toast.LENGTH_LONG).show();

      new VeiculoDao().inserir(vei);
      placa.setText("");
      modelo.setText("");
     // listar();
    }
}
