package com.practicaltask.subscriber;

import com.practicaltask.config.CommonResponse;
import com.practicaltask.subscriber.dto.SubscriberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/subscriber")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubscriberController {

    private SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse<SubscriberDTO> createSubscriber(@RequestBody SubscriberDTO subscriber,
                                                          HttpServletResponse httpServletResponse) {

        return subscriberService.createSubscriber(subscriber, true);
    }

    @PatchMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse<SubscriberDTO> updateSubscriber(@RequestBody SubscriberDTO subscriber,
                                                          HttpServletResponse httpServletResponse) {

        return subscriberService.createSubscriber(subscriber, false);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse<List<SubscriberDTO>> getAllSubscribers() {
        return subscriberService.getAllSubscribers();
    }

    @DeleteMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse deleteSubscriber(@RequestParam String id,
                                           HttpServletResponse httpServletResponse) {
        return subscriberService.deleteSubscriber(id);
    }

}
