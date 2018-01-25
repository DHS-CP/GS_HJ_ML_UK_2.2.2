package examples.pltw.org.collegeapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gustavo Silvera on 1/24/2018.
 */

public class FamilyListFragment extends ListFragment { //#18 implemented here
    private static final String TAG = FamilyListFragment.class.getName(); //#19 implemented here
    private static Family mFamily; //#20 implemented here
    public FamilyListFragment () { //#21 implemented here
        mFamily = Family.get(); //#22 implemented here
    }
    //step 23 implemented in lines 18-25 below
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.family_members_title);

        FamilyMemberAdapter adapter = new FamilyMemberAdapter(mFamily.getFamily());
        setListAdapter(adapter);
    }
    //step 24 implemented in lines 27-49 below
    private class FamilyMemberAdapter extends ArrayAdapter<FamilyMember> {
        public FamilyMemberAdapter(ArrayList<FamilyMember> family) {
            super(getActivity(), 0, family);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_family_member, null);
            }

            FamilyMember f = getItem(position);
            TextView nameTextView = (TextView)convertView.findViewById(R.id.family_member_list_item_nameTextView);
            nameTextView.setText(f.getFirstName() + " " + f.getLastName());

            return convertView;
        }
    }
}