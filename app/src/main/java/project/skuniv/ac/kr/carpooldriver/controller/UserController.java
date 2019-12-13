package project.skuniv.ac.kr.carpooldriver.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

import project.skuniv.ac.kr.carpooldriver.domain.dto.user.CheckUserIdDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.LoginDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignInDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.SignUpDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.UserGetDto;
import project.skuniv.ac.kr.carpooldriver.network.NetRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class UserController {

    Context context;
    private SharedPreferences userInformation;
    private SharedPreferences.Editor loginEditor;

    public UserController(Context context) {
        this.context = context;
    }

    public void singIn(SignInDto signInDto) {
        Call<LoginDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().singIn(signInDto);

        response.enqueue(new Callback<LoginDto>() {
            @Override
            public void onResponse(Call<LoginDto> call, Response<LoginDto> response) {
                if(response.isSuccessful()) {
                    Log.d("Response Data : " , response.body().getMessage().toString());
                    loginCheck = true;
                }
                else {
                    loginCheck = false;
                }
            }

            @Override
            public void onFailure(Call<LoginDto> call, Throwable t) {

            }
        });
    }

    public void signUp(SignUpDto signUpDto) {

        Call<SignUpDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().signUp(signUpDto);

        response.enqueue(new Callback<SignUpDto>() {
            @Override
            public void onResponse(Call<SignUpDto> call, Response<SignUpDto> response) {
                if(response.isSuccessful()) {
                    Log.d("Sign Up is Success : ", response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<SignUpDto> call, Throwable t) {

            }
        });
    }

    public void checkUserId(CheckUserIdDto checkUserIdDto) {

        Call<Boolean> response = NetRetrofit.getInstance().getNetRetrofitInterface().checkUserId(checkUserIdDto);

        response.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()) {
                    loginCheck = true;
                }
                else {
                    loginCheck = false;
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void getUserInformation(String userId) {

        userInformation = context.getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();


        Call<UserGetDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().userGetInformation(userId);
        response.enqueue(new Callback<UserGetDto>() {
            @Override
            public void onResponse(Call<UserGetDto> call, Response<UserGetDto> response) {
                if(response.isSuccessful()) {
                    loginEditor.putString("loginName", response.body().getName());
                    loginEditor.putString("loginAge", response.body().getAge());
                    loginEditor.putString("loginPhone", response.body().getPhone());
                    loginEditor.putString("loginSex", response.body().getSex());
                    loginEditor.putLong("loginUno", response.body().getUno());
                    loginEditor.commit();

                    loginCheck = true;
                }
                else {
                    loginCheck = false;
                }
            }

            @Override
            public void onFailure(Call<UserGetDto> call, Throwable t) {

            }
        });
    }
}
