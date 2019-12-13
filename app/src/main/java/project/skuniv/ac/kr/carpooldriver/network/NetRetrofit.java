package project.skuniv.ac.kr.carpooldriver.network;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {

    Context context;

    private static final NetRetrofit ourInstance = new NetRetrofit();

    public static NetRetrofit getInstance() {
        return ourInstance;
    }

    /**
     * Retrofit 객체 생성 과정
     *
     * 1. Retrofit 객체 생성
     * 2. base(api 서버) url 설정
     * 3. json 형식의 reponse 데이터의 파싱을 위해 Gson 추가
     * 3. 위에서 만든 OkHttpClient 객체를 추가
     * 4. Retrofit 빌드
     *
     * 주의) addConverterFactory를 추가하지 않을 경우 어플리케이션이 종료됨
     */
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.13:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitInterface netRetrofitInterface;

    public RetrofitInterface getNetRetrofitInterface() {
        if(netRetrofitInterface == null) {
            netRetrofitInterface = retrofit.create(RetrofitInterface.class);
        }

        return netRetrofitInterface;
    }
}
