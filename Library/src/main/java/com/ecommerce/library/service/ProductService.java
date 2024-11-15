package com.ecommerce.library.service;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<ProductDto> allProduct();
    Product save(MultipartFile imageProduct, ProductDto productDto);
    Product update(MultipartFile imageProduct, ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);

    ProductDto getById(Long id);

    Page<ProductDto> getAllProducts(int pageNo);

    Page<ProductDto> searchProducts(int pageNo, String keyword);
    List<ProductDto> searchProductList(String keyword);

    List<ProductDto> products();

    List<ProductDto> randomProduct();

    List<ProductDto> listViewProducts();

    List<ProductDto> findAllByCategory(String category);

    List<ProductDto> filterHighProducts();

    List<ProductDto> filterLowProducts();

    List<ProductDto> findByCategoryId(Long id);
}
