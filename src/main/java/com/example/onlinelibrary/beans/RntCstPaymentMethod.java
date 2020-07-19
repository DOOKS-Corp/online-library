package com.example.onlinelibrary.beans;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RntCstPaymentMethod
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntCstPaymentMethod   {
  @JsonProperty("paymentSys")
  private String paymentSys = null;

  @JsonProperty("cardNumber")
  private String cardNumber = null;

  public RntCstPaymentMethod paymentSys(String paymentSys) {
    this.paymentSys = paymentSys;
    return this;
  }

  /**
   * Get paymentSys
   * @return paymentSys
  **/
  @ApiModelProperty(value = "")
  
    public String getPaymentSys() {
    return paymentSys;
  }

  public void setPaymentSys(String paymentSys) {
    this.paymentSys = paymentSys;
  }

  public RntCstPaymentMethod cardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  /**
   * 16 digits for debit/credit card
   * @return cardNumber
  **/
  @ApiModelProperty(value = "16 digits for debit/credit card")
  
    public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntCstPaymentMethod rntCstPaymentMethod = (RntCstPaymentMethod) o;
    return Objects.equals(this.paymentSys, rntCstPaymentMethod.paymentSys) &&
        Objects.equals(this.cardNumber, rntCstPaymentMethod.cardNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentSys, cardNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntCstPaymentMethod {\n");
    
    sb.append("    paymentSys: ").append(toIndentedString(paymentSys)).append("\n");
    sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
