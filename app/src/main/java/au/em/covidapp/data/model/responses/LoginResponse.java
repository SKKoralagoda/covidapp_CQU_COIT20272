package au.em.covidapp.data.model.responses;

import au.em.covidapp.data.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LoginResponse {

  @SerializedName("data")
  @Expose
  private List<User> user;

  public List<User> getUser() {
    return user;
  }

  public void setUser(List<User> user) {
    this.user = user;
  }
}
