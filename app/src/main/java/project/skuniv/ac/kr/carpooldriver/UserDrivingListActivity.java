package project.skuniv.ac.kr.carpooldriver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import project.skuniv.ac.kr.carpooldriver.adapter.UserDrivingListAdapter;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.time.LocalDate;

public class UserDrivingListActivity extends AppCompatActivity {

    private UserDrivingListAdapter userDrivingListAdapter;
    private ListView listView;
    private LocalDate localDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_driving_list);

        localDate = LocalDate.now();

        listView = (ListView) findViewById(R.id.user_driving_list_view);

        userDrivingListAdapter = new UserDrivingListAdapter();

        listView.setAdapter(userDrivingListAdapter);

        userDrivingListAdapter.addItem(3L,"asd","asd","asd","asd", localDate);

    }
}
