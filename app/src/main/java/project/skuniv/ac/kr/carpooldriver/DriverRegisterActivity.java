package project.skuniv.ac.kr.carpooldriver;

import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.controller.DriverController;
import project.skuniv.ac.kr.carpooldriver.domain.dto.driver.DriverRegisterDto;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DriverRegisterActivity extends AppCompatActivity {

    private EditText licenseNumber, licenseType, licenseClosingDate, carNumber, carType;
    private CheckBox isInsurance;
    private Button join, cancel;
    private DriverRegisterDto driverRegisterDto;
    private DriverController driverController;

    private SharedPreferences userId, auto;
    private SharedPreferences.Editor loginEditor, editor;
    private String loginId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);

        licenseNumber = (EditText) findViewById(R.id.license_number_edit_text);
        licenseType = (EditText) findViewById(R.id.license_type_edit_text);
        licenseClosingDate = (EditText) findViewById(R.id.license_closing_date_edit_text);
        carNumber = (EditText) findViewById(R.id.car_number_edit_text);
        carType = (EditText) findViewById(R.id.car_type_edit_text);
        isInsurance = (CheckBox) findViewById(R.id.is_insurance_edit_text);
        join = (Button) findViewById(R.id.driver_register_button_join);
        cancel = (Button) findViewById(R.id.driver_register_button_cancel);
        driverController = new DriverController(getApplicationContext());

        userId = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userId.edit();

        loginId = userId.getString("loginId", null);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!licenseNumber.getText().toString().equals("") || !licenseType.getText().toString().equals("") ||
                !licenseClosingDate.getText().toString().equals("") || !carNumber.getText().toString().equals("") ||
                !carType.getText().toString().equals("") || !isInsurance.isChecked()) {


                    driverRegisterDto = new DriverRegisterDto(licenseNumber.getText().toString(),
                            licenseType.getText().toString(), licenseClosingDate.getText().toString(),
                            carNumber.getText().toString(), carType.getText().toString(), true, loginId);

                    driverController.saveDriver(driverRegisterDto);
                    Intent intent = new Intent(getApplicationContext(), DriverStartingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
