package social.net.service.vk;

import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import social.net.repo.PupilRepo;

import javax.annotation.PostConstruct;

@Component
public class VkInitializer {
    @Value("${access.token}")
    private String token;
    private Integer appId = 7978813;
    private UserActor actor;

    @PostConstruct
    public void init(){
        actor=new UserActor(appId,token);
    }
    public  int getSchool() {
        return 226069;
    }

    public  int getCount() {
        return 1000;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public UserActor getActor() {
        return actor;
    }

    public void setActor(UserActor actor) {
        this.actor = actor;
    }
}
