package au.em.covidapp.data.model.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

  @SerializedName("firstname")
  @Expose
  private String firstName;

  @SerializedName("lastname")
  @Expose
  private String lastName;

  @SerializedName("phone")
  @Expose
  private String phone;

  @SerializedName("houseno")
  @Expose
  private String houseNo;

  @SerializedName("lane")
  @Expose
  private String houseLane;

  @SerializedName("suburb")
  @Expose
  private String houseSuburb;

  @SerializedName("postcode")
  @Expose
  private String postCode;

  @SerializedName("age")
  @Expose
  private String age;

  @SerializedName("gender")
  @Expose
  private String gender;

  @SerializedName("email")
  @Expose
  private String email;

  @SerializedName("username")
  @Expose
  private String userName;

  @SerializedName("password")
  @Expose
  private String password;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getHouseNo() {
    return houseNo;
  }

  public void setHouseNo(String houseNo) {
    this.houseNo = houseNo;
  }

  public String getHouseLane() {
    return houseLane;
  }

  public void setHouseLane(String houseLane) {
    this.houseLane = houseLane;
  }

  public String getHouseSuburb() {
    return houseSuburb;
  }

  public void setHouseSuburb(String houseSuburb) {
    this.houseSuburb = houseSuburb;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
