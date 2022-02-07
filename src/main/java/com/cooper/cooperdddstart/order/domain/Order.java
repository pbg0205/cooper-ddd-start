package com.cooper.cooperdddstart.order.domain;

import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(of = "orderNumber")
public class Order {

    private OrderState orderState;
    private ShippingInfo shippingInfo;

    private List<OrderLine> orderLines;
    private int totalAmounts;

    private String orderNumber;

    public Order(OrderState orderState, ShippingInfo shippingInfo, List<OrderLine> orderLines) {
        this.orderState = orderState;
        this.shippingInfo = shippingInfo;
        setOrderLinesAndTotalAmounts(orderLines);
    }

    private void setOrderLinesAndTotalAmounts(List<OrderLine> orderLines) {
        setOrderLines(orderLines);
        setTotalAmounts(orderLines);
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
    }

    private void verifyNotYetShipped() {
        if (orderState.isShipped()) {
            throw new IllegalArgumentException("already shipped");
        }
    }

    private void setShippingInfo(ShippingInfo newShippingInfo) {
        if (!orderState.isShippingChangeable()) {
            throw new IllegalArgumentException("배송 상태가 " + orderState + "변경할 수 없습니다.");
        }
        this.shippingInfo = newShippingInfo;
    }

    public void changeShipped() {
        this.orderState = OrderState.SHIPPED;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null) {
            this.orderLines = Collections.emptyList();
        }
        this.orderLines = orderLines;
    }

    private void setTotalAmounts(List<OrderLine> orderLines) {
        this.totalAmounts = orderLines.stream().mapToInt(OrderLine::getAmounts).sum();
    }
}
