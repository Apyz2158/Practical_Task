package com.practicaltask.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    @Id
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String currency;
    private Boolean accepts_marketing;
    private String tags;
    private String state;
    private Integer orders_count;
    private boolean verified_email;
    private double total_spent;
    private boolean tax_exempt;


}
