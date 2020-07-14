package project.skuniv.ac.kr.carpooldriver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverInformationActivity extends AppCompatActivity {

    private SharedPreferences driverInformation;
    private SharedPreferences.Editor driverLoginEditor;
    private String licenseNumber, licenseType, licenseClosingDate, carNumber, carType;
    private Boolean isInsurance;
    private TextView driverLicenseNumber, driverLicenseType, driverLicenseClosingDate, driverCarNumber, driverCarType, driverIsInsurance;
    private Button update, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_information);

        driverInformation = getSharedPreferences("driverInformation", Activity.MODE_PRIVATE);
        driverLoginEditor = driverInformation.edit();

        licenseNumber = driverInformation.getString("licenseNumberInfo", null);
        licenseType = driverInformation.getString("licenseTypeInfo", null);
        licenseClosingDate = driverInformation.getString("licenseClosingDateInfo", null);
        carNumber = driverInformation.getString("carNumberInfo", null);
        carType = driverInformation.getString("carTypeInfo", null);
        isInsurance = driverInformation.getBoolean("isInsuranceInfo", false);

        Log.d("LicenseNumber : " , licenseNumber);

        update = (Button) findViewById(R.id.driver_info_update_button);
        cancel = (Button) findViewById(R.id.driver_info_cancel_button);

        driverLicenseNumber = (TextView) findViewById(R.id.driver_info_license_number_text_view);
        driverLicenseType = (TextView) findViewById(R.id.driver_info_license_type_text_view);
        driverLicenseClosingDate = (TextView) findViewById(R.id.driver_info_closing_date_text_view);
        driverCarNumber = (TextView) findViewById(R.id.driver_info_car_number_text_view);
        driverCarType = (TextView) findViewById(R.id.driver_info_car_type_text_view);
        driverIsInsurance = (TextView) findViewById(R.id.driver_info_is_insurance_text_view);

        driverLicenseNumber.setText(licenseNumber);
        driverLicenseType.setText(licenseType);
        driverLicenseClosingDate.setText(licenseClosingDate);
        driverCarNumber.setText(carNumber);
        driverCarType.setText(carType);
        if(isInsurance == true) {
            driverIsInsurance.setText("TRUE");
        }
        else {
            driverIsInsurance.setText("FALSE");
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverStartingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
