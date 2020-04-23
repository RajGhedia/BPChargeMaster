package OtherTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIGet

    {
        public static void main(String[] args)

        {
            Response resp=RestAssured.get("http://h1-qsse01.rightmove.com:8080/suggestions");
            resp.getStatusCode();
            int code=resp.getStatusCode();
            System.out.println("Status code is " +code);
            String data=resp.asString();
            System.out.println("Date is " +data);
            System.out.println("Response time " + resp.getTime());
        }
    }



