package project.skuniv.ac.kr.carpooldriver.network;

import java.util.List;

import project.skuniv.ac.kr.carpooldriver.domain.dto.driver.DriverGetDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driver.DriverRegisterDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingGetDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.CheckUserIdDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.LoginDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignInDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignUpDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.UserGetDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    /**
     * User
     */

    @POST("carpool/user/signIn")
    Call<LoginDto> singIn(@Body SignInDto signInDto);

    @POST("carpool/user/signUp")
    Call<SignUpDto> signUp(@Body SignUpDto signUpDto);

    @POST("carpool/user/checkUserId")
    Call<Boolean> checkUserId(@Body CheckUserIdDto checkUserIdDto);

    @GET("carpool/user/getInformation/{userId}")
    Call<UserGetDto> userGetInformation(@Path("userId") String userId);

    @GET("carpool/user/getInformation/{userId}")
    Call<UserGetDto> requestUserGetInformation(@Path("userId") String userId);

    /**
     * Driving
     */

    @POST("carpool/driving/saveDriving")
    Call<DrivingSaveDto> saveDriving(@Body DrivingSaveDto drivingSaveDto);

    @GET("carpool/driving/getDrivingListByUserId/{userId}")
    Call<List<DrivingGetDto>> drivingGetByUserId(@Path("userId") String userId);

    @GET("carpool/driving/getDrivingListByUserIdAndFinish/{userId}")
    Call<List<DrivingGetDto>> drivingGetByUserIdAndFinish(@Path("userId") String userId);

    @DELETE("carpool/driving/removeDrivingList/{userId}/{dno}")
    Call<Void> removeDrivingList(@Path("userId") String userId, @Path("dno") Long dno);

    @GET("carpool/driving/getAllDrivings")
    Call<List<DrivingGetDto>> getAllDrivings();

    @GET("carpool/driving/getDrivingByDeparture/{first}/{second}")
    Call<List<DrivingGetDto>> drivingGetByDeparture(@Path("first") String first, @Path("second") String second);

    @GET("carpool/driving/getDrivingByDestination/{first}/{second}")
    Call<List<DrivingGetDto>> drivingGetByDestination(@Path("first") String first, @Path("second") String second);

    @PUT("carpool/driving/updateDrivingRequest/{userId}/{dno}")
    Call<Void> updateDrivingRequest(@Path("userId") String userId, @Path("dno") Long dno);

    @GET("carpool/driving/getMatchingList/{userId}")
    Call<List<DrivingGetDto>> getMatchingList(@Path("userId") String userId);

    @GET("carpool/driving/getMatchingListByUserId/{userId}")
    Call<List<DrivingGetDto>> getMatchingListByUserId(@Path("userId") String userId);

    @PUT("carpool/driving/updateMatchingDriver/{userId}/{dno}/{driverId}")
    Call<Void> updateMatchingDriver(@Path("userId") String userId, @Path("dno") Long dno, @Path("driverId") String driverId);

    @PUT("carpool/driving/updateMatchingCancel/{dno}")
    Call<Void> updateMatchingCancel(@Path("dno") Long dno);

    @PUT("carpool/driving/updateFinishByDriver/{dno}")
    Call<Void> updateFinishByDriver(@Path("dno") Long dno);

    @PUT("carpool/driving/updateFinishByUser/{dno}")
    Call<Void> updateFinishByUser(@Path("dno") Long dno);

    @GET("carpool/driving/getFinishByDriver/{userId}")
    Call<List<DrivingGetDto>> getFinishByDriver(@Path("userId") String userId);

    @GET("carpool/driving/getFinishByUser/{userId}")
    Call<List<DrivingGetDto>> getFinishByUser(@Path("userId") String userId);

    /**
     * Driver
     */

    @POST("carpool/driver/saveDriver")
    Call<DriverRegisterDto> saveDriver(@Body DriverRegisterDto driverRegisterDto);

    @GET("carpool/driver/getInformation/{userId}")
    Call<DriverGetDto> driverGetInformation(@Path("userId") String userId);

}