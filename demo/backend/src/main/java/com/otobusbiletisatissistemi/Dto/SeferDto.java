package com.otobusbiletisatissistemi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SeferDto {
    private Long firmaId;
    private Long otobusId;
    private String seferKalkisYeri;
    private String seferVarisYeri;
    private LocalDateTime seferKalkisSaati;
    private LocalDateTime seferVarisSaati;

    public SeferDto() {
    }
}
