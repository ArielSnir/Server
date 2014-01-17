package com.yummynoodlebar.events.orders;

import com.yummynoodlebar.events.UpdateEvent;

import java.util.UUID;

public class SetTeamPaymentEvent extends UpdateEvent {

  private UUID key;
  private PaymentDetails paymentDetails;

  public SetTeamPaymentEvent(UUID key, PaymentDetails paymentDetails) {
    this.key = key;
    this.paymentDetails = paymentDetails;
  }

  public UUID getKey() {
    return key;
  }

  public PaymentDetails getPaymentDetails() {
    return paymentDetails;
  }
}
