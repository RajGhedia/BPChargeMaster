package OtherTests.com.APICW.Json;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class GetCallPublic {

    public static void main(String[] args) throws IOException{

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("http://www.rightmoveplaces.co.uk/api/_sidebar?locationIdentifier=REGION%5E77165&radius=0.0&channel=BUY")
            .get()
            .addHeader("cache-control", "no-cache")
            .addHeader("Postman-Token", "146a0b4b-b6f0-4dc5-bd34-f0ef499b15ff")
            .build();
    Response response;

        response = client.newCall(request).execute();

        System.out.println(request);
        System.out.println(response);
}

}
