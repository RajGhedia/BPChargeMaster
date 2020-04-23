package OtherTests.com.APICW;

import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.io.PrintWriter;

public class podapi {

    public static void main(String[] args) throws InterruptedException, IOException {


        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"searchParameters\":\n\n{         \"minBedrooms\": 5,         \"maxPrice\": 900000,         \"radius\": 0.0,         \"propertyTypes\": [ \"detached\",  \"semi-detached\" ]   }\n}");
        Request request = new Request.Builder()
                .url("http://h1-qsse01.rightmove.com:8080/suggestions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "c6ba7846-1075-4492-8dd4-1bf8123e538c")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(request);
        System.out.println(response);

/*
        JsonObject json = new JsonObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        json.put("minBedrooms",5);
        json.put("propertyTypes", "detached");
        // finally output the json string
        out.print(json.toString());
*/

    }
}