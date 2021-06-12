package com.practicaltask.product;

import com.practicaltask.config.CommonResponse;
import com.practicaltask.config.ResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public CommonResponse saveAndGetProducts() throws URISyntaxException, IOException, InterruptedException {
        return ResponseUtilities.createSuccessResponse(productService.saveProductList());
    }

}
