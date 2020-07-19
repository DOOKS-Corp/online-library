package com.example.onlinelibrary.beans;

import java.util.Objects;
import com.example.onlinelibrary.beans.Metadata;
import com.example.onlinelibrary.beans.RntBookItem;
import com.example.onlinelibrary.beans.StandardResponse;
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
 * RntMultBookResWrp
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntMultBookResWrp extends StandardResponse  {
  @JsonProperty("books")
  @Valid
  private List<RntBookItem> books = null;

  public RntMultBookResWrp books(List<RntBookItem> books) {
    this.books = books;
    return this;
  }

  public RntMultBookResWrp addBooksItem(RntBookItem booksItem) {
    if (this.books == null) {
      this.books = new ArrayList<>();
    }
    this.books.add(booksItem);
    return this;
  }

  /**
   * Get books
   * @return books
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<RntBookItem> getBooks() {
    return books;
  }

  public void setBooks(List<RntBookItem> books) {
    this.books = books;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntMultBookResWrp rntMultBookResWrp = (RntMultBookResWrp) o;
    return Objects.equals(this.books, rntMultBookResWrp.books) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(books, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntMultBookResWrp {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    books: ").append(toIndentedString(books)).append("\n");
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
