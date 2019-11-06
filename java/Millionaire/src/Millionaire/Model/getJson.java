package Millionaire.Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import 3 party library
import org.json.JSONArray;

//get
public abstract interface getJson {
    // getJsonData() will get json text from get.php page(backend) url: is the url to the page that show json text.
    default void getJsonText(String url){
        // create httpClient object.
        HttpClient client = HttpClient.newHttpClient();
        // create httpRequest object.
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(this::getJsonArray).join();
    }
    // toStringArray will convert Json array to string.
    default String[] toStringArray(JSONArray jsonArray){
        String [] stringArray = new String[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++){
            stringArray[i]=jsonArray.getString(i);
        }
        return stringArray;
    }
    //getJsonArray() will be called inside getJsonData() it will have behavior to handle json (data) text
    boolean getJsonArray(String data);
}
