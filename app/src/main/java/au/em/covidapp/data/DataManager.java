package au.em.covidapp.data;

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
import au.em.covidapp.data.remote.ApiService;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Wageesha Chinthaka on 2019-06-19.
 */
@Singleton
public class DataManager {

  private ApiService mApiService;

  @Inject
  public DataManager(ApiService apiService) {
    this.mApiService = apiService;
  }

  public Observable<LoginResponse> registerUser(RegisterRequest request) {
    return mApiService.userRegister(request).map(
        registerResponse -> registerResponse);
  }

  public Observable<LoginResponse> login(String userName, String password) {
    return mApiService.userLogin(userName, password).map(
        loginResponse -> loginResponse);
  }

  public Observable<SuccessResponse> createTest(CreateTestRequest createTestRequest) {
    return mApiService.createTest(createTestRequest).map(
        successResponse -> successResponse);
  }

  public Observable<SuccessResponse> createReport(CreateReportRequest createReportRequest) {
    return mApiService.createReport(createReportRequest).map(
        successResponse -> successResponse
    );
  }

  public Observable<GetSymptomsResponse> getSymptoms(GetSymptomsRequest getSymptomsRequest) {
    return mApiService.getSymptoms(getSymptomsRequest).map(
        getSymptomsResponse -> getSymptomsResponse
    );
  }

  public Observable<SuccessResponse> updateSymptoms(UpdateSymptomRequest request) {
    return mApiService.updateSymptoms(request).map(
        successResponse -> successResponse);
  }

  public Observable<SuccessResponse> generateReport(GenerateReportRequest request) {
    return mApiService.generateReport(request).map(
        successResponse -> successResponse);
  }

  public Observable<GetResultResponse> getResult(GetReportRequest getReportRequest){
    return mApiService.getReport(getReportRequest).map(
        getResultResponse -> getResultResponse);
  }
}
