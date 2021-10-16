package social.net.service.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.stereotype.Component;

import static com.sun.javafx.scene.control.skin.FXVK.vk;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.REDIRECT_URI;

@Component
public class VkInitializer {

    TransportClient transportClient=new HttpTransportClient();
    VkApiClient vk=new VkApiClient(transportClient);
}
