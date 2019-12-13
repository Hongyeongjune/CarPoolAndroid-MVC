package project.skuniv.ac.kr.carpooldriver.network;

import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.CheckUserIdDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.LoginDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignInDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignUpDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.UserGetDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("carpool/user/signIn")
    Call<LoginDto> singIn(@Body SignInDto signInDto);

    @POST("carpool/user/signUp")
    Call<SignUpDto> signUp(@Body SignUpDto signUpDto);

    @POST("carpool/user/checkUserId")
    Call<Boolean> checkUserId(@Body CheckUserIdDto checkUserIdDto);

    @GET("carpool/user/getInformation/{userId}")
    Call<UserGetDto> userGetInformation(@Path("userId") String userId);

    @POST("carpool/driving/saveDriving")
    Call<DrivingSaveDto> saveDriving(@Body DrivingSaveDto drivingSaveDto);

}