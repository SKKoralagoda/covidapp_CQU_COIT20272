package au.em.covidapp.data.model.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTestRequest {

  @SerializedName("userid")
  @Expose
  private String userId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
