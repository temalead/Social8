package social.net.service.vk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.users.responses.SearchResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.net.model.Pupil;
import social.net.repo.PupilRepo;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class VkService {
    private final TransportClient transportClient = new HttpTransportClient();
    private final VkApiClient vk = new VkApiClient(transportClient);
    private final PupilRepo pupilRepo;
    private final VkInitializer vkInitializer;
    private List<UserFull> pupils;
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(VkService.class);


    @PostConstruct
    public void init() throws ClientException, ApiException {
        getPupilFromJson();
    }

    @Autowired
    public VkService(PupilRepo pupilRepo, PupilRepo pupilRepo1, VkInitializer vkInitializer) {
        this.pupilRepo = pupilRepo1;
        this.vkInitializer = vkInitializer;
    }

    public void getPupilFromJson() throws ClientException, ApiException {
        if (!isNotPupilPresent()){
            List<UserFull> pupils = getAllPupilsFromSchool();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            for (UserFull pupil : pupils) {
                Pupil pupilToDb = gson.fromJson(String.valueOf(pupil), Pupil.class);
                logger.info("Id pupil is "+pupilToDb.getId()+"\nAnd his name is "+pupilToDb.getFirstName());
                pupilRepo.save(pupilToDb);
            }
        }
    }

    private boolean isNotPupilPresent() throws ClientException, ApiException {
        List<UserFull> pupilFromSchool = getAllPupilsFromSchool();
        for (UserFull pupil : pupilFromSchool) {
            Optional<Pupil> pupilFromDb = pupilRepo.findById(Long.valueOf(pupil.getId()));
            if (pupilFromDb.isPresent()){
                return false;
            }
        }
        return true;
    }


    public List<UserFull> getAllPupilsFromSchool() throws ClientException, ApiException {
        if (Optional.ofNullable(pupils).isPresent()){
            return pupils;
        }
        else{
            SearchResponse execute = vk.users().search(vkInitializer.getActor())
                    .school(vkInitializer.getSchool()).count(vkInitializer.getCount()).execute();
            pupils=execute.getItems();
        }
        return pupils;
    }
}
