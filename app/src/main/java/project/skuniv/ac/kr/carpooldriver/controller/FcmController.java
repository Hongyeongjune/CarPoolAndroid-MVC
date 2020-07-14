package project.skuniv.ac.kr.carpooldriver.controller;

import android.content.Context;

import project.skuniv.ac.kr.carpooldriver.domain.dto.fcm.SendDto;
import project.skuniv.ac.kr.carpooldriver.network.NetRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FcmController {

    Context context;

    public FcmController(Context context) {
        this.context = context;
    }

    public void firebaseMessagingSend(SendDto sendDto) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().firebaseMessagingSend(sendDto);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
