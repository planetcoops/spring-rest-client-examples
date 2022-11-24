package guru.springframework.springrestclientexamples.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String json = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"gender\": \"female\",\n" +
            "      \"name\": {\n" +
            "        \"title\": \"Ms.\",\n" +
            "        \"first\": \"Molly\",\n" +
            "        \"last\": \"Robel\"\n" +
            "      },\n" +
            "      \"location\": {\n" +
            "        \"street\": \"24674 Cyrus Key Apt. 291\",\n" +
            "        \"city\": \"Emilieberg\",\n" +
            "        \"state\": \"Texas\",\n" +
            "        \"postcode\": \"47890-3822\"\n" +
            "      },\n" +
            "      \"email\": \"tremblay.loy@streich.com\",\n" +
            "      \"login\": {\n" +
            "        \"username\": \"devonte67\",\n" +
            "        \"password\": \"><'}$pwwuv\",\n" +
            "        \"md5\": \"a9b1643ad87da1cf6598a27c5bbd3ae1\",\n" +
            "        \"sha1\": \"42f2cc46f189cb35c600d4a788a050d0ee27d623\",\n" +
            "        \"sha256\": \"9f5a440f116de4b9e54ac378ba96b98f126cf0a3ec6c76d0e01cfefd8b75fcab\"\n" +
            "      },\n" +
            "      \"phone\": \"866-864-1372\",\n" +
            "      \"job\": {\n" +
            "        \"title\": \"Gas Processing Plant Operator\",\n" +
            "        \"company\": \"Keeling, Halvorson and Mayert\"\n" +
            "      },\n" +
            "      \"billing\": {\n" +
            "        \"card\": {\n" +
            "          \"type\": \"MasterCard\",\n" +
            "          \"number\": \"4539661724756466\",\n" +
            "          \"expiration_date\": {\n" +
            "            \"date\": \"2016-09-29 01:39:53.000000\",\n" +
            "            \"timezone_type\": 3,\n" +
            "            \"timezone\": \"UTC\"\n" +
            "          },\n" +
            "          \"iban\": \"NA55633746255612759286461167\",\n" +
            "          \"swift\": \"RGBHHSXZ\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"language\": \"yi\",\n" +
            "      \"currency\": \"ZAR\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    public List<User> getUsers(Integer limit) {
        //UserData userData = restTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        UserData userData;
        try {
            userData = mapper.readValue(json, UserData.class);
            return userData.getData();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Flux<User> getUsers(Mono<Integer> limit) throws ExecutionException, InterruptedException {
        return Flux.just(getUsers(limit.toFuture().get()).toArray(new User[0]));
    }
}
