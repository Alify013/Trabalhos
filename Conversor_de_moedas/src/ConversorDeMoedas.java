import com.google.gson.Gson;

import java.util.Map;
import java.util.Scanner;

public class ConversorDeMoedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true; // Controle do loop principal

        while (continuar) {
            exibirMenu(); // Exibe o menu
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa a entrada

            if (opcao == 7) {
                System.out.println("Encerrando o programa. Até logo!");
                continuar = false;
                break;
            }

            // Mapeia as opções do menu para as moedas
            String moedaBase = getMoedaBase(opcao);
            String moedaDestino = getMoedaDestino(opcao);

            if (moedaBase == null || moedaDestino == null) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            try {
                // Chama a API e processa a resposta
                String respostaJson = ApiHandler.obterTaxasDeCambio(moedaBase);
                RespostaAPI resposta = new Gson().fromJson(respostaJson, RespostaAPI.class);

                // Obtém a taxa de conversão
                Map<String, Double> taxasDeConversao = resposta.getConversionRates();
                if (taxasDeConversao == null || !taxasDeConversao.containsKey(moedaDestino)) {
                    System.out.println("Erro: Taxa de conversão não encontrada para " + moedaDestino);
                    continue;
                }

                // Solicita o valor a ser convertido
                System.out.println("Digite o valor a ser convertido de " + moedaBase + " para " + moedaDestino + ":");
                double valorOriginal = scanner.nextDouble();

                // Calcula o valor convertido
                double taxa = taxasDeConversao.get(moedaDestino);
                double valorConvertido = valorOriginal * taxa;

                // Exibe o resultado
                System.out.printf("Valor convertido: %.2f %s%n", valorConvertido, moedaDestino);

            } catch (Exception e) {
                System.out.println("Erro ao acessar a API ou processar os dados: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Exibe o menu principal
    private static void exibirMenu() {
        System.out.println("**************************************************");
        System.out.println("Seja Bem-vindo/a ao Conversor de Moeda = ]");
        System.out.println("1) Dólar (USD) => Peso argentino (ARS)");
        System.out.println("2) Peso argentino (ARS) => Dólar (USD)");
        System.out.println("3) Dólar (USD) => Real brasileiro (BRL)");
        System.out.println("4) Real brasileiro (BRL) => Dólar (USD)");
        System.out.println("5) Dólar (USD) => Peso colombiano (COP)");
        System.out.println("6) Peso colombiano (COP) => Dólar (USD)");
        System.out.println("7) Sair");
        System.out.println("Escolha uma opção válida:");
        System.out.println("**************************************************");
    }

    // Obtém a moeda base a partir da opção escolhida
    private static String getMoedaBase(int opcao) {
        return switch (opcao) {
            case 1, 3, 5 -> "USD";
            case 2 -> "ARS";
            case 4 -> "BRL";
            case 6 -> "COP";
            default -> null;
        };
    }

    // Obtém a moeda destino a partir da opção escolhida
    private static String getMoedaDestino(int opcao) {
        return switch (opcao) {
            case 1 -> "ARS";
            case 2 -> "USD";
            case 3 -> "BRL";
            case 4 -> "USD";
            case 5 -> "COP";
            case 6 -> "USD";
            default -> null;
        };
    }
}
