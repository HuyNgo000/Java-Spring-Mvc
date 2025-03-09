package vn.huyngo.phoneshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public SANPHAM handleSaveProduct(SANPHAM sanpham) {
        return this.productRepository.save(sanpham);
    }

    public List<SANPHAM> fetchProducts() {
        return this.productRepository.findAll();
    }
}
