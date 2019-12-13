package project.skuniv.ac.kr.carpooldriver.controller;

import android.content.Context;

import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;
import project.skuniv.ac.kr.carpooldriver.network.NetRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrivingController {

    Context context;

    public DrivingController(Context context) {
        this.context = context;
    }

    public void saveDriving(DrivingSaveDto drivingSaveDto) {

        Call<DrivingSaveDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().saveDriving(drivingSaveDto);

        response.enqueue(new Callback<DrivingSaveDto>() {
            @Override
            public void onResponse(Call<DrivingSaveDto> call, Response<DrivingSaveDto> response) {

            }

            @Override
            public void onFailure(Call<DrivingSaveDto> call, Throwable t) {

            }
        });

    }
}
