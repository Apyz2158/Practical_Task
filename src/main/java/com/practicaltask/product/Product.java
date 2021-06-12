package com.practicaltask.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    @Id
    private Long id;
    private String title;
    private String tags;
    private String template_suffix;
    private String published_scope;
    private String product_type;
    private String handle;
    private String body_html;
    private String vendor;
//    private String options;

}
