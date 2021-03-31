package au.em.covidapp.data.model.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateSymptomRequest {

  @SerializedName("userid")
  @Expose
  private String userId;

  @SerializedName("temperature")
  @Expose
  private String temperature;

  @SerializedName("dry_cough")
  @Expose
  private String dryCough;

  @SerializedName("sneezing")
  @Expose
  private String sneezing;

  @SerializedName("sore_throat")
  @Expose
  private String soreThroat;

  @SerializedName("runny_nose")
  @Expose
  private String runnyNose;

  @SerializedName("stomach_pain")
  @Expose
  private String stomachPain;

  @SerializedName("diarrhea")
  @Expose
  private String diarrhea;

  @SerializedName("weakness")
  @Expose
  private String weakness;

  @SerializedName("moderate_cough")
  @Expose
  private String moderateCough;

  @SerializedName("high_cough")
  @Expose
  private String highCough;

  @SerializedName("feeling_breathless")
  @Expose
  private String feelingBreathless;

  @SerializedName("difficult_breathless")
  @Expose
  private String difficultBreathless;

  @SerializedName("drowsiness")
  @Expose
  private String drowsiness;

  @SerializedName("chest_pain")
  @Expose
  private String chestPain;

  @SerializedName("severe_weakness")
  @Expose
  private String severeWeakness;

  @SerializedName("diabetes")
  @Expose
  private String diabetes;

  @SerializedName("high_dp")
  @Expose
  private String highBloodPressure;

  @SerializedName("heart_disease")
  @Expose
  private String heartDisease;

  @SerializedName("kidney_disease")
  @Expose
  private String kidneyDisease;

  @SerializedName("lung_disorder")
  @Expose
  private String lungDisorder;

  @SerializedName("stroke")
  @Expose
  private String stroke;

  @SerializedName("compromised_imu_sys")
  @Expose
  private String compromisedImmune;

  @SerializedName("T_in_14d")
  @Expose
  private String TravelInDays;

  @SerializedName("T_in_hrc")
  @Expose
  private String TravelInHrs;

  @SerializedName("contact_cnf_case")
  @Expose
  private String contactCNFCase;

  @SerializedName("date")
  @Expose
  private String dateTime;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public String getDryCough() {
    return dryCough;
  }

  public void setDryCough(String dryCough) {
    this.dryCough = dryCough;
  }

  public String getSneezing() {
    return sneezing;
  }

  public void setSneezing(String sneezing) {
    this.sneezing = sneezing;
  }

  public String getSoreThroat() {
    return soreThroat;
  }

  public void setSoreThroat(String soreThroat) {
    this.soreThroat = soreThroat;
  }

  public String getRunnyNose() {
    return runnyNose;
  }

  public void setRunnyNose(String runnyNose) {
    this.runnyNose = runnyNose;
  }

  public String getStomachPain() {
    return stomachPain;
  }

  public void setStomachPain(String stomachPain) {
    this.stomachPain = stomachPain;
  }

  public String getDiarrhea() {
    return diarrhea;
  }

  public void setDiarrhea(String diarrhea) {
    this.diarrhea = diarrhea;
  }

  public String getWeakness() {
    return weakness;
  }

  public void setWeakness(String weakness) {
    this.weakness = weakness;
  }

  public String getModerateCough() {
    return moderateCough;
  }

  public void setModerateCough(String moderateCough) {
    this.moderateCough = moderateCough;
  }

  public String getHighCough() {
    return highCough;
  }

  public void setHighCough(String highCough) {
    this.highCough = highCough;
  }

  public String getFeelingBreathless() {
    return feelingBreathless;
  }

  public void setFeelingBreathless(String feelingBreathless) {
    this.feelingBreathless = feelingBreathless;
  }

  public String getDifficultBreathless() {
    return difficultBreathless;
  }

  public void setDifficultBreathless(String difficultBreathless) {
    this.difficultBreathless = difficultBreathless;
  }

  public String getDrowsiness() {
    return drowsiness;
  }

  public void setDrowsiness(String drowsiness) {
    this.drowsiness = drowsiness;
  }

  public String getChestPain() {
    return chestPain;
  }

  public void setChestPain(String chestPain) {
    this.chestPain = chestPain;
  }

  public String getSevereWeakness() {
    return severeWeakness;
  }

  public void setSevereWeakness(String severeWeakness) {
    this.severeWeakness = severeWeakness;
  }

  public String getDiabetes() {
    return diabetes;
  }

  public void setDiabetes(String diabetes) {
    this.diabetes = diabetes;
  }

  public String getHighBloodPressure() {
    return highBloodPressure;
  }

  public void setHighBloodPressure(String highBloodPressure) {
    this.highBloodPressure = highBloodPressure;
  }

  public String getHeartDisease() {
    return heartDisease;
  }

  public void setHeartDisease(String heartDisease) {
    this.heartDisease = heartDisease;
  }

  public String getKidneyDisease() {
    return kidneyDisease;
  }

  public void setKidneyDisease(String kidneyDisease) {
    this.kidneyDisease = kidneyDisease;
  }

  public String getLungDisorder() {
    return lungDisorder;
  }

  public void setLungDisorder(String lungDisorder) {
    this.lungDisorder = lungDisorder;
  }

  public String getStroke() {
    return stroke;
  }

  public void setStroke(String stroke) {
    this.stroke = stroke;
  }

  public String getCompromisedImmune() {
    return compromisedImmune;
  }

  public void setCompromisedImmune(String compromisedImmune) {
    this.compromisedImmune = compromisedImmune;
  }

  public String getTravelInDays() {
    return TravelInDays;
  }

  public void setTravelInDays(String travelInDays) {
    TravelInDays = travelInDays;
  }

  public String getTravelInHrs() {
    return TravelInHrs;
  }

  public void setTravelInHrs(String travelInHrs) {
    TravelInHrs = travelInHrs;
  }

  public String getContactCNFCase() {
    return contactCNFCase;
  }

  public void setContactCNFCase(String contactCNFCase) {
    this.contactCNFCase = contactCNFCase;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }
}
