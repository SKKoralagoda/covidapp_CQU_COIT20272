package au.em.covidapp.data.remote;

import au.em.covidapp.data.model.requests.CreateReportRequest;
import au.em.covidapp.data.model.requests.CreateTestRequest;
import au.em.covidapp.data.model.requests.GenerateReportRequest;
import au.em.covidapp.data.model.requests.GetReportRequest;
import au.em.covidapp.data.model.requests.GetSymptomsRequest;
import au.em.covidapp.data.model.requests.RegisterRequest;
import au.em.covidapp.data.model.requests.UpdateSymptomRequest;
import au.em.covidapp.data.model.responses.GetResultResponse;
import au.em.covidapp.data.model.responses.GetSymptomsResponse;
import au.em.covidapp.data.model.responses.LoginResponse;
import au.em.covidapp.data.model.responses.SuccessResponse;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
public interface ApiService {

  //String ENDPOINT = "http://admonster.com.au/covid-app/api/";
  String ENDPOINT = "http://covid-app.site/covid-app/api/";

  @POST("user/userregister.php")
  Observable<LoginResponse> userRegister(
      @Body RegisterRequest registerRequest
  );

  @GET("user/login.php")
  Observable<LoginResponse> userLogin(
      @Query("username") String userName,
      @Query("password") String password
  );

  @POST("symptoms/createtest.php")
  Observable<SuccessResponse> createTest(
      @Body CreateTestRequest createTestRequest
  );

  @POST("report/createreport.php")
  Observable<SuccessResponse> createReport(
      @Body CreateReportRequest createReportRequest
  );

  @PUT("symptoms/getsymptoms.php")
  Observable<GetSymptomsResponse> getSymptoms(
      @Body GetSymptomsRequest getSymptomsRequest
  );

  @PUT("symptoms/updatetest.php")
  Observable<SuccessResponse> updateSymptoms(
      @Body UpdateSymptomRequest updateSymptomRequest
  );

  @Headers({
      "Content-Type: application/json"
  })
  @PUT("report/generatereport.php")
  Observable<SuccessResponse> generateReport(
      @Body GenerateReportRequest generateReportRequest
  );

  @Headers({
      "Content-Type: application/json"
  })
  @PUT("report/getreport.php")
  Observable<GetResultResponse> getReport(
      @Body GetReportRequest getReportRequest
  );

  class Creator {

    private static final long TIME_OUT_DURATION = 30;

    public static ApiService newApiService() {

      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient.Builder builder = new OkHttpClient.Builder()
          .connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
          .writeTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
          .readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
          .addInterceptor(httpLoggingInterceptor);

      OkHttpClient client = builder.build();

      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(ApiService.ENDPOINT)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

      return retrofit.create(ApiService.class);
    }
  }
}
