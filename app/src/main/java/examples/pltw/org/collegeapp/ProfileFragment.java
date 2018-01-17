package examples.pltw.org.collegeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.Date;
import java.util.List;


/**
 * Created by wdumas on 4/8/16.
 */
public class ProfileFragment extends Fragment{
    private static final String TAG = "ProfileFragment";
    private static final int REQUEST_DATE_OF_BIRTH = 0;
    private static final int WITHIN_8_YEARS = 2008;
    private Button mDateOfBirthButton;

    private TextView mFirstName;
    private EditText mEditFirstName;
    private TextView mLastName;
    private EditText mEditLastName;
    private Button mSubmitButton;

    private Profile mProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfile = new Profile();

        mFirstName = (TextView) rootView.findViewById(R.id.profileFirstNameTextView);
        mFirstName.setText(mProfile.getFirstName());
        mLastName = (TextView) rootView.findViewById(R.id.profileLastNameTextView);
        mLastName.setText(mProfile.getLastName());

        mEditFirstName = (EditText) rootView.findViewById(R.id.profileFirstNameEditText);
        mEditLastName = (EditText) rootView.findViewById(R.id.profileLastNameEditText);

        mSubmitButton = (Button) rootView.findViewById(R.id.profileSubmitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfile.setFirstName(mEditFirstName.getText().toString());
                mFirstName.setText(mProfile.getFirstName());
                mProfile.setLastName(mEditLastName.getText().toString());
                mLastName.setText(mProfile.getLastName());
            }
        });

        mDateOfBirthButton = (Button) rootView.findViewById(R.id.profileDateOfBirthButton);

        mDateOfBirthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mProfile.getDateOfBirth());
                dialog.setTargetFragment(ProfileFragment.this, REQUEST_DATE_OF_BIRTH);
                dialog.show(fm, "DialogDateOfBirth");

            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE_OF_BIRTH) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE_OF_BIRTH);
            try {
                if (date.getYear() + 1900 <= WITHIN_8_YEARS) {
                    mProfile.setDateOfBirth(date);
                    mDateOfBirthButton.setText(mProfile.getDateOfBirth().toString());
                }
                else throw new AgeException("Who are you, Michael Kearney?");
            } catch (AgeException e) {
                Log.i(TAG, e.joinMessageAndYear(e.getMessage(), date.getYear() + 1900));
            }
        }
    }
    //step 14-16 implemented in lines 102-145 below
    @Override
    public void onPause() {
        super.onPause();
        //step 23 implemented in lines 107-112 below
        SharedPreferences sharedPreferences =
                getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
        if (mProfile.getEmail() == null) {
            mProfile.setEmail(email);
        }

        //step 24 implemented in lines 117-133 below
        String whereClause = "email = '" + email + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> profile) {
                if (!profile.isEmpty()) {
                    String profileId = profile.get(0).getObjectId();
                    Log.d(TAG, "Object ID: " + profileId);
                    mProfile.setObjectId(profileId);
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Failed to find profile: " + fault.getMessage());
            }
        });
        Backendless.Data.of(Profile.class).save(mProfile, new AsyncCallback<Profile>() {
            @Override
            public void handleResponse(Profile response) {
                Log.i(TAG, "Saved profile to Backendless");
            }
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, "Failed to save profile!" + fault.getMessage());
            }
        });
    }
    //step 25 implemented in line 146 below
    @Override
    public void onStart(){
        super.onStart();
        //step 26 implemented in lines 149-174 below
        SharedPreferences sharedPreferences =
                getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
        if (mProfile.getEmail() == null) {
            mProfile.setEmail(email);
        }
        String whereClause = "email = '" + email + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> profile) {
                if (!profile.isEmpty()) {
                    mProfile = profile.get(0);
                    String profileFirstName = profile.get(0).getFirstName();
                    Log.d(TAG, "First Name" + profileFirstName);
                    mProfile.setFirstName(profileFirstName);
                    String profileLastName = profile.get(0).getLastName();
                    Log.d(TAG, "Last Name" + profileLastName);
                    mProfile.setLastName(profileLastName);
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Failed to find profile: " + fault.getMessage());
            }
        });
    }

}
