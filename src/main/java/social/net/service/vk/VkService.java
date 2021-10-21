package social.net.service.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.users.responses.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.net.model.Pupil;
import social.net.repo.PupilRepo;

import java.util.List;
import java.util.Optional;

@Component
public class VkService {
    private final TransportClient transportClient = new HttpTransportClient();
    private final VkApiClient vk = new VkApiClient(transportClient);
    private final PupilRepo pupilRepo;
    private final VkInitializer vkInitializer;

    @Autowired
    public VkService(PupilRepo pupilRepo, PupilRepo pupilRepo1, VkInitializer vkInitializer) {
        this.pupilRepo = pupilRepo1;
        this.vkInitializer = vkInitializer;
    }

    /*public Pupil getPupilFromJson() {
        if (!isPupilPresent()) {

        }
    }*/

    private boolean isPupilPresent() throws ClientException, ApiException {
        List<UserFull> pupilFromSchool = getAllPupilsFromSchool();
        for (UserFull pupil : pupilFromSchool) {
            Optional<Pupil> byId = pupilRepo.findById(Long.valueOf(pupil.getId()));
            if (byId.isPresent()){
                return false;
            }
        }
        return true;
    }


    public List<UserFull> getAllPupilsFromSchool() throws ClientException, ApiException {
        SearchResponse execute = vk.users().search(vkInitializer.getActor())
                .school(vkInitializer.getSchool()).count(vkInitializer.getCount()).execute();
        return execute.getItems();
    }
}
