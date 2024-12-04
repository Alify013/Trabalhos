import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHandler {

    // Método para obter taxas de câmbio em tempo real
    public static String obterTaxasDeCambio(String moedaBase) throws Exception {
        String url = "https://v6.exchangerate-api.com/v6/7ee2a30b291807be477ba3d7/latest/" + moedaBase;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body(); // Retorna o JSON como String
    }
}
