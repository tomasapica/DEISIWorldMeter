package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    int id;
    int ano;
    int populacaoMasculina;
    int populacaoFeminina;
    double densidade;

    public Populacao(int id, int ano, int populacaoMasculina, int populacaoFeminina, double densidade) {
        this.id = id;
        this.ano = ano;
        this.populacaoMasculina = populacaoMasculina;
        this.populacaoFeminina = populacaoFeminina;
        this.densidade = densidade;
    }

    public Populacao() {

    }

    @Override
    public String toString(){
        return "";
    }
}