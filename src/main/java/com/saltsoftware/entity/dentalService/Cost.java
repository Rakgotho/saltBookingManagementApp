package com.saltsoftware.entity.dentalService;
/* @Author: Junade Frizlar
 *   StudentNumber : 208046402
 *   This is a cost entity class.
 * */


import com.saltsoftware.entity.payment.PatientPaymentType;

import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class Cost {

    //required parameters
    @Id
    private String costID;
    private double amount;

    //Default Constructor
    protected Cost(){}

    //getters
    public String getCostID() {
        return costID;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "costID='" + costID + '\'' +
                ", amount=" + amount +
                '}';
    }
    //constructor
    private Cost(Cost.Builder builder) {
        this.costID = builder.costID;
        this.amount = builder.amount;


    }


    public static class Builder{

        public String costID;
        public double amount;

        public Builder setCostID(String costID) {
            this.costID = costID;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder copy(Cost cost) {
            this.costID = cost.costID;
            this.amount = cost.amount;

            return this;
        }

        public Cost build() {
            return new Cost(this);
        }
    }


    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), costID);
    }
}
