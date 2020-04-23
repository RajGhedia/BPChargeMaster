/*
package OtherTests.com.APICW;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static sun.management.snmp.util.JvmContextFactory.getUserData;

public class FormLoads {


    private static OkHttpClient client = new OkHttpClient();
    public static void main(String[] args)


    {
        for(String str : getUserData(""))

            System.out.println(str);

    }

 public static String getJson(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
            return response.body().string();

    }

    public static String [] getUserData (String username);

  //  Gson gson = new Gson();

}
*/