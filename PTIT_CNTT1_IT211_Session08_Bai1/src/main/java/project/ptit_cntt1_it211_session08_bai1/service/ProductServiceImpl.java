package project.ptit_cntt1_it211_session08_bai1.service;

import project.ptit_cntt1_it211_session08_bai1.entity.Product;
import project.ptit_cntt1_it211_session08_bai1.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void stockIn(
            String sku,
            Integer quantity,
            String username,
            String role
    ) {

        int updated = productRepository.stockIn(sku, quantity);

        if (updated == 0) {
            throw new RuntimeException("Không tìm thấy SKU");
        }
    }

    @Transactional
    @Override
    public void stockOut(
            String sku,
            Integer quantity,
            String username,
            String role
    ) {

        int updated = productRepository.stockOut(sku, quantity);

        if (updated == 0) {
            throw new RuntimeException(
                    "Xuất kho thất bại hoặc không đủ hàng"
            );
        }
    }

    @Override
    public void deleteProduct(
            Long id,
            String username,
            String role
    ) {

        productRepository.deleteById(id);
    }

    @Override
    public String inspectInventory(
            String username,
            String role
    ) {

        List<Product> products = productRepository.findAll();

        int totalQuantity = products.stream()
                .mapToInt(Product::getQuantity)
                .sum();

        double totalValue = products.stream()
                .mapToDouble(
                        p -> p.getPrice() * p.getQuantity()
                )
                .sum();

        return "Total Quantity = " +
                totalQuantity +
                ", Total Value = " +
                totalValue;
    }
}
