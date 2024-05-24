package pt.ulusofona.aed.deisiworldmeter;

public class InputInvalido {
    String nomeFicheiro;
    int ok;
    int nok;
    int primeiraNok;

    public InputInvalido(String nomeFicheiro, int ok, int nok, int primeiraNok) {
        this.nomeFicheiro = nomeFicheiro;
        this.ok = ok;
        this.nok = nok;
        this.primeiraNok = primeiraNok;
    }

    public InputInvalido() {

    }

    @Override
    public String toString() {
        return nomeFicheiro + " | " + ok + " | " + nok + " | " +  primeiraNok;
    }
}

