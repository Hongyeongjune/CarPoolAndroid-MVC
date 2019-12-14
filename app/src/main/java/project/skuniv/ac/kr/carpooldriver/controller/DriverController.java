package project.skuniv.ac.kr.carpooldriver.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import project.skuniv.ac.kr.carpooldriver.domain.dto.driver.DriverGetDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driver.DriverRegisterDto;
import project.skuniv.ac.kr.carpooldriver.network.NetRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class DriverController {

    Context context;
    private SharedPreferences driverInformation;
    private SharedPreferences.Editor driverLoginEditor;

    public DriverController(Context context) {
        this.context = context;
    }

    public void saveDriver(DriverRegisterDto driverRegisterDto) {

        Call<DriverRegisterDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().saveDriver(driverRegisterDto);

        response.enqueue(new Callback<DriverRegisterDto>() {
            @Override
            public void onResponse(Call<DriverRegisterDto> call, Response<DriverRegisterDto> response) {
                if(response.isSuccessful()) {
                    Log.d("Register is Success : ", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<DriverRegisterDto> call, Throwable t) {

            }
        });

    }

    public void getDriverInformation(String userId) {

        driverInformation = context.getSharedPreferences("driverInformation", Activity.MODE_PRIVATE);
        driverLoginEditor = driverInformation.edit();

        Call<DriverGetDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().driverGetInformation(userId);

        response.enqueue(new Callback<DriverGetDto>() {
            @Override
            public void onResponse(Call<DriverGetDto> call, Response<DriverGetDto> response) {
                if(response.isSuccessful()) {

                    Log.d("LicenseNumber : " , response.body().getLicenseNumber());

                    driverLoginEditor.putString("licenseNumberInfo", response.body().getLicenseNumber());
                    driverLoginEditor.putString("licenseTypeInfo", response.body().getLicenseType());
                    driverLoginEditor.putString("licenseClosingDateInfo", response.body().getLicenseClosingDate());
                    driverLoginEditor.putString("carNumberInfo", response.body().getCarNumber());
                    driverLoginEditor.putString("carTypeInfo", response.body().getCarType());
                    driverLoginEditor.putBoolean("isInsuranceInfo", response.body().isInsurance());
                    driverLoginEditor.commit();

                    loginCheck = true;
                }
                else {
                    loginCheck = false;
                }
            }

            @Override
            public void onFailure(Call<DriverGetDto> call, Throwable t) {

            }
        });
    }
}
