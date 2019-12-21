package project.skuniv.ac.kr.carpooldriver;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import project.skuniv.ac.kr.carpooldriver.adapter.DriverDrivingListAdapter;
import project.skuniv.ac.kr.carpooldriver.controller.DriverController;
import project.skuniv.ac.kr.carpooldriver.controller.DrivingController;
import project.skuniv.ac.kr.carpooldriver.controller.UserController;

import static project.skuniv.ac.kr.carpooldriver.IntroActivity.loginCheck;

public class DriverStartingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int userCheck = 0;

    private UserController userController;
    private TextView name, id;
    private SharedPreferences userInformation, auto, drivingTotalList, request;
    private SharedPreferences.Editor loginEditor, editor, drivingTotalEditor, requestEditor;
    private String loginId;
    private String loginName;
    private View navigation_header_view;
    private DriverController driverController;
    private DrivingController drivingController;

    private Spinner select, firstPlace, place;

    private ListView drivingList;
    private DriverDrivingListAdapter driverDrivingListAdapter;

    private String selectPlace = "";
    
    private Button searchButton, clearButton;

    private String selectItem = null, item = null;
    
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

        searchButton = (Button) findViewById(R.id.driver_starting_search_button);
        clearButton = (Button) findViewById(R.id.driver_starting_clear_button);
        userInformation = getSharedPreferences("loginInformation", Activity.MODE_PRIVATE);
        loginEditor = userInformation.edit();

        auto = getSharedPreferences("autoSignIn", Activity.MODE_PRIVATE);
        editor = auto.edit();

        request = getSharedPreferences("requestUserInformation", Activity.MODE_PRIVATE);
        requestEditor = request.edit();

        loginId = userInformation.getString("loginId", null);

        Log.d("Login Id : ", loginId);

        navigation_header_view = navigationView.getHeaderView(0);

        name = (TextView) navigation_header_view.findViewById(R.id.nav_header_driver_name);
        id = (TextView) navigation_header_view.findViewById(R.id.nav_header_driver_id);
        userController = new UserController(getApplicationContext());
        driverController =  new DriverController(getApplicationContext());;

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
        },2000);

        drivingList = (ListView) findViewById(R.id.driving_start_list_view);
        driverDrivingListAdapter = new DriverDrivingListAdapter();
        drivingList.setAdapter(driverDrivingListAdapter);
        drivingController = new DrivingController(getApplicationContext());

        select = (Spinner) findViewById(R.id.spinner_start);
        firstPlace = (Spinner) findViewById(R.id.spinner_first_place);

        place = (Spinner) findViewById(R.id.spinner_second_place);

        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPlace = (String) select.getSelectedItem();
                Log.d("SelectPlace : ", selectPlace);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        firstPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(((String)firstPlace.getSelectedItem()).equals("-----")) {
                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.clear, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                }
                else if(((String)firstPlace.getSelectedItem()).equals("서울")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.seoul, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();
                            
                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("경기")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.gyeonggi, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("인천")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.incheon, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("부산")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.busan, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("대구")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.daegu, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("광주")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.gwangju, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("대전")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.daejeon, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("울산")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.ulsan, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                else if(((String)firstPlace.getSelectedItem()).equals("제주")) {

                    ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.jeju, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    place.setAdapter(adapter);
                    place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            selectItem = (String)firstPlace.getSelectedItem();
                            item = (String)place.getSelectedItem();

                            Log.d("Item : " , selectItem + " / " + item);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        drivingController.getAllDrivings();

        Handler handler1 = new Handler();

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                drivingTotalList = getSharedPreferences("drivingAllList", Activity.MODE_PRIVATE);
                drivingTotalEditor = drivingTotalList.edit();

                Log.d("DrivingList : ", drivingTotalList.getString("drivingAllListDeparture" + 0, null));

                int size = drivingTotalList.getInt("drivingAllListSize", 0);

                Log.d("Size : ", drivingTotalList.getInt("drivingAllListSize", 0) + "");
                for (int i = 0; i < size; i++) {

                    Log.d("User Id : ", drivingTotalList.getString("drivingAllListUserId" + i, null));
                    driverDrivingListAdapter.addItem(drivingTotalList.getLong("drivingAllListDno" + i, 0L),
                            drivingTotalList.getString("drivingAllListDistance" + i, null),
                            drivingTotalList.getString("drivingAllListDeparture" + i, null),
                            drivingTotalList.getString("drivingAllListDestination" + i, null),
                            drivingTotalList.getString("drivingAllListDate" + i, null),
                            drivingTotalList.getString("drivingAllListRegDate" + i, null));
                }

                if(size == 0) {
                    driverDrivingListAdapter.deleteItem();

                    Toast.makeText(DriverStartingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                }

                userCheck = 1;

                driverDrivingListAdapter.notifyDataSetChanged();
            }
        }, 3000);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectItem!= null && item!= null) {
                    setPlaceList(selectItem, item);
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select.setSelection(0);
                firstPlace.setSelection(0);

                driverDrivingListAdapter.deleteItem();

                drivingController.getAllDrivings();

                Handler handler3 = new Handler();

                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drivingTotalList = getSharedPreferences("drivingAllList", Activity.MODE_PRIVATE);
                        drivingTotalEditor = drivingTotalList.edit();

                        Log.d("DrivingList : ", drivingTotalList.getString("drivingAllListDeparture" + 0, null));

                        int size = drivingTotalList.getInt("drivingAllListSize", 0);

                        Log.d("Size : ", drivingTotalList.getInt("drivingAllListSize", 0) + "");


                        for (int i = 0; i < size; i++) {
                            Log.d("User Id : ", drivingTotalList.getString("drivingAllListUserId" + i, null));
                            driverDrivingListAdapter.addItem(drivingTotalList.getLong("drivingAllListDno" + i, 0L),
                                    drivingTotalList.getString("drivingAllListDistance" + i, null),
                                    drivingTotalList.getString("drivingAllListDeparture" + i, null),
                                    drivingTotalList.getString("drivingAllListDestination" + i, null),
                                    drivingTotalList.getString("drivingAllListDate" + i, null),
                                    drivingTotalList.getString("drivingAllListRegDate" + i, null));
                        }

                        if(size == 0) {
                            driverDrivingListAdapter.deleteItem();

                            Toast.makeText(DriverStartingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                        }

                        userCheck = 1;

                        driverDrivingListAdapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });
    }

    private void setPlaceList(String selectedItem, String item) {
        
        if(selectPlace.equals("출발지")) {
            driverDrivingListAdapter.deleteItem();

            drivingController.getDrivingByDeparture(selectedItem, item);

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drivingTotalList = getSharedPreferences("drivingDepartureList", Activity.MODE_PRIVATE);
                    drivingTotalEditor = drivingTotalList.edit();

                    Log.d("DrivingList : ", drivingTotalList.getString("drivingDepartureListDeparture" + 0, null));

                    int size = drivingTotalList.getInt("drivingDepartureListSize", 0);

                    Log.d("Size : ", drivingTotalList.getInt("drivingDepartureListSize", 0) + "");
                    for (int i = 0; i < size; i++) {

                        driverDrivingListAdapter.addItem(drivingTotalList.getLong("drivingDepartureListDno" + i, 0L),
                                drivingTotalList.getString("drivingDepartureListDistance" + i, null),
                                drivingTotalList.getString("drivingDepartureListDeparture" + i, null),
                                drivingTotalList.getString("drivingDepartureListDestination" + i, null),
                                drivingTotalList.getString("drivingDepartureListDate" + i, null),
                                drivingTotalList.getString("drivingDepartureListRegDate" + i, null));
                    }

                    if(size == 0) {
                        driverDrivingListAdapter.deleteItem();

                        Toast.makeText(DriverStartingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    userCheck = 2;

                    if(size == 0) {
                        driverDrivingListAdapter.deleteItem();

                        Toast.makeText(DriverStartingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    driverDrivingListAdapter.notifyDataSetChanged();
                }
            },3000);
        }
        else if(selectPlace.equals("도착지")) {

            driverDrivingListAdapter.deleteItem();

            drivingController.getDrivingByDestination(selectedItem, item);

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drivingTotalList = getSharedPreferences("drivingDestinationList", Activity.MODE_PRIVATE);
                    drivingTotalEditor = drivingTotalList.edit();

                    Log.d("DrivingList : ", drivingTotalList.getString("drivingDestinationListDeparture" + 0, null));

                    int size = drivingTotalList.getInt("drivingDestinationListSize", 0);

                    Log.d("Size : ", drivingTotalList.getInt("drivingDestinationListSize", 0) + "");
                    for (int i = 0; i < size; i++) {

                        driverDrivingListAdapter.addItem(drivingTotalList.getLong("drivingDestinationListDno" + i, 0L),
                                drivingTotalList.getString("drivingDestinationListDistance" + i, null),
                                drivingTotalList.getString("drivingDestinationListDeparture" + i, null),
                                drivingTotalList.getString("drivingDestinationListDestination" + i, null),
                                drivingTotalList.getString("drivingDestinationListDate" + i, null),
                                drivingTotalList.getString("drivingDestinationListRegDate" + i, null));
                    }

                    if(size == 0) {
                        driverDrivingListAdapter.deleteItem();
                        Toast.makeText(DriverStartingActivity.this, "요청 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    userCheck = 3;
                    driverDrivingListAdapter.notifyDataSetChanged();
                }
            },3000);
        }
        else {
            Toast.makeText(this, "출발지 / 도착지 선택해주세요", Toast.LENGTH_SHORT).show();
        }
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
            Intent intent = new Intent(getApplicationContext(), RequestDrivingActivity.class);
            intent.putExtra("matchingCheck", 1);
            startActivity(intent);

        } else if (id == R.id.nav_driver_driving_list_finish) {
            Intent intent = new Intent(getApplicationContext(), FinishDrivingActivity.class);
            intent.putExtra("finishCheckByGroup", 1);
            startActivity(intent);
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
