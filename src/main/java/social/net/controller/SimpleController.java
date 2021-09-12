package social.net.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/message")
    public String getMessageHello(){
        return "helloыфаывфа";
    }
}