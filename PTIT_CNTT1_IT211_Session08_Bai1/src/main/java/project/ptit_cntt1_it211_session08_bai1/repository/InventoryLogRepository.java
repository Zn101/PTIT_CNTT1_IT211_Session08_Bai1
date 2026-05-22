package project.ptit_cntt1_it211_session08_bai1.repository;

import project.ptit_cntt1_it211_session08_bai1.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLogRepository
        extends JpaRepository<InventoryLog, Long> {
}