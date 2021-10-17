package social.net.service.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.stereotype.Component;

@Component
public class VkInitializer {

    TransportClient transportClient=new HttpTransportClient();
    VkApiClient vk=new VkApiClient(transportClient);
}
