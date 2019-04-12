package osvaldo.unifacear.edu.br.rastreador.Entity;

/**
 * Created by osval on 28/03/2019.
 */

public class Rota {

    private String endereco;
    private Integer idveiculo;




    @Override
    public String toString() {
        return idveiculo +"  "+ endereco;
    }

    public Rota() {
    }
    public Rota(String endereco, Integer idveiculo) {
        this.endereco = endereco;
        this.idveiculo = idveiculo;
    }

    public Integer getIdveiculo() {
        return idveiculo;
    }

    public void setIdveiculo(Integer idveiculo) {
        this.idveiculo = idveiculo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
