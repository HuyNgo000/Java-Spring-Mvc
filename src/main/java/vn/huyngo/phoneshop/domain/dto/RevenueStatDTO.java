package vn.huyngo.phoneshop.domain.dto;

public class RevenueStatDTO {
    private String time;
    private Double revenue;

    public RevenueStatDTO(Object time, Double revenue) {
        this.time = String.valueOf(time);
        this.revenue = revenue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
