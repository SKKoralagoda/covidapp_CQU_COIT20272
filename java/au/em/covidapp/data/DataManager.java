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

@Singleton
public class DataManager {

  //To access ApiService in file
  private ApiService mApiService;

  //Constructor class
  @Inject
  public DataManager(ApiService apiService) {
    this.mApiService = apiService;
  }

  //To integrate service for user registration
  public Observable<LoginResponse> registerUser(RegisterRequest request) {
    return mApiService.userRegister(request).map(
        registerResponse -> registerResponse);
  }

  //To integrate service for user login
  public Observable<LoginResponse> login(String userName, String password) {
    return mApiService.userLogin(userName, password).map(
        loginResponse -> loginResponse);
  }

  //To integrate service for user test create
  public Observable<SuccessResponse> createTest(CreateTestRequest createTestRequest) {
    return mApiService.createTest(createTestRequest).map(
        successResponse -> successResponse);
  }

  //To integrate service for user report create
  public Observable<SuccessResponse> createReport(CreateReportRequest createReportRequest) {
    return mApiService.createReport(createReportRequest).map(
        successResponse -> successResponse
    );
  }

  //To integrate service for getting saved user answers for symptoms
  public Observable<GetSymptomsResponse> getSymptoms(GetSymptomsRequest getSymptomsRequest) {
    return mApiService.getSymptoms(getSymptomsRequest).map(
        getSymptomsResponse -> getSymptomsResponse
    );
  }

  //To integrate service for save updated answers from user
  public Observable<SuccessResponse> updateSymptoms(UpdateSymptomRequest request) {
    return mApiService.updateSymptoms(request).map(
        successResponse -> successResponse);
  }

  //To integrate service for Generate report(result)
  public Observable<SuccessResponse> generateReport(GenerateReportRequest request) {
    return mApiService.generateReport(request).map(
        successResponse -> successResponse);
  }

  //To integrate service for getting result
  public Observable<GetResultResponse> getResult(GetReportRequest getReportRequest){
    return mApiService.getReport(getReportRequest).map(
        getResultResponse -> getResultResponse);
  }
}
