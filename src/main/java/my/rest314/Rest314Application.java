package my.rest314;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Rest314Application {

    static  String URL_EMPLOYEES = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) {
        SpringApplication.run(Rest314Application.class, args);
        String cookies ="";

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));


        HttpEntity<String> entityS = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entityS, String.class);
        cookies = response.getHeaders().get("Set-Cookie").get(0);
        System.out.println(cookies);
        headers.set("Cookie", cookies);

        User user = new User((long) 3, "James", "Brown", (byte) 53);
        HttpEntity<User> entityU = new HttpEntity<>(user, headers);// build the request
        response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.POST, entityU, String.class);
        String result1 = response.getBody();
        System.out.println(result1);


        user = new User((long) 3, "Thomas", "Shelby", (byte) 53);
        entityU = new HttpEntity<>(user, headers);
        response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.PUT, entityU, String.class);
        String result2 = response.getBody();
        System.out.println(result2);

        response = restTemplate.exchange(URL_EMPLOYEES + "/3", HttpMethod.DELETE, entityU, String.class);
        String result3 = response.getBody();
        System.out.println(result1 + result2 + result3);

    }
}
