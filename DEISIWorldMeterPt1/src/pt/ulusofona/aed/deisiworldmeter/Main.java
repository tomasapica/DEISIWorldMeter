package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static ArrayList<Paises> paises = new ArrayList<>();
    static ArrayList<Cidades> cidades = new ArrayList<>();
    static ArrayList<Populacao> populacao = new ArrayList<>();
    static ArrayList<InputInvalido> inputInvalido = new ArrayList<>();

    public static boolean searchId(ArrayList<Paises> paises,int id) {
        for(int i = 0; i < paises.size(); i++) {
            if(Objects.equals(paises.get(i).id,id)) {
                return true;
            }
        }
        return false;
    }
    public static int searchIdPopulacao(ArrayList<Populacao> populacao,int id) {
        int count = 0;
        for(int i = 0; i < populacao.size(); i++) {
            if(Objects.equals(populacao.get(i).id,id)) {
                count++;
            }
        }
        return count;
    }

    public static boolean searchAlfa2(ArrayList<Paises> paises,String alfa2) {
        for(int i = 0; i < paises.size(); i++) {
            if(Objects.equals(paises.get(i).alfa2,alfa2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static ArrayList getObjects(TipoEntidade tipo) {
        if(tipo == TipoEntidade.CIDADE) {
            return cidades;
        } else if(tipo == TipoEntidade.PAIS) {
            return paises;
        } else {
            return inputInvalido;
        }
    }

    public static boolean parseFiles(File folder) {

        // dar reset ao Array
        paises = new ArrayList<>();
        cidades = new ArrayList<>();
        populacao = new ArrayList<>();
        inputInvalido = new ArrayList<>();

        File ficheiroPaises = new File(folder,"paises.csv");
        File ficheiroCidades = new File(folder,"cidades.csv");
        File ficheiroPopulacao = new File(folder,"populacao.csv");

        Scanner scannerPaises = null;
        try {
            scannerPaises = new Scanner(ficheiroPaises);
        } catch (FileNotFoundException e) {
            return false;
        }

        Scanner scannerCidades = null;
        try {
            scannerCidades = new Scanner(ficheiroCidades);
        } catch (FileNotFoundException e) {
            return false;
        }

        Scanner scannerPopulacao = null;
        try {
            scannerPopulacao = new Scanner(ficheiroPopulacao);
        } catch (FileNotFoundException e) {
            return false;
        }

        boolean primeiraLinha = true;

        int ok = 0;
        int nok = 0;
        int primeiraNok = -1;

        while (scannerPaises.hasNext()) {
            String linha = scannerPaises.nextLine();

            if (primeiraLinha) {
                primeiraLinha = false;
            } else {
                String[] partes = linha.split(",");

                if(primeiraNok == -1 &&
                        (partes.length != 4 || partes[0].isEmpty() ||
                                !isNumber(partes[0]) || partes[1].isEmpty() ||
                                isNumber(partes[1]) || partes[2].isEmpty() ||
                                isNumber(partes[2]) || partes[3].isEmpty() ||
                                isNumber(partes[3]) || searchId(paises,Integer.parseInt(partes[0])))) {

                    primeiraNok = ok + 2;
                    nok++;
                } else if(partes.length != 4 || partes[0].isEmpty() ||
                        !isNumber(partes[0]) || partes[1].isEmpty() ||
                        isNumber(partes[1]) || partes[2].isEmpty() ||
                        isNumber(partes[2]) || partes[3].isEmpty() ||
                        isNumber(partes[3]) || searchId(paises,Integer.parseInt(partes[0]))) {

                    nok++;
                } else {
                        int id = Integer.parseInt(partes[0]);
                        String alfa2 = partes[1];
                        String alfa3 = partes[2];
                        String nome = partes[3];

                        Paises objetoPais = new Paises(id, alfa2, alfa3, nome);
                        paises.add(objetoPais);
                        ok++;
                }
            }
        }
        InputInvalido pais = new InputInvalido("paises.csv",ok,nok,primeiraNok);
        inputInvalido.add(pais);

        ok = 0;
        nok = 0;
        primeiraNok = -1;
        primeiraLinha = true;

        while (scannerCidades.hasNext()) {
            String linha = scannerCidades.nextLine();

            if (primeiraLinha) {
                primeiraLinha = false;
            } else {
                String[] partes = linha.split(",");

                if(primeiraNok == -1 &&
                        (partes.length != 6 || partes[0].isEmpty() ||
                                 partes[2].isEmpty() ||
                                 partes[3].isEmpty() ||
                                !isNumber(partes[3]) || partes[4].isEmpty() ||
                                !isNumber(partes[4]) || partes[5].isEmpty() ||
                                !isNumber(partes[5]) || !searchAlfa2(paises,partes[0]))) {

                    primeiraNok = ok + 2;
                    nok++;
                } else if(partes.length != 6 || partes[0].isEmpty() ||
                         partes[2].isEmpty() ||
                         partes[3].isEmpty() ||
                        !isNumber(partes[3]) || partes[4].isEmpty() ||
                        !isNumber(partes[4]) || partes[5].isEmpty() ||
                        !isNumber(partes[5]) || !searchAlfa2(paises,partes[0])) {

                    nok++;
                } else {
                    String alfa2 = partes[0];
                    String cidade = partes[1];
                    String regiao = partes[2];
                    int populacao = (int) Double.parseDouble((partes[3]));
                    double latitude = Double.parseDouble(partes[4]);
                    double longitude = Double.parseDouble(partes[5]);

                    Cidades objetoCidade = new Cidades(alfa2,cidade,regiao,populacao,latitude,longitude);
                    cidades.add(objetoCidade);
                    ok++;
                }
            }
        }
        InputInvalido cidade = new InputInvalido("cidades.csv",ok,nok,primeiraNok);
        inputInvalido.add(cidade);

        ok = 0;
        nok = 0;
        primeiraNok = -1;
        primeiraLinha = true;

        while (scannerPopulacao.hasNext()) {
            String linha = scannerPopulacao.nextLine();

            if (primeiraLinha) {
                primeiraLinha = false;
            } else {
                String[] partes = linha.split(",");

                if(primeiraNok == -1 && (partes.length != 5 ||
                        partes[0].isEmpty() || !isNumber(partes[0]) ||
                        partes[1].isEmpty() || !isNumber(partes[1]) ||
                        partes[2].isEmpty() || !isNumber(partes[2]) ||
                        partes[3].isEmpty() || !isNumber(partes[3]) ||
                        partes[4].isEmpty() || !isNumber(partes[4]) ||
                        !searchId(paises, Integer.parseInt(partes[0])))) {

                    primeiraNok = ok + 2;
                    nok++;
                } else if(partes.length != 5 ||
                        partes[0].isEmpty() || !isNumber(partes[0]) ||
                        partes[1].isEmpty() || !isNumber(partes[1]) ||
                        partes[2].isEmpty() || !isNumber(partes[2]) ||
                        partes[3].isEmpty() || !isNumber(partes[3]) ||
                        partes[4].isEmpty() || !isNumber(partes[4]) ||
                        !searchId(paises, Integer.parseInt(partes[0]))) {

                    nok++;
                } else {
                    int id = Integer.parseInt(partes[0]);
                    int ano = Integer.parseInt(partes[1]);
                    int populacaoMasculina = Integer.parseInt(partes[2]);
                    int populacaoFeminina = Integer.parseInt(partes[3]);
                    double densidade = Double.parseDouble(partes[4]);

                    Populacao objetoPopulacao = new Populacao(id,ano,populacaoMasculina,populacaoFeminina,densidade);
                    populacao.add(objetoPopulacao);
                    ok++;
                }
            }
        }
        InputInvalido popul = new InputInvalido("populacao.csv",ok,nok,primeiraNok);
        inputInvalido.add(popul);

        for(int i = 0; i < paises.size(); i++){
            if(paises.get(i).id > 700) {
                int numPop = searchIdPopulacao(populacao,paises.get(i).id);
                Paises paises2 = new Paises(paises.get(i).id,paises.get(i).alfa2,paises.get(i).alfa3,paises.get(i).nome,numPop);
                paises.set(i,paises2);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao DEISI World Meter");

        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("."));
        if (!parseOk) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }

        long end = System.currentTimeMillis();
        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + " ms");
        System.out.println("Informações sobre a leitura dos ficheiros: ");
        System.out.println("nome | linhas OK | Linhas NOK | primeira linha NOK");
        ArrayList inputInvalido = getObjects(TipoEntidade.INPUT_INVALIDO);
        System.out.println(inputInvalido.get(0));
        System.out.println(inputInvalido.get(1));
        System.out.println(inputInvalido.get(2));
    }
}