package com.example.basketService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.basketService.model.Basket;
import com.example.basketService.repository.BasketRepository;

@SpringBootApplication
public class BasketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner LoadData(BasketRepository basketRepository) {
		return args -> {
			Basket basket = new Basket();
			basket.setProductCode("A-1");
			basket.setQuantity(10);

			boolean exist2 = basketRepository.findByProductCode("A-1").isPresent();
			if (exist2 == false) {
				basketRepository.save(basket);
			}
			

			Basket basket1 = new Basket();
			basket1.setProductCode("B-2");
			basket1.setQuantity(0);

			boolean exist = basketRepository.findByProductCode("B-2").isPresent();
			if (exist == false) {
				basketRepository.save(basket1);
			}
			
		};
	}

}
