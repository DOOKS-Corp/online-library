package com.example.onlinelibrary.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ErrorObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-18T21:14:48.044Z[GMT]")
public class ErrorObject   {
  /**
   * Type of the message
   */
  public enum ErrorTypeEnum {
    ERROR("Error"),
    
    WARNING("Warning");

    private String value;

    ErrorTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ErrorTypeEnum fromValue(String text) {
      for (ErrorTypeEnum b : ErrorTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("errorType")
  private ErrorTypeEnum errorType = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("fieldName")
  private String fieldName = null;

  @JsonProperty("moreInfo")
  private String moreInfo = null;

  public ErrorObject errorType(ErrorTypeEnum errorType) {
    this.errorType = errorType;
    return this;
  }

  /**
   * Type of the message
   * @return errorType
  **/
  @ApiModelProperty(required = true, value = "Type of the message")
      @NotNull

    public ErrorTypeEnum getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorTypeEnum errorType) {
    this.errorType = errorType;
  }

  public ErrorObject message(String message) {
    this.message = message;
    return this;
  }

  /**
   * error message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "error message")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorObject fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  /**
   * Name of the fiels caused the error
   * @return fieldName
  **/
  @ApiModelProperty(value = "Name of the fiels caused the error")
  
    public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public ErrorObject moreInfo(String moreInfo) {
    this.moreInfo = moreInfo;
    return this;
  }

  /**
   * error code
   * @return moreInfo
  **/
  @ApiModelProperty(value = "error code")
  
    public String getMoreInfo() {
    return moreInfo;
  }

  public void setMoreInfo(String moreInfo) {
    this.moreInfo = moreInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorObject errorObject = (ErrorObject) o;
    return Objects.equals(this.errorType, errorObject.errorType) &&
        Objects.equals(this.message, errorObject.message) &&
        Objects.equals(this.fieldName, errorObject.fieldName) &&
        Objects.equals(this.moreInfo, errorObject.moreInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorType, message, fieldName, moreInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorObject {\n");
    
    sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    moreInfo: ").append(toIndentedString(moreInfo)).append("\n");
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
