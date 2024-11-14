package com.viet.adapter;

public interface Xpay {

    public String getCreditCardNo(); // lấy só thẻ tín dụng

    public String getCustomerName(); // lấy tên khách hàng

    public String getCardExpMonth(); // lấy tháng hết hạn thẻ

    public String getCardExpYear(); // lấy năm hết hạn thẻ

    public Short getCardCVVNo(); // lấy số CVV của thẻ

    public Double getAmount(); // lấy số tiền

    public void setCreditCardNo(String creditCardNo);

    public void setCustomerName(String customerName);

    public void setCardExpMonth(String cardExpMonth);

    public void setCardExpYear(String cardExpYear);

    public void setCardCVVNo(Short cardCVVNo);

    public void setAmount(Double amount);
}
