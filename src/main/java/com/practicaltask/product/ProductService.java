package com.practicaltask.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicaltask.config.CommonResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CommonResponse<Product> saveProductList() throws URISyntaxException, IOException, InterruptedException {
        String jsonString = this.getProductData();
        JSONObject object = new JSONObject(jsonString);
        JSONObject data = object.getJSONObject("data");
        JSONObject products = data.getJSONObject("products");
        JSONArray jsonArray = products.getJSONArray("edges");
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = new ArrayList<>();
        for (Object obj : jsonArray) {
            Product product = mapper.readValue(obj.toString(), Product.class);
            productList.add(product);
        }

        List<Product> savedProducts = productRepository.saveAll(productList);
        CommonResponse response = new CommonResponse(savedProducts);
        return response;
    }

    private String getProductData() throws URISyntaxException, IOException, InterruptedException {
        String json =  "{\"query\":\"query {\\r\\n products(first: 100) {\\r\\n      edges {\\r\\n        node {\\r\\n          id\\r\\n          title\\r\\n          handle\\r\\n          tags\\r\\n          \\r\\n          vendor\\r\\n          options{\\r\\n              id\\r\\n          }\\r\\n\\r\\n        }\\r\\n      }\\r\\n }\\r\\n}\",\"variables\":{}}";
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(new URI("https://securecod4.myshopify.com/admin/api/2020-07/graphql.json"))
                .header("X-Shopify-Access-Token", "a87522cc2e2551e43549aceb52e5d141")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
