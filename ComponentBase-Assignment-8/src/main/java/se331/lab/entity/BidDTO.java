package se331.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDTO {
    private Long id;
    private Double amount;
    private LocalDateTime datetime;
    // ตัดฟิลด์ item ออก เพื่อป้องกันการวนลูปกลับไปหา Item ซ้ำซ้อน
}