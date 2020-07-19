package com.example.onlinelibrary.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RntCstAddress
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntCstAddress   {
  @JsonProperty("line1")
  private String line1 = null;

  @JsonProperty("line2")
  private String line2 = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("zipCode")
  private String zipCode = null;

  public RntCstAddress line1(String line1) {
    this.line1 = line1;
    return this;
  }

  /**
   * address line 1
   * @return line1
  **/
  @ApiModelProperty(value = "address line 1")
  
    public String getLine1() {
    return line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public RntCstAddress line2(String line2) {
    this.line2 = line2;
    return this;
  }

  /**
   * address line 2
   * @return line2
  **/
  @ApiModelProperty(value = "address line 2")
  
    public String getLine2() {
    return line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public RntCstAddress city(String city) {
    this.city = city;
    return this;
  }

  /**
   * city
   * @return city
  **/
  @ApiModelProperty(value = "city")
  
    public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public RntCstAddress state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")
  
    public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public RntCstAddress country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(value = "")
  
    public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public RntCstAddress zipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  /**
   * Get zipCode
   * @return zipCode
  **/
  @ApiModelProperty(value = "")
  
    public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntCstAddress rntCstAddress = (RntCstAddress) o;
    return Objects.equals(this.line1, rntCstAddress.line1) &&
        Objects.equals(this.line2, rntCstAddress.line2) &&
        Objects.equals(this.city, rntCstAddress.city) &&
        Objects.equals(this.state, rntCstAddress.state) &&
        Objects.equals(this.country, rntCstAddress.country) &&
        Objects.equals(this.zipCode, rntCstAddress.zipCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(line1, line2, city, state, country, zipCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntCstAddress {\n");
    
    sb.append("    line1: ").append(toIndentedString(line1)).append("\n");
    sb.append("    line2: ").append(toIndentedString(line2)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    zipCode: ").append(toIndentedString(zipCode)).append("\n");
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
