package au.em.covidapp.data.model.responses;

import au.em.covidapp.data.model.Symptoms;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetSymptomsResponse {

  //Getter and setters for List Objects for Symptoms
  @SerializedName("data")
  @Expose
  private List<Symptoms> symptomList;

  public List<Symptoms> getSymptomList() {
    return symptomList;
  }

  public void setSymptomList(List<Symptoms> symptomList) {
    this.symptomList = symptomList;
  }
}
