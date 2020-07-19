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
 * RntPublisher
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntPublisher   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("contact")
  private String contact = null;

  public RntPublisher name(String name) {
    this.name = name;
    return this;
  }

  /**
   * name of the publisher
   * @return name
  **/
  @ApiModelProperty(value = "name of the publisher")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RntPublisher contact(String contact) {
    this.contact = contact;
    return this;
  }

  /**
   * name of the contact person
   * @return contact
  **/
  @ApiModelProperty(value = "name of the contact person")
  
    public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntPublisher rntPublisher = (RntPublisher) o;
    return Objects.equals(this.name, rntPublisher.name) &&
        Objects.equals(this.contact, rntPublisher.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntPublisher {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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
