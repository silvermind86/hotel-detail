package br.com.cvc.hotel.models.dtos;

import java.math.BigDecimal;

public class PriceDto {

    private BigDecimal adult;
    private BigDecimal child;

    public PriceDto() {
    }

    public PriceDto(Long adult, Long child) {
        this.adult = new BigDecimal(adult);
        this.child = new BigDecimal(child);
    }

    public BigDecimal getAdult() {
        return adult;
    }

    public void setAdult(BigDecimal adult) {
        this.adult = adult;
    }

    public BigDecimal getChild() {
        return child;
    }

    public void setChild(BigDecimal child) {
        this.child = child;
    }
}
