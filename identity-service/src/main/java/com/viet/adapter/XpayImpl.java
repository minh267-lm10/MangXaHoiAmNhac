package com.viet.adapter;

public class XpayImpl implements Xpay {
    private String creditCardNo;
    private String customerName;
    private String cardExpMonth;
    private String cardExpYear;
    private Short cardCVVNo;
    private Double amount;

    @Override
    public String getCreditCardNo() {
        // TODO Auto-generated method stub
        return creditCardNo;
    }

    @Override
    public String getCustomerName() {
        // TODO Auto-generated method stub
        return customerName;
    }

    @Override
    public String getCardExpMonth() {
        // TODO Auto-generated method stub
        return cardExpMonth;
    }

    @Override
    public String getCardExpYear() {
        // TODO Auto-generated method stub
        return cardExpYear;
    }

    @Override
    public Short getCardCVVNo() {
        // TODO Auto-generated method stub
        return cardCVVNo;
    }

    @Override
    public Double getAmount() {
        // TODO Auto-generated method stub
        return amount;
    }

    @Override
    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    @Override
    public void setCustomerName(String customerName) {
        // TODO Auto-generated method stub
        this.customerName = customerName;
    }

    @Override
    public void setCardExpMonth(String cardExpMonth) {
        // TODO Auto-generated method stub
        this.cardExpMonth = cardExpMonth;
    }

    @Override
    public void setCardExpYear(String cardExpYear) {
        // TODO Auto-generated method stub
        this.cardExpYear = cardExpYear;
    }

    @Override
    public void setCardCVVNo(Short cardCVVNo) {
        // TODO Auto-generated method stub
        this.cardCVVNo = cardCVVNo;
    }

    @Override
    public void setAmount(Double amount) {
        // TODO Auto-generated method stub
        this.amount = amount;
    }
}
