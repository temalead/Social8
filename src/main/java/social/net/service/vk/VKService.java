package social.net.service.vk;

import org.springframework.stereotype.Service;

@Service
public class VKService {
    private VkInitializer vk;

    public VKService(VkInitializer vk) {
        this.vk = vk;
    }
}
