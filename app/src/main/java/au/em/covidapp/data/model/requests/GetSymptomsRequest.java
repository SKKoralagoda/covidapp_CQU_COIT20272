package au.em.covidapp.data.model.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSymptomsRequest {

  @SerializedName("userid")
  @Expose
  private String UserId;

  public String getUserId() {
    return UserId;
  }

  public void setUserId(String userId) {
    UserId = userId;
  }
}
