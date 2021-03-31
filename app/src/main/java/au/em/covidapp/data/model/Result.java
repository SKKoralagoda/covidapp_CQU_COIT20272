package au.em.covidapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

  @SerializedName("userid")
  @Expose
  private String userId;

  @SerializedName("risk")
  @Expose
  private String risk;

  @SerializedName("recommended")
  @Expose
  private String recommended;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getRisk() {
    return risk;
  }

  public void setRisk(String risk) {
    this.risk = risk;
  }

  public String getRecommended() {
    return recommended;
  }

  public void setRecommended(String recommended) {
    this.recommended = recommended;
  }
}
