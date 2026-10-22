package gal.usc.etse.soap;

import gal.usc.etse.soap.calculator.Calculator;
import gal.usc.etse.soap.textAnalyzer.TextAnalyzer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Scanner;

public class Client {
    //Deixo aquí as variables globais, que son o scanner e os valores por defecto
    private static final Scanner scanner = new Scanner(System.in);
    private static final int DEFAULT_A = 2;
    private static final int DEFAULT_B = 1;
    private static final int[] DEFAULT_NUMS = {12, 4, 7, 4, 9, 12, 4, 15, 7, 4, 20, 9};
    private static final String DEFAULT_TEXTO =
            "Corbin Bleu Reivers, nado o 21 de febreiro de 1989, "
                    + "máis coñecido como Corbin Bleu, é un actor, modelo, "
                    + "bailarín e cantante estadounidense. É coñecido polo "
                    + "seu papel en High School Musical, a serie de televisión "
                    + "de Discovery Kids chamada Flight 29 Down e a película "
                    + "orixinal de Disney Channel titulada Jump In!. Tamén "
                    + "ten unha carreira en solitario e lanzou o seu primeiro "
                    + "álbum, Another Side, o 1 de maio de 2007. O álbum "
                    + "atinxiu a cifra de 18.000 copias vendidas na primeira "
                    + "semana. Bleu publicou o seu segundo álbum, Speed of "
                    + "Light, o 10 de marzo de 2009 nos Estados Unidos de América.";
    private static final String DEFAULT_PALABRA = "seu";
    private static final String DEFAULT_SUSTITUCION = "meu";

    public static void main(String[] args){
        try {
            //Inicializamos os dous servizos a partir das URLs proporcionadas polo servidor
            URL wsdlURL = new URL("http://localhost:8080/calculator?wsdl");
            QName SERVICE_NAME = new QName("http://calculator.soap.etse.usc.gal/", "Calculator");
            Service CalculatorService = Service.create(wsdlURL, SERVICE_NAME);
            Calculator CalculatorClient = CalculatorService.getPort(Calculator.class);
            URL wsdlURL2 = new URL("http://localhost:8080/textAnalyzer?wsdl");
            QName SERVICE_NAME_2 = new QName("http://textAnalyzer.soap.etse.usc.gal/", "TextAnalyzer");
            Service AnalyzerService = Service.create(wsdlURL2, SERVICE_NAME_2);
            TextAnalyzer AnalyzerClient = AnalyzerService.getPort(TextAnalyzer.class);
            System.out.println("Servizos SOAP conectados.");
            boolean activo = true;
            //Instanciamos un cliente para cada un, pero neste caso teremos un bucle no cal o usuario escolle cal usar e, unha vez dentro deste, realiza as operacións pertinentes
            //Polo tanto, os clientes non son concorrentes
            while (activo) {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Calculadora");
                System.out.println("2. Analizador de textos");
                System.out.println("0. Saír");

                String option = readLine("Escolle unha opción: ");

                switch (option) {
                    case "1":
                        calculatorMenu(CalculatorClient);
                        break;
                    case "2":
                        textMenu(AnalyzerClient);
                        break;
                    case "0":
                        activo = false;
                        break;
                    default:
                        System.out.println("Opción non válida.");
                }
            }       //Xestionamos as excepcións e pechamos o scanner antes de finalizar o programa
        } catch (Exception e) { System.out.println("Non foi posible conectar cos servizos SOAP. Comproba que o servidor está iniciado.");
        } finally { scanner.close();
            System.out.println("Cliente finalizado");}

    }

    private static void calculatorMenu(Calculator c) {
        while (true) {  //Cliente da calculadora, procesa as operacións ofrecidas
            System.out.println("\n=== CALCULADORA ===");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz cadrada");
            System.out.println("7. Logaritmo neperiano");
            System.out.println("8. Máximo dunha lista");
            System.out.println("9. Mínimo dunha lista");
            System.out.println("10. Media");
            System.out.println("11. Mediana");
            System.out.println("12. Moda");
            System.out.println("13. Desviación típica");
            System.out.println("0. Volver ao menú principal");

            String option = readLine("Operación: ");

            if (option.equals("0")) {
                return;
            }

            try {
                int a;
                int b;
                int[] nums;
                double result;

                switch (option) {
                    case "1":   //Para as operacións de suma e multiplicación, temos un overload do método, polo que deixamos ao usuario escoller que versión usar
                        System.out.println("Desexa sumar dous números ou un array deles?");
                        System.out.println("1. Suma de dous números");
                        System.out.println("2. Suma dun array de números");
                        String option2 = readLine("Tipo de suma: ");
                        switch (option2) {
                            case "1":
                                a = readInt("Primeiro sumando", DEFAULT_A); //Usamos distintamente as funcións readInt, readIntArray e, despois, readText para asegurar o correcto formato dos inputs máis fácilmente
                                b = readInt("Segundo sumando", DEFAULT_B);
                                System.out.println("Resultado: " + c.sum(a, b));
                                break;
                            case "2":
                                nums = readIntArray(DEFAULT_NUMS);
                                System.out.println("Resultado: " + c.sum(nums));
                                break;
                            default:
                                System.out.println("Opción non válida. Realizarase a suma de dous números por defecto."); //Neste caso, considero que non vale a pena facer un bucle, polo que se o input é inválido tomamos unha decisión
                                a = readInt("Primeiro sumando", DEFAULT_A);
                                b = readInt("Segundo sumando", DEFAULT_B);
                                System.out.println("Resultado: " + c.sum(a, b));
                                break;
                        }
                        break;

                    case "2":
                        a = readInt("Minuendo", DEFAULT_A);
                        b = readInt("Subtraendo", DEFAULT_B);
                        System.out.println("Resultado: " + c.resta(a, b));
                        break;

                    case "3":
                        System.out.println("Desexa multiplicar dous números ou un array deles?");
                        System.out.println("1. Multiplicación de dous números");
                        System.out.println("2. Multiplicación dun array de números");
                        String option3 = readLine("Tipo de multiplicación: ");
                        switch (option3) {
                            case "1":
                                a = readInt("Primeiro factor", DEFAULT_A);
                                b = readInt("Segundo factor", DEFAULT_B);
                                System.out.println("Resultado: " + c.prod(a, b));
                                break;
                            case "2":
                                nums = readIntArray(DEFAULT_NUMS);
                                System.out.println("Resultado: " + c.prod(nums));
                                break;
                            default:
                                System.out.println("Opción non válida. Realizarase a multiplicación de dous números por defecto.");
                                a = readInt("Primeiro factor", DEFAULT_A);
                                b = readInt("Segundo factor", DEFAULT_B);
                                System.out.println("Resultado: " + c.prod(a, b));
                                break;
                        }
                        break;

                    case "4":
                        a = readInt("Numerador", DEFAULT_A);
                        b = readInt("Denominador", DEFAULT_B);
                        System.out.println("Resultado: " + c.div(a, b));
                        break;

                    case "5":
                        a = readInt("Base", DEFAULT_A);
                        b = readInt("Expoñente", DEFAULT_B);
                        System.out.println("Resultado: " + c.pow(a, b));
                        break;

                    case "6":
                        a = readInt("Número", DEFAULT_A);
                        System.out.println("Resultado: " + c.sqrt(a));
                        break;

                    case "7":
                        a = readInt("Número", DEFAULT_A);
                        System.out.println("Resultado: " + c.ln(a));
                        break;

                    case "8":
                        nums = readIntArray(DEFAULT_NUMS);
                        System.out.println("Resultado: " + c.max(nums));
                        break;

                    case "9":
                        nums = readIntArray(DEFAULT_NUMS);
                        System.out.println("Resultado: " + c.min(nums));
                        break;

                    case "10":
                        nums = readIntArray(DEFAULT_NUMS);
                        result = c.media(nums);
                        System.out.println("Resultado: " + result);
                        break;

                    case "11":
                        nums = readIntArray(DEFAULT_NUMS);
                        result = c.med(nums);
                        System.out.println("Resultado: " + result);
                        break;

                    case "12":
                        nums = readIntArray(DEFAULT_NUMS);
                        System.out.println("Resultado: " + c.moda(nums));
                        break;

                    case "13":
                        nums = readIntArray(DEFAULT_NUMS);
                        System.out.println("Resultado: " + c.sd(nums));
                        break;

                    default:
                        System.out.println("Operación non válida.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao executar a operación: "+ e.getMessage());
            }
        }
    }

    private static void textMenu(TextAnalyzer t) {
        while (true) {
            System.out.println("\n=== ANALIZADOR DE TEXTOS ===");
            System.out.println("1. Contar palabras");
            System.out.println("2. Contar caracteres");
            System.out.println("3. Contar frases");
            System.out.println("4. Contar aparicións dunha palabra");
            System.out.println("5. Palabra máis usada");
            System.out.println("6. Palabra menos usada");
            System.out.println("7. Reemplazar palabra");
            System.out.println("0. Volver ao menú principal");

            String option = readLine("Operación: ");

            if (option.equals("0")) {
                return;
            }

            try {
                String text;
                String word;
                String replacement;

                switch (option) {
                    case "1":
                        text = readText();
                        System.out.println("Resultado: " + t.contaP(text));
                        break;

                    case "2":
                        text = readText();
                        System.out.println("Resultado: " + t.contaC(text));
                        break;

                    case "3":
                        text = readText();
                        System.out.println("Resultado: " + t.contaF(text));
                        break;

                    case "4":
                        text = readText();
                        word = readString("Palabra que buscar", DEFAULT_PALABRA);
                        System.out.println("Resultado: " + t.rep(text, word));
                        break;

                    case "5":
                        text = readText();
                        System.out.println("Resultado: " + t.mais(text));
                        break;

                    case "6":
                        text = readText();
                        System.out.println("Resultado: " + t.menos(text));
                        break;

                    case "7":
                        text = readText();
                        word = readString("Palabra que substituír", DEFAULT_PALABRA);
                        replacement = readString("Nova palabra", DEFAULT_SUSTITUCION);
                        System.out.println("Resultado: \n" + t.reemp(text, word, replacement));
                        break;

                    default:
                        System.out.println("Operación non válida.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao executar a operación: "
                        + e.getMessage());
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt, int vDefault) {
        while (true) {
            String linha = readLine(
                    prompt + " (Enter/default = " + vDefault + "): "
            );

            if (linha.isEmpty() || linha.equalsIgnoreCase("default")) {
                return vDefault;
            }

            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número enteiro válido.");
            }
        }
    }

    private static int[] readIntArray(int[] defaultValue) {
        StringBuilder defaults = new StringBuilder();

        for (int i = 0; i < defaultValue.length; i++) {
            if (i > 0) {
                defaults.append(", ");
            }
            defaults.append(defaultValue[i]);
        }

        while (true) {
            String value = readLine(
                    "Lista de enteiros separados por comas "
                            + "(Enter/default = " + defaults + "): "
            );

            if (value.isEmpty() || value.equalsIgnoreCase("default")) {
                return defaultValue.clone();
            }

            try {
                String[] parts = value.split(",");
                int[] numbers = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i].trim());
                }

                return numbers;
            } catch (NumberFormatException e) {
                System.out.println(
                        "Formato incorrecto. Exemplo correcto: 3, 8, -2, 10, 0, 7"
                );
            }
        }
    }


    private static String readText() {
        System.out.println("Introduce o texto. Escribe END nunha liña independente para rematar. Tamén podes premer Enter directamente para usar o texto predefinido.");

        String primeiraLinha = scanner.nextLine();

        if (primeiraLinha.trim().isEmpty()) {
            return DEFAULT_TEXTO;
        }

        if (primeiraLinha.trim().equalsIgnoreCase("default")) {
            return DEFAULT_TEXTO;
        }

        if (primeiraLinha.equals("END")) {
            return "";
        }

        StringBuilder texto = new StringBuilder();
        texto.append(primeiraLinha);

        while (true) {
            String linha = scanner.nextLine(); //Facemos directamente o next line para contar os espazos baleiros, que afectan á contaxe de caracteres

            if (linha.equals("END")) {
                break;
            }

            texto.append('\n').append(linha);
        }

        return texto.toString();
    }


    private static String readString(String prompt, String vDefault) {
        System.out.println(
                prompt + " (Enter/default para usar o valor predefinido):"
        );
        System.out.println(
                "Valor predefinido: " + vDefault
        );

        String linha = readLine("> ");

        if (linha.isEmpty() || linha.equalsIgnoreCase("default")) {
            return vDefault;
        }

        return linha;
    }
}
