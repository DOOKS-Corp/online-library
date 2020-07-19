package com.example.onlinelibrary.beans;

import java.util.Objects;
import com.example.onlinelibrary.beans.RntCstAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RntCustomer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntCustomer   {
  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("address")
  @Valid
  private List<RntCstAddress> address = null;

  public RntCustomer lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")
  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public RntCustomer firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")
  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public RntCustomer phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "")
  
    public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public RntCustomer email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RntCustomer address(List<RntCstAddress> address) {
    this.address = address;
    return this;
  }

  public RntCustomer addAddressItem(RntCstAddress addressItem) {
    if (this.address == null) {
      this.address = new ArrayList<>();
    }
    this.address.add(addressItem);
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<RntCstAddress> getAddress() {
    return address;
  }

  public void setAddress(List<RntCstAddress> address) {
    this.address = address;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntCustomer rntCustomer = (RntCustomer) o;
    return Objects.equals(this.lastName, rntCustomer.lastName) &&
        Objects.equals(this.firstName, rntCustomer.firstName) &&
        Objects.equals(this.phoneNumber, rntCustomer.phoneNumber) &&
        Objects.equals(this.email, rntCustomer.email) &&
        Objects.equals(this.address, rntCustomer.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastName, firstName, phoneNumber, email, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntCustomer {\n");
    
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
