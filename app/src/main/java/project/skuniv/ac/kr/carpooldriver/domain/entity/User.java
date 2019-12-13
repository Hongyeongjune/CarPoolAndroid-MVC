package project.skuniv.ac.kr.carpooldriver.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("uno")
    private Long uno;

    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("age")
    private String age;
    @SerializedName("phone")
    private String phone;

}
