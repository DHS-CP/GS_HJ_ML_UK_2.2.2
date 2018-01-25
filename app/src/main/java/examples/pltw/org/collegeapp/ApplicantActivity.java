package examples.pltw.org.collegeapp;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
//step 8 implemented in line 16 below
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
/*MAKE SURE TO INCLUDE COM.BACKEND:BACKEND:4.4.0, NOT 3.1.1 LIKE THEY SAID GOSH DARNET*/
public class ApplicantActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = ApplicantActivity.class.getName();
    //step 11 implemented in lines 25-26 below
    final String BE_APP_ID = "CDC0B054-F9A4-AF64-FF07-C7B5A44D7000";
    final String BE_ANDROID_API_KEY = "DC9B04BF-D0E8-9BB9-FF36-7225D19F6100";
    //step 20 implemented in line 28 below
    private final String MY_EMAIL_ADDRESS = "silvera4198@mydusd.org";
    //step 21 implemented in line 30 below
    public static final String EMAIL_PREF = "wut";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //step 12 implemented in line 29 below
        Backendless.initApp(this,BE_APP_ID,BE_ANDROID_API_KEY);
        setContentView(R.layout.activity_applicant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //step 13 implemented in lines 36-47 below
       /* BackendlessUser user = new BackendlessUser();
        user.setEmail( MY_EMAIL_ADDRESS );
        user.setPassword( "password" );

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>(){
            @Override
            public void handleResponse(BackendlessUser backendlessUser){
                Log.i( "User ", backendlessUser.getEmail() + " successfully registered" );
            }
            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e( "ANOTHER ERROR! ", backendlessFault.getMessage());
            }
        });
*/
        //step 22 implemented in lines 59-63 below
        SharedPreferences sharedPreferences =
                this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_PREF, MY_EMAIL_ADDRESS);
        editor.commit();



/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.applicant, menu);
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
        Fragment contentFragment = null;

        if (id == R.id.family_member) {
            Log.i(TAG, "Family Member menu item selected.");
            contentFragment = new FamilyListFragment();
        } else if (id == R.id.profile) {
            Log.i(TAG, "Profile menu item selected.");
            contentFragment = new ProfileFragment();
        } else if (id == R.id.guardian_member) {
            Log.i(TAG, "Profile menu item selected.");
            contentFragment = new GuardianFragment();
        }

        if (contentFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, contentFragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}