package com.example.Last.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LastTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastTaskApplication.class, args);
		String url = "http://94.198.50.185:7081/api/users";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
		String cookie = forEntity.getHeaders().getFirst("Set-Cookie").split(";")[0];
		System.out.println(cookie);
		User user3 = new User(3L, "James", "Brown", (byte) 25);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Cookie", cookie);
		HttpEntity<User> userHttpEntity = new HttpEntity<>(user3, headers);
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, userHttpEntity, String.class);
		System.out.println(exchange.getHeaders());
		System.out.println(exchange.getStatusCode());
		System.out.println(exchange.getBody());
		user3.setName("Thomas");
		user3.setLastName("Shelby");
		HttpEntity<User> userUpdate = new HttpEntity<>(user3, headers);
		ResponseEntity<String> exchange1 = restTemplate.exchange(url, HttpMethod.PUT, userUpdate, String.class);
		System.out.println(exchange1.getHeaders());
		System.out.println(exchange1.getStatusCode());
		System.out.println(exchange1.getBody());
		ResponseEntity<String> exchange2 = restTemplate.exchange(url + "/" + user3.getId(), HttpMethod.DELETE, userUpdate, String.class);
		System.out.println(exchange2.getHeaders());
		System.out.println(exchange2.getStatusCode());
		System.out.println(exchange2.getBody());
	}
}
