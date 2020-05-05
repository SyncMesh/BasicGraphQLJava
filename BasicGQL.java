import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BasicGQL {
    public static String GetReq(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(uri)).build();
        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static String PostReq(String uri, String Post) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(Post)).build();

        HttpResponse<String> response = client.send(req,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static void main(String[] args) {
        String server = "http://82.166.179.129:8080/rest/authors";
        String allBooks = "{\n" +
                "\tallBooks{\n" +
                "\t\tid\n" +
                "\t\ttitle\n" +
                "\t\tisbn\n" +
                "\t}\n" +
                "}\n";
        String author1 = "{\n" +
                "\tauthor(id: \"1\"){\n" +
                "\t\tid\n" +
                "\t\tfirstName\n" +
                "\t\tlastName\n" +
                "\t}\n" +
                "}\n";
        String CreateBook1 = "mutation{\n" +
                "\tcreateBook(id: \"10\", title: \"Hi Dude\", isbn: \"20440056\"){   \n" +
                "\t\tid\n" +
                "\t\ttitle\n" +
                "\t}\n" +
                "}\n";
        try {
            System.out.println(GetReq(server));
            System.out.print(PostReq(server, CreateBook1 ));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
