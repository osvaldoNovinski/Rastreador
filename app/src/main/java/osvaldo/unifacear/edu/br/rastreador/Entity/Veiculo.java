package osvaldo.unifacear.edu.br.rastreador.Entity;

/**
 * Created by osval on 15/03/2019.
 */

public class Veiculo {

    private Integer  id;
    private String placa;
    private String modelo;
    private String marca;

    @Override
    public String toString() {
        return id + "  " + placa + "  "+ modelo+"   "+marca;
    }

    public Veiculo() {

    }

    public Veiculo(Integer id, String placa, String modelo, String marca) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
