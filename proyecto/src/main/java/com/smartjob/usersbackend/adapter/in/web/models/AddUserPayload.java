package com.smartjob.usersbackend.adapter.in.web.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.smartjob.usersbackend.adapter.in.web.models.TelephoneDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AddUserPayload
 */
@javax.validation.Valid

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class AddUserPayload implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  @Valid
  private List<@Valid TelephoneDTO> phones = new ArrayList<>();

  public AddUserPayload() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AddUserPayload(String name, String email, String password, List<@Valid TelephoneDTO> phones) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
  }

  public AddUserPayload name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AddUserPayload email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @NotNull @jakarta.validation.constraints.Email 
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AddUserPayload password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   */
  @NotNull 
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AddUserPayload phones(List<@Valid TelephoneDTO> phones) {
    this.phones = phones;
    return this;
  }

  public AddUserPayload addPhonesItem(TelephoneDTO phonesItem) {
    if (this.phones == null) {
      this.phones = new ArrayList<>();
    }
    this.phones.add(phonesItem);
    return this;
  }

  /**
   * Get phones
   * @return phones
   */
  @NotNull @Valid 
  @JsonProperty("phones")
  public List<@Valid TelephoneDTO> getPhones() {
    return phones;
  }

  public void setPhones(List<@Valid TelephoneDTO> phones) {
    this.phones = phones;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddUserPayload addUserPayload = (AddUserPayload) o;
    return Objects.equals(this.name, addUserPayload.name) &&
        Objects.equals(this.email, addUserPayload.email) &&
        Objects.equals(this.password, addUserPayload.password) &&
        Objects.equals(this.phones, addUserPayload.phones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password, phones);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddUserPayload {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    phones: ").append(toIndentedString(phones)).append("\n");
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

