package org.dto.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscription {
    private String bankCard;
    private LocalDate startDate;
}
