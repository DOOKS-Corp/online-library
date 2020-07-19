package com.example.onlinelibrary.beans;

import java.util.Objects;
import com.example.onlinelibrary.beans.ErrorObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Metadata
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class Metadata   {
  /**
   * The request status code. S - success, R - failed, U - unknown.
   */
  public enum StatusCodeEnum {
    S("S"),
    
    R("R"),
    
    U("U");

    private String value;

    StatusCodeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusCodeEnum fromValue(String text) {
      for (StatusCodeEnum b : StatusCodeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("StatusCode")
  private StatusCodeEnum statusCode = StatusCodeEnum.S;

  @JsonProperty("messages")
  @Valid
  private List<ErrorObject> messages = null;

  public Metadata statusCode(StatusCodeEnum statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  /**
   * The request status code. S - success, R - failed, U - unknown.
   * @return statusCode
  **/
  @ApiModelProperty(required = true, value = "The request status code. S - success, R - failed, U - unknown.")
      @NotNull

    public StatusCodeEnum getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(StatusCodeEnum statusCode) {
    this.statusCode = statusCode;
  }

  public Metadata messages(List<ErrorObject> messages) {
    this.messages = messages;
    return this;
  }

  public Metadata addMessagesItem(ErrorObject messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * Indicates errors or warnings related to the request.
   * @return messages
  **/
  @ApiModelProperty(value = "Indicates errors or warnings related to the request.")
      @Valid
    public List<ErrorObject> getMessages() {
    return messages;
  }

  public void setMessages(List<ErrorObject> messages) {
    this.messages = messages;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata metadata = (Metadata) o;
    return Objects.equals(this.statusCode, metadata.statusCode) &&
        Objects.equals(this.messages, metadata.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, messages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
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
