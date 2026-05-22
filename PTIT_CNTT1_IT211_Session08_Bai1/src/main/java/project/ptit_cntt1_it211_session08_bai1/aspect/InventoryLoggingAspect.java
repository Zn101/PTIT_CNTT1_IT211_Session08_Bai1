package project.ptit_cntt1_it211_session08_bai1.aspect;

import project.ptit_cntt1_it211_session08_bai1.entity.InventoryLog;
import project.ptit_cntt1_it211_session08_bai1.repository.InventoryLogRepository;
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
            execution(* project.ptit_cntt1_it211_session08_bai1.service.*.stockIn(..))
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
            execution(* project.ptit_cntt1_it211_session08_bai1.*.stockOut(..))
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