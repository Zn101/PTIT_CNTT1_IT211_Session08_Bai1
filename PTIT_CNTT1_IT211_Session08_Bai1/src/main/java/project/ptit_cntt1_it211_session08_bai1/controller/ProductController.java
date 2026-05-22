package project.ptit_cntt1_it211_session08_bai1.controller;

import project.ptit_cntt1_it211_session08_bai1.dto.StockRequest;
import project.ptit_cntt1_it211_session08_bai1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @PostMapping("/stock-in")
    public String stockIn(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequest request
    ) {

        productService.stockIn(
                request.getSku(),
                request.getQuantity(),
                username,
                role
        );

        return "Nhập kho thành công";
    }

    @PostMapping("/stock-out")
    public String stockOut(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequest request
    ) {

        productService.stockOut(
                request.getSku(),
                request.getQuantity(),
                username,
                role
        );

        return "Xuất kho thành công";
    }

    @GetMapping("/inspect")
    public String inspect(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        return productService.inspectInventory(
                username,
                role
        );
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        productService.deleteProduct(
                id,
                username,
                role
        );

        return "Xóa thành công";
    }
}
