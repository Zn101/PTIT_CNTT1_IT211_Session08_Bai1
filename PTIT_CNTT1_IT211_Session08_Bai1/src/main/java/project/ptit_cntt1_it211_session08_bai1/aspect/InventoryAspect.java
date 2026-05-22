package project.ptit_cntt1_it211_session08_bai1.aspect;

import com.example.inventory.entity.InventoryLog;
import com.example.inventory.repository.InventoryLogRepository;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class InventoryLoggingAspect {

    private final InventoryLogRepository inventoryLogRepository;

    public InventoryLoggingAspect(
            InventoryLogRepository inventoryLogRepository
    ) {
        this.inventoryLogRepository = inventoryLogRepository;
    }

    @AfterReturning("""
            execution(* com.example.inventory.service.*.stockIn(..))
            && args(sku, quantity, username, role)
            """)
    public void logStockIn(
            String sku,
            Integer quantity,
            String username,
            String role
    ) {

        InventoryLog log = new InventoryLog(
                LocalDateTime.now(),
                username,
                "STOCK_IN",
                "Quantity changed: " + quantity
        );

        inventoryLogRepository.save(log);
    }

    @AfterReturning("""
            execution(* com.example.inventory.service.*.stockOut(..))
            && args(sku, quantity, username, role)
            """)
    public void logStockOut(
            String sku,
            Integer quantity,
            String username,
            String role
    ) {

        InventoryLog log = new InventoryLog(
                LocalDateTime.now(),
                username,
                "STOCK_OUT",
                "Quantity changed: " + quantity
        );

        inventoryLogRepository.save(log);
    }
}