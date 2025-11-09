package com.smartjob.usersbackend.adapter.in.web.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TelephoneDTO
 */
@javax.validation.Valid

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class TelephoneDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String number;

  @NotNull
  private String cityCode;

  @NotNull
  private String countryCode;

  public TelephoneDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TelephoneDTO(String number, String cityCode, String countryCode) {
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
  }

  public TelephoneDTO number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Get number
   * @return number
   */
  @NotNull 
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public TelephoneDTO cityCode(String cityCode) {
    this.cityCode = cityCode;
    return this;
  }

  /**
   * Get cityCode
   * @return cityCode
   */
  @NotNull 
  @JsonProperty("cityCode")
  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public TelephoneDTO countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Get countryCode
   * @return countryCode
   */
  @NotNull 
  @JsonProperty("countryCode")
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TelephoneDTO telephoneDTO = (TelephoneDTO) o;
    return Objects.equals(this.number, telephoneDTO.number) &&
        Objects.equals(this.cityCode, telephoneDTO.cityCode) &&
        Objects.equals(this.countryCode, telephoneDTO.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, cityCode, countryCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TelephoneDTO {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    cityCode: ").append(toIndentedString(cityCode)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
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

