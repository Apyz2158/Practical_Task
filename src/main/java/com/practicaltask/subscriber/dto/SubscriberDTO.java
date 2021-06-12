package com.practicaltask.subscriber.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriberDTO {

    private Long id;
    private String name;
    private String gender;
    private String email;
    private String mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipcode;

}
