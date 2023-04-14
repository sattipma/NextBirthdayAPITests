import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://lx8ssktxx9.execute-api.eu-west-1.amazonaws.com";
        RestAssured.basePath = "/Prod/next-birthday";
    }

    public Response getNextBirthdayResponse(String dateOfBirth, String unit) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("dateofbirth", dateOfBirth);
        queryParams.put("unit", unit);

        return RestAssured.given()
                .queryParams(queryParams)
                .get();
    }

    public int getStatusCode(Response response) {
        return response.statusCode();
    }

    public String getMessage(Response response) {
        return response.jsonPath().getString("message");
    }
}
