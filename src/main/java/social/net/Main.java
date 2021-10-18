package social.net;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import social.net.service.vk.VkInitializer;

@SpringBootApplication
@EnableAsync
@EnableEncryptableProperties
@PropertySource(name="EncryptedProperties", value = "classpath:encrypted.properties")
public class Main {
    public static void main(String[] args) {
        VkInitializer vkInitializer = new VkInitializer();
        vkInitializer.getActor();

        SpringApplication.run(Main.class,args);
    }
}
