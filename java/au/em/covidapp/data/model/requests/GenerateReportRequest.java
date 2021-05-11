package au.em.covidapp.data.model.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateReportRequest {

  //Getter and setters for generate report
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
