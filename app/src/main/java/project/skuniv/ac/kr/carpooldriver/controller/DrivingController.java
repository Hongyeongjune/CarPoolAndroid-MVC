package project.skuniv.ac.kr.carpooldriver.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingGetDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driving.DrivingSaveDto;
import project.skuniv.ac.kr.carpooldriver.domain.dto.user.UserGetDto;
import project.skuniv.ac.kr.carpooldriver.network.NetRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class DrivingController {

    Context context;

    private SharedPreferences drivingList, drivingListFinish, userInformation;
    private SharedPreferences.Editor drivingEditor, drivingFinishEditor, userEditor;

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
                        drivingEditor.putString("drivingListUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.commit();

                    }

                    if(response.body().size() == 0){
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

    public void getDrivingByUserIdAndFinish(String userId) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().drivingGetByUserIdAndFinish(userId);

        drivingListFinish = context.getSharedPreferences("drivingListByUserIdAndFinish", Activity.MODE_PRIVATE);
        drivingFinishEditor = drivingListFinish.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {

                if(response.isSuccessful()) {


                    if(response.body() == null) {
                        return;
                    }
                    drivingFinishEditor.putInt("drivingFinishListSize", response.body().size());

                    Log.d("Size : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingFinishEditor.putLong("drivingFinishListDno" + i, response.body().get(i).getDno());
                        drivingFinishEditor.putString("drivingFinishListDistance" + i, response.body().get(i).getDistance());
                        drivingFinishEditor.putString("drivingFinishListDeparture" + i, response.body().get(i).getDeparture());
                        drivingFinishEditor.putString("drivingFinishListDestination" + i, response.body().get(i).getDestination());
                        drivingFinishEditor.putString("drivingFinishListDate" + i, response.body().get(i).getDate());
                        drivingFinishEditor.putString("drivingFinishListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingFinishEditor.putString("drivingFinishListUserId" + i, response.body().get(i).getUserId());
                        drivingFinishEditor.commit();

                    }

                    if(response.body().size() == 0){
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

    public void getAllDrivings() {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().getAllDrivings();

        drivingList = context.getSharedPreferences("drivingAllList", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {
                if(response.isSuccessful()) {

                    if(response.body() == null) {
                        return;
                    }
                    drivingEditor.putInt("drivingAllListSize", response.body().size());

                    Log.d("Size : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("drivingAllListDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("drivingAllListDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("drivingAllListDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("drivingAllListDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("drivingAllListDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("drivingAllListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("drivingAllListUserId" +i, response.body().get(i).getUserId());
                        drivingEditor.commit();

                    }

                    if(response.body().size() == 0){
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

    public void getDrivingByDeparture(String first, String second) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().drivingGetByDeparture(first, second);

        drivingList = context.getSharedPreferences("drivingDepartureList", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {
                if(response.isSuccessful()) {

                    if(response.body() == null) {
                        return;
                    }
                    drivingEditor.putInt("drivingDepartureListSize", response.body().size());

                    Log.d("Size : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("drivingDepartureListDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("drivingDepartureListDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("drivingDepartureListDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("drivingDepartureListDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("drivingDepartureListDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("drivingDepartureListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("drivingDepartureListUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.commit();

                    }

                    if(response.body().size() == 0) {
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

    public void getDrivingByDestination(String first, String second) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().drivingGetByDestination(first, second);

        drivingList = context.getSharedPreferences("drivingDestinationList", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {
                if(response.isSuccessful()) {

                    if(response.body() == null) {
                        return;
                    }
                    drivingEditor.putInt("drivingDestinationListSize", response.body().size());

                    Log.d("Size : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("drivingDestinationListDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("drivingDestinationListDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("drivingDestinationListDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("drivingDestinationListDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("drivingDestinationListDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("drivingDestinationListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("drivingDestinationListUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.commit();

                    }

                    if(response.body().size() == 0){
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

    public void requestGetUserInformation(String userId) {

        userInformation = context.getSharedPreferences("requestUserInformation", Activity.MODE_PRIVATE);
        userEditor = userInformation.edit();

        final String id = userId;

        Call<UserGetDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().requestUserGetInformation(userId);
        response.enqueue(new Callback<UserGetDto>() {
            @Override
            public void onResponse(Call<UserGetDto> call, Response<UserGetDto> response) {
                if(response.isSuccessful()) {
                    userEditor.putString("requestName", response.body().getName());
                    userEditor.putString("requestAge", response.body().getAge());
                    userEditor.putString("requestPhone", response.body().getPhone());
                    userEditor.putString("requestSex", response.body().getSex());
                    userEditor.putLong("requestUno", response.body().getUno());
                    userEditor.putString("requestId", id);
                    userEditor.commit();
                }
                else {
                }
            }

            @Override
            public void onFailure(Call<UserGetDto> call, Throwable t) {

            }
        });
    }

    public void updateDrivingRequest(String userId, Long dno) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().updateDrivingRequest(userId, dno);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateMatchingCancel(Long dno) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().updateMatchingCancel(dno);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateMatchingDriver(String userId, Long dno, String driverId) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().updateMatchingDriver(userId, dno, driverId);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getMatchingList(String userId) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().getMatchingList(userId);

        drivingList = context.getSharedPreferences("matchingList", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {

                if(response.isSuccessful()) {

                    drivingEditor.putInt("matchingListSize", response.body().size());

                    Log.d("ControllerMatchSize : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("matchingListDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("matchingListDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("matchingListDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("matchingListDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("matchingListDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("matchingListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("matchingListUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.putBoolean("matchingListDriverCall" + i, response.body().get(i).isDriverCall());
                        drivingEditor.putBoolean("matchingListDriverFinish" + i, response.body().get(i).isDriverFinishCall());
                        drivingEditor.putBoolean("matchingListUserFinish" + i, response.body().get(i).isUserFinishCall());
                        drivingEditor.putBoolean("matchingListIsDriving" + i, response.body().get(i).isDriving());
                        Log.d("IsDriving : ", response.body().get(i).isDriving() + "");
                        drivingEditor.commit();

                    }

                    if(response.body().size() == 0){
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

    public void getMatchingListByUserId(String userId) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().getMatchingListByUserId(userId);

        drivingList = context.getSharedPreferences("matchingList", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {

                if(response.isSuccessful()) {

                    drivingEditor.putInt("matchingListSize", response.body().size());

                    Log.d("ControlMatchSizeUser : ", response.body().size() + " ");

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("matchingListDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("matchingListDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("matchingListDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("matchingListDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("matchingListDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("matchingListRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("matchingListUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.putBoolean("matchingListDriverCall" + i, response.body().get(i).isDriverCall());
                        drivingEditor.putBoolean("matchingListDriverFinish" + i, response.body().get(i).isDriverFinishCall());
                        drivingEditor.putBoolean("matchingListUserFinish" + i, response.body().get(i).isUserFinishCall());
                        drivingEditor.putBoolean("matchingListIsDriving" + i, response.body().get(i).isDriving());
                        Log.d("IsDriverCallUserId : ", response.body().get(i).isDriverCall() + "");
                        Log.d("IsDrivingUserId : ", response.body().get(i).isDriving() + "");
                        drivingEditor.commit();

                    }

                    if(response.body().size() == 0){
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

    public void updateFinishByDriver(Long dno) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().updateFinishByDriver(dno);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateFinishByUser(Long dno) {

        Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().updateFinishByUser(dno);

        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getFinishByDriver(String userId) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().getFinishByDriver(userId);

        drivingList = context.getSharedPreferences("finishListByDriver", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {
                if(response.isSuccessful()) {

                    drivingEditor.putInt("finishListByDriverSize", response.body().size());

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("finishListByDriverDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("finishListByDriverDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("finishListByDriverDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("finishListByDriverDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("finishListByDriverDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("finishListByDriverRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("finishListByDriverUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.commit();

                    }
                    if(response.body().size() == 0){
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

    public void getFinishByUser(String userId) {

        Call<List<DrivingGetDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().getFinishByUser(userId);

        drivingList = context.getSharedPreferences("finishListByUser", Activity.MODE_PRIVATE);
        drivingEditor = drivingList.edit();

        response.enqueue(new Callback<List<DrivingGetDto>>() {
            @Override
            public void onResponse(Call<List<DrivingGetDto>> call, Response<List<DrivingGetDto>> response) {

                if(response.isSuccessful()) {

                    drivingEditor.putInt("finishListByUserSize", response.body().size());

                    for(int i=0; i<response.body().size(); i++) {

                        drivingEditor.putLong("finishListByUserDno" + i, response.body().get(i).getDno());
                        drivingEditor.putString("finishListByUserDistance" + i, response.body().get(i).getDistance());
                        drivingEditor.putString("finishListByUserDeparture" + i, response.body().get(i).getDeparture());
                        drivingEditor.putString("finishListByUserDestination" + i, response.body().get(i).getDestination());
                        drivingEditor.putString("finishListByUserDate" + i, response.body().get(i).getDate());
                        drivingEditor.putString("finishListByUserRegDate" + i, response.body().get(i).getRegDate() + "");
                        drivingEditor.putString("finishListByUserUserId" + i, response.body().get(i).getUserId());
                        drivingEditor.commit();

                    }
                    if(response.body().size() == 0){
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
}
