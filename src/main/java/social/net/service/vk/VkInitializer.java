package social.net.service.vk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.users.responses.SearchResponse;
import com.vk.api.sdk.streaming.clients.VkStreamingApiClient;
import org.springframework.beans.factory.annotation.Value;
import social.net.model.Pupil;
import social.net.repo.PupilRepo;

import java.util.List;

public class VkInitializer {
    private PupilRepo pupilRepo;

    @Value("${access.token}")
    private String token;
    TransportClient transportClient = new HttpTransportClient();

    VkApiClient vk = new VkApiClient(transportClient);
    VkStreamingApiClient streamingClient = new VkStreamingApiClient(transportClient);

    //Create service actor
    Integer appId = 7978813;
    UserActor actor = new UserActor(appId, token);

    public VkInitializer(PupilRepo pupilRepo) {
        this.pupilRepo = pupilRepo;
    }

    public SearchResponse getActor() throws ClientException, ApiException {
        SearchResponse execute = vk.users().search(actor).school(226069).count(1000).execute();
        return execute;
    }
    public boolean getDataOfUser(SearchResponse execute){
        List<UserFull> items = execute.getItems();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        if (!pupilRepo.findById((long) 123).isPresent()) {
            for (UserFull item : items) {
                Pupil pupil = gson.fromJson(item.toPrettyString(), Pupil.class);
                pupilRepo.save(pupil);
            }
            return true;
        }
        return false;
    }

}
/*access_token=44a9cbf4ad7f51d2473c44418669dce16e806d557b6de0827f0b093bcf3e47c94b5d3e4ada17da93db3a8&expires_in=0
&user_id=254530506*/


/*https://oauth.vk.com/blank.html#access_token=44a9cbf4ad7f51d2473c44418669dce16e806d557b6de0827f0b093bcf3e47c94b5d3e4ada17da93db3a8
&expires_in=0
&user_id=254530506*/
