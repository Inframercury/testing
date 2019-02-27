package selenium;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.core.IsEqual.equalTo;


public class PeopleTest {


    @Test
    public void test_add_person() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person("Ivan","Durakov");
        String p = objectMapper.writeValueAsString(person);
        System.out.println(p);
        with().body(p).when().request("POST","http://localhost:8080/people/").then().statusCode(201);
    }

    @Test
    public void test_check_names(){
        post("http://localhost:8080/people/search/findByLastName?name=Durakov").then().statusCode(200).assertThat().body("_embedded.people[0].firstName",equalTo("Ivan"));
    }
}
