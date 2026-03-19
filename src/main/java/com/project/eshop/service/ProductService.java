package com.project.eshop.service;

import com.project.eshop.dto.ProductDTO;
import com.project.eshop.entity.Category;
import com.project.eshop.entity.Product;
import com.project.eshop.repositories.CategoryRepository;
import com.project.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO).orElse(null);
    }

    public List<ProductDTO> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
        if (category.isPresent()) {
            product.setCategory(category.get());
        } else {
            throw new RuntimeException("Category ID does not exist!");
        }

        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setStock(productDTO.getStock());

            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
            category.ifPresent(existingProduct::setCategory);

            Product updatedProduct = productRepository.save(existingProduct);
            return convertToDTO(updatedProduct);
        }
        return null;
    }
    
    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        return dto;
    }
}
