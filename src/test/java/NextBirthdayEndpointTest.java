import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NextBirthdayEndpointTest extends BaseTest {
    @Test
    public void testStatusCodeOk() {
        Response response = getNextBirthdayResponse("1990-10-30", "hour");
        Assert.assertEquals(getStatusCode(response), 200, "Wrong status code for valid params");
    }

    @Test(dataProvider = "BirthdayPositiveTestData", dataProviderClass = TestUtilities.class)
    public void testTimeLeftUntilBirthday(String dateOfBirth, String unit, String test) {
        Response response = getNextBirthdayResponse(dateOfBirth, unit);
        Assert.assertEquals(getMessage(response), TestUtilities.timeLeftUntilNextBirthday(dateOfBirth, unit), "Response mismatch when birthday is " + test + ". Params " + dateOfBirth + " and " + unit);
    }

    @Test
    public void testStatusCodeForInvalidDOB() {
        Response response = getNextBirthdayResponse("10-1999-30", "hour");
        Assert.assertEquals(getStatusCode(response), 400, "Wrong status code for invalid date of birth");
    }

    @Test
    public void testStatusCodeForInvalidUnit() {
        Response response = getNextBirthdayResponse("1999-10-30", "abcd");
        Assert.assertEquals(getStatusCode(response), 400, "Wrong status code for invalid params");
    }

    @Test
    public void testMessageForInvalidDOB() {
        Response response = getNextBirthdayResponse("10-1999-30", "day");
        Assert.assertEquals(getMessage(response), "Please specify dateofbirth in ISO format YYYY-MM-DD", "Wrong error message for invalid DOB");
    }

    @Test
    public void testMessageForInvalidUnit() {
        Response response = getNextBirthdayResponse("1999-10-30", "abcd");
        Assert.assertEquals(getMessage(response), "Please specify valid unit hour/day/week/month", "Wrong error message for invalid unit");
    }

}
