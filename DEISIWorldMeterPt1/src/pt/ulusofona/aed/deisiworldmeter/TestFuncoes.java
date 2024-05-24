package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class TestFuncoes {

    @Test
    public void testStringIdMenor700() {

        String resultadoEsperado = "Afeganistão | 4 | AF | AFG";
        String resultadoAtual = new Paises(4,"af","afg","Afeganistão").toString();

        Assertions.assertEquals(resultadoEsperado,resultadoAtual,"Test failure");
    }

    @Test
    public void testStringIdMaior700() {

        String resultadoEsperado = "África do Sul | 710 | ZA | ZAF | 0";
        String resultadoAtual = new Paises(710,"za","zaf","África do Sul",0).toString();

        Assertions.assertEquals(resultadoEsperado,resultadoAtual,"Test failure");
    }

    @Test
    public void testStringCidades() {

        String resultadoEsperado = "canillo | AD | 02 | 3292 | (42.5666667,1.6)";
        String resultadoAtual = new Cidades("ad","canillo","02",3292,42.5666667,1.6).toString();

        Assertions.assertEquals(resultadoEsperado,resultadoAtual,"Test failure");
    }

    @Test
    public void testParseFiles() {

        boolean parseOk = Main.parseFiles(new File("test-files"));
        String[] paisEsperado = new String[] {"Albânia | 8 | AL | ALB","Alemanha | 276 | DE | DEU"};

        String[] cidadeEsperada = new String[] {"berat | AL | 40 | 47606 | (40.7058333,19.952222199999998)",
                "bilisht | AL | 46 | 7114 | (40.6275,20.99)",
                "hockenheim | DE | 01 | 20814 | (49.322778,8.545833)",
                "hofgeismar | DE | 05 | 16199 | (51.483333,9.4)"};

        Assertions.assertEquals(Arrays.toString(paisEsperado),Main.getObjects(TipoEntidade.PAIS).toString(),"Test failure");
        Assertions.assertEquals(Arrays.toString(cidadeEsperada),Main.getObjects(TipoEntidade.CIDADE).toString(),"Test failure");
    }

    @Test
    public void testParseFilesComErros() {

        boolean parseOk = Main.parseFiles(new File("test-files"));
        String[] inputInvalidoEsperado = new String[] {"paises.csv | 2 | 0 | -1",
                "cidades.csv | 4 | 0 | -1",
                "populacao.csv | 4 | 0 | -1"};

        Assertions.assertEquals(Arrays.toString(inputInvalidoEsperado),Main.getObjects(TipoEntidade.INPUT_INVALIDO).toString(),"Test failure");
    }
}