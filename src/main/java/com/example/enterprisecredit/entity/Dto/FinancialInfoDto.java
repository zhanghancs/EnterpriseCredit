package com.example.enterprisecredit.entity.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

public class FinancialInfoDto {
    public FinancialInfoDto() {
        this.totalRevenue = new ArrayList<Double>();
        this.totalCost = new ArrayList<Double>();
        this.cost = new ArrayList<Double>();
        this.expense = new ArrayList<Double>();
        this.profit = new ArrayList<Double>();
    }
    public FinancialInfoDto(List<Double> totalRevenue, List<Double> totalCost, List<Double> cost, List<Double> expense, List<Double> profit) {
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.cost = cost;
        this.expense = expense;
        this.profit = profit;
    }
    private List<Double> totalRevenue;

    private List<Double> totalCost;

    private List<Double> cost;

    private List<Double> expense;

    private List<Double> profit;
}
