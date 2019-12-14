package project.skuniv.ac.kr.carpooldriver.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingGetDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;
import project.skuniv.ac.kr.carpooldriver.network.NetRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class DrivingController {

    Context context;

    private SharedPreferences drivingList;
    private SharedPreferences.Editor drivingEditor;

    public DrivingController() {

    }

    public DrivingController(Context context) {
        this.context = context;
    }

    public void saveDriving(DrivingSaveDto drivingSaveDto) {

        Call<DrivingSaveDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().saveDriving(drivingSaveDto);

        response.enqueue(new Callback<DrivingSaveDto>() {
            @Override
            public void onResponse(Call<DrivingSaveDto> call, Response<DrivingSaveDto> response) {

                if(response.isSuccessful()) {
                    loginCheck = true;
                }
                else {
                    loginCheck = false;
                }
            }

            @Override
            public void onFailure(Call<DrivingSaveDto> call, Throwable t) {

            }
        });

    }

    public void getDrivingByUserId(String userId) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().drivingGetByUserId(userId);

        drivingList = context.getSharedPreferences("drivingListByUserId", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {

                if(response.isSuccessful()) {


                    if(response.body() == null) {
                        return;
                    }
                    drivingEditor.putInt("drivingListSize", response.body().size());

                    Log.d("Size : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("drivingListDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("drivingListDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("drivingListDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("drivingListDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("drivingListDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("drivingListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.commit();

                    }
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<List<DrivingGetDto>> call, Throwable t) {

            }
        });
    }

    public void removeDrivingList(String userId, Long Dno) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().removeDrivingList(userId, Dno);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()) {
                    Log.d("Delete : " , "삭제 성공" );
                }

                else {

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
