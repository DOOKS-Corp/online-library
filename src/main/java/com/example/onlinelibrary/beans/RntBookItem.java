package com.example.onlinelibrary.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RntBookItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class RntBookItem   {
  @JsonProperty("isbn")
  private String isbn = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("cost")
  private String cost = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("pubDate")
  private OffsetDateTime pubDate = null;

  @JsonProperty("author")
  @Valid
  private List<RntAuthor> author = null;

  @JsonProperty("publisher")
  private RntPublisher publisher = null;

  public RntBookItem isbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  /**
   * Get isbn
   * @return isbn
  **/
  @ApiModelProperty(value = "")
  
    public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public RntBookItem title(String title) {
    this.title = title;
    return this;
  }

  /**
   * title of the book
   * @return title
  **/
  @ApiModelProperty(value = "title of the book")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public RntBookItem cost(String cost) {
    this.cost = cost;
    return this;
  }

  /**
   * cost of rent per 30 days
   * @return cost
  **/
  @ApiModelProperty(value = "cost of rent per 30 days")
  
    public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }

  public RntBookItem category(String category) {
    this.category = category;
    return this;
  }

  /**
   * ganre
   * @return category
  **/
  @ApiModelProperty(value = "ganre")
  
    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public RntBookItem pubDate(OffsetDateTime pubDate) {
    this.pubDate = pubDate;
    return this;
  }

  /**
   * Get pubDate
   * @return pubDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OffsetDateTime getPubDate() {
    return pubDate;
  }

  public void setPubDate(OffsetDateTime pubDate) {
    this.pubDate = pubDate;
  }

  public RntBookItem author(List<RntAuthor> author) {
    this.author = author;
    return this;
  }

  public RntBookItem addAuthorItem(RntAuthor authorItem) {
    if (this.author == null) {
      this.author = new ArrayList<>();
    }
    this.author.add(authorItem);
    return this;
  }

  /**
   * Get author
   * @return author
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<RntAuthor> getAuthor() {
    return author;
  }

  public void setAuthor(List<RntAuthor> author) {
    this.author = author;
  }

  public RntBookItem publisher(RntPublisher publisher) {
    this.publisher = publisher;
    return this;
  }

  /**
   * Get publisher
   * @return publisher
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public RntPublisher getPublisher() {
    return publisher;
  }

  public void setPublisher(RntPublisher publisher) {
    this.publisher = publisher;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RntBookItem rntBookItem = (RntBookItem) o;
    return Objects.equals(this.isbn, rntBookItem.isbn) &&
        Objects.equals(this.title, rntBookItem.title) &&
        Objects.equals(this.cost, rntBookItem.cost) &&
        Objects.equals(this.category, rntBookItem.category) &&
        Objects.equals(this.pubDate, rntBookItem.pubDate) &&
        Objects.equals(this.author, rntBookItem.author) &&
        Objects.equals(this.publisher, rntBookItem.publisher);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn, title, cost, category, pubDate, author, publisher);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RntBookItem {\n");
    
    sb.append("    isbn: ").append(toIndentedString(isbn)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    pubDate: ").append(toIndentedString(pubDate)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
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
