package millionaire.Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import third-party library. This used to be able to handle Json array that will be fetched from the server.
import org.json.JSONArray;

/**
 * This interface have methods that will handle getting a Json data from the server (game questions).
 *
 * @author Mohammad.
 */
interface GetJson {
    /**
     * It will get json text from a page that have json data(backend)
     *
     * @param url refer to the page that show json text.
     */
    default void getJsonText(String url) {
        // create httpClient object.
        HttpClient client = HttpClient.newHttpClient();
        // create httpRequest object and prepare it to be able to send it.
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(this::setupJsonData).join();
        } catch (Exception e) {
            System.err.println("Couldn't get json data from the following link: " + url);
        }
    }

    /**
     * It will convert Json array to string array (used to get question options from every question object from json text).
     *
     * @param jsonArray json array that will be returned as a string array.
     * @return string array.
     */
    default String[] toStringArray(JSONArray jsonArray) {
        String[] stringArray = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            stringArray[i] = jsonArray.getString(i);
        }
        return stringArray;
    }

    /**
     * It will have behavior to handle json (data) text for example set every json object in a question object from questions array inside game class.(It will be called inside getJsonData() when the data will be ready to use).
     *
     * @param data is a json text that will used inside this method.
     */
    void setupJsonData(String data);
}
