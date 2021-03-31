package au.em.covidapp.data.model.responses;

import au.em.covidapp.data.model.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetResultResponse {

  @SerializedName("data")
  @Expose
  private List<Result> resultList;

  public List<Result> getResultList() {
    return resultList;
  }

  public void setResultList(List<Result> resultList) {
    this.resultList = resultList;
  }
}
