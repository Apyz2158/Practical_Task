package com.practicaltask.subscriber.converter;

import com.practicaltask.subscriber.Subscriber;
import com.practicaltask.subscriber.dto.SubscriberDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriberConverter {

    public SubscriberDTO convert(Subscriber subscriber) {
        SubscriberDTO dto = new SubscriberDTO();
        dto.setId(subscriber.getId());
        dto.setName(subscriber.getName());
        dto.setEmail(subscriber.getEmail());
        dto.setMobile(subscriber.getMobile());
        dto.setGender(subscriber.getGender());
        dto.setAddressLine1(subscriber.getAddressLine1());
        if (subscriber.getAddressLine2() != null) {
            dto.setAddressLine2(subscriber.getAddressLine2());
        }
        dto.setCity(subscriber.getCity());
        dto.setState(subscriber.getState());
        dto.setCountry(subscriber.getCountry());
        dto.setZipcode(subscriber.getZipcode());
        return dto;
    }

    public List<SubscriberDTO> convertList(List<Subscriber> subscriberList) {
        List<SubscriberDTO> dtoList = new ArrayList<>();
        if (subscriberList != null) {
            for (Subscriber subscriber : subscriberList) {
                SubscriberDTO dto = this.convert(subscriber);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}
