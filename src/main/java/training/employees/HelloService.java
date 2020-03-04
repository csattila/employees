package training.employees;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

//    private Environment environment;

//    public HelloService(Environment environment){
//        this.environment = environment;
//    }

    public HelloService(@Value("${employees.hello}") String hello){
        this.hello = hello;
    }

//    @Value("${employees.hello}")
    private String hello;

    public String sayHello(){
//        String hello = environment.getProperty("employees.hello");
        return hello + " " + LocalDateTime.now();
    }
}
