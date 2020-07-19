package com.example.onlinelibrary.beans;

import java.util.Objects;
import com.example.onlinelibrary.beans.Metadata;
import com.example.onlinelibrary.beans.RntBookItem;
import com.example.onlinelibrary.beans.StandardResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RntBookResWrp
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntBookResWrp extends StandardResponse  {
  @JsonProperty("book")
  private RntBookItem book = null;

  public RntBookResWrp book(RntBookItem book) {
    this.book = book;
    return this;
  }

  /**
   * Get book
   * @return book
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public RntBookItem getBook() {
    return book;
  }

  public void setBook(RntBookItem book) {
    this.book = book;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntBookResWrp rntBookResWrp = (RntBookResWrp) o;
    return Objects.equals(this.book, rntBookResWrp.book) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(book, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntBookResWrp {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    book: ").append(toIndentedString(book)).append("\n");
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
