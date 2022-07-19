import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer conexão HTTP com a API do IMDB - no momento a API do IMDB estava
        // offline, estarei utilizando uma API criada para as aulas da imersão JAVA
        // https://alura-filmes.herokuapp.com/conteudos

        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI adress = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Pegar somente informações importantes (titulo, poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        // Exibir e manipular os dados
        for (Map<String, String> movie : moviesList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));

        }
    }
}
