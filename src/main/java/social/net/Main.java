package social.net;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableEncryptableProperties
@PropertySource(name="EncryptedProperties", value = "classpath:encrypted.properties")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
