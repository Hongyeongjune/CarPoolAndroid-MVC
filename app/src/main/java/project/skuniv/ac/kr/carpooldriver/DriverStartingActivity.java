package project.skuniv.ac.kr.carpooldriver;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import project.skuniv.ac.kr.carpooldriver.controller.DriverController;
import project.skuniv.ac.kr.carpooldriver.controller.UserController;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class DriverStartingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private UserController userController;
    private TextView name, id;
    private SharedPreferences userInformation, auto;
    private SharedPreferences.Editor loginEditor, editor;
    private String loginId;
    private String loginName;
    private View navigation_header_view;
    private DriverController driverController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_starting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_driver);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_driver);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_driver_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_driver_view);
        navigationView.setNavigationItemSelectedListener(this);

        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();

        auto = getSharedPreferences("autoSignIn", Activity.MODE_PRIVATE);
        editor = auto.edit();

        loginId = userInformation.getString("loginId", null);

        Log.d("Login Id : ", loginId);

        navigation_header_view = navigationView.getHeaderView(0);

        name = (TextView) navigation_header_view.findViewById(R.id.nav_header_driver_name);
        id = (TextView) navigation_header_view.findViewById(R.id.nav_header_driver_id);
        userController = new UserController(getApplicationContext());
        driverController =  new DriverController(getApplicationContext());

        userController.getUserInformation(loginId);
        driverController.getDriverInformation(loginId);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(loginCheck) {
                    loginName = userInformation.getString("loginName", null);
                    Log.d("Login Name : ", loginName);
                    name.setText("Name : " + loginName);
                    id.setText("ID : " + loginId);
                }
            }
        },3000);



    }

    @Override
    public void onBackPressed() {
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_driver_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver_starting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_driver_register) {
            Intent intent = new Intent(getApplicationContext(), DriverRegisterActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_driver_information) {
            Intent intent = new Intent(getApplicationContext(), UserInformationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_driver_license_information) {
            Intent intent = new Intent(getApplicationContext(), DriverInformationActivity.class);
            startActivity(intent);

        }  else if (id == R.id.nav_driver_driving_list) {

        } else if (id == R.id.nav_driver_notice) {

        } else if (id == R.id.nav_driver_logout) {

            editor.putString("autoId", "");
            editor.putString("autoPw", "");
            editor.commit();

            loginEditor.putString("loginId", "");
            loginEditor.putString("loginName","");
            loginEditor.putString("loginAge", "");
            loginEditor.putString("loginPhone", "");
            loginEditor.putString("loginSex", "");
            loginEditor.putLong("loginUno", 0);
            loginEditor.commit();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_driver_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
