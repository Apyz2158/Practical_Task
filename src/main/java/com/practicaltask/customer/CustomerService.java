package com.practicaltask.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicaltask.config.CommonResponse;
import com.practicaltask.config.ResponseUtilities;
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
public class CustomerService {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CommonResponse<Customer> saveAllCustomers() throws URISyntaxException, IOException, InterruptedException {
        String jsonString = this.getAllDataOfCustomers();
        JSONObject object = new JSONObject(jsonString);
        JSONArray jsonArray = object.getJSONArray("customers");
        ObjectMapper mapper = new ObjectMapper();
        List<Customer> customerList = new ArrayList<>();
        for (Object obj : jsonArray) {
            Customer customer = mapper.readValue(obj.toString(), Customer.class);
            customerList.add(customer);
        }
        List<Customer> customers = customerRepository.saveAll(customerList);
        CommonResponse response = new CommonResponse(customers);
        return response;
    }

    private String getAllDataOfCustomers() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://securecod4.myshopify.com/admin/api/2020-01/customers.json"))
                .header("Authorization", "Basic ZTM1MTljZThkMmI3Mjc1MDIxMDgwMGYzYmE3MTE1ZjI6YTg3NTIyY2MyZTI1NTFlNDM1NDlhY2ViNTJlNWQxNDE=")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}
