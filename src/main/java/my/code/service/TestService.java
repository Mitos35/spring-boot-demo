package my.code.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Value("${app.services.test.messages}")
    private String testValue;

    public String getHelloMessage() {
        return testValue;
    }
}
