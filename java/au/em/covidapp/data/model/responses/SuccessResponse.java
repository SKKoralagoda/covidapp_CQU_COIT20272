package au.em.covidapp.data.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessResponse {

  //Getter and setters for related objects for success response
  @SerializedName("Success")
  @Expose
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
