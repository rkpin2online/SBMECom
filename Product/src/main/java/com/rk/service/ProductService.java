package com.rk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.dto.Product;
import com.rk.exception.CurrencyNotValidException;
import com.rk.exception.OfferNotValidateException;
import com.rk.repo.ProductRepository;
import com.rk.service.config.ProductConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author RK
 *
 */

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductConfiguration productConfiguration;

	public String addProduct(Product product) {

		log.info("Adding Product");
		
		if(product.getPrice()==0 && product.getDiscount()>0) {
			
			throw new OfferNotValidateException("No discount is allowed at 0 product price");
		}
//		List<String> validCurrencys = new ArrayList<>();
//		validCurrencys.add("INR");
//		validCurrencys.add("USD");
//		validCurrencys.add("ERU");
//		validCurrencys.add("YAN");
		
		if(!productConfiguration.getCurrencies().contains(product.getCurrency().toUpperCase())) {
			throw new CurrencyNotValidException("Invalid Currency. Validate Currencies -" +productConfiguration.getCurrencies());
		}
		productRepo.save(product);

		return "Success";
	}

	public List<Product> listAllProducts() {
		return productRepo.findAll();
	}

	public List<Product> productCategoryList(String category) {

		return productRepo.findByCategory(category);

	}

	public Product productById(String id) {
		return productRepo.findById(id).get();
	}

	public String updateProduct(Product product) {

		productRepo.save(product);

		return "Product Updated...";
	}


	public String deleteProductById(String id) {
		productRepo.deleteById(id);
		return "Product Deletion Failed";
	}


}
