package project.ptit_cntt1_it211_session08_bai1.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InventoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private String username;

    private String action;

    @Column(length = 1000)
    private String detail;

    public InventoryLog() {
    }

    public InventoryLog(LocalDateTime timestamp,
                        String username,
                        String action,
                        String detail) {

        this.timestamp = timestamp;
        this.username = username;
        this.action = action;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public String getDetail() {
        return detail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
