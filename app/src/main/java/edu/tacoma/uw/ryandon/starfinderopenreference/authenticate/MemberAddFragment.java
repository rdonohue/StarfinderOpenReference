package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import edu.tacoma.uw.ryandon.starfinderopenreference.R;
//import edu.tacoma.uw.ryandon.starfinderopenreference.data.MembersDB;
import edu.tacoma.uw.ryandon.starfinderopenreference.model.Members;

public class MemberAddFragment extends Fragment  {


    private MemberAddFragmentListener mMemberAddFragmentListener;
    private Members member;

    public  interface AddListener{

    }



    public MemberAddFragment() {
        // Required empty public constructor
    }


    /**
     * Overriding the onCreate function of the lifecycle.
     * @param savedInstanceState the saved instance state bundle.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This method inflates the member_add_fragment and puts user input into a new member object, which is then added
     * to the heroku database.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.member_add_fragment, container, false);
        getActivity().setTitle("Add a New Member");

        final EditText memberFirstNameEditText = v.findViewById(R.id.add_member_first_name);
        final EditText memberLastNameEditText = v.findViewById(R.id.add_member_last_name);
        final EditText memberUsernameEditText = v.findViewById(R.id.add_member_username);
        final EditText memberEmailEditText = v.findViewById(R.id.add_member_email);
        final EditText memberPwdEditText = v.findViewById(R.id.add_member_pwd);
        Button addButton = v.findViewById(R.id.btn_add_member);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMemberAddFragmentListener = (MemberAddFragmentListener) getActivity();

                String memberFirstName = memberFirstNameEditText.getText().toString();
                String memberLastName = memberLastNameEditText.getText().toString();
                String memberUsername = memberUsernameEditText.getText().toString();
                String memberEmail = memberEmailEditText.getText().toString();
                String memberPwd = memberPwdEditText.getText().toString();

                 member = new Members( memberFirstName, memberLastName, memberEmail, memberUsername, memberPwd);


                if (mMemberAddFragmentListener != null) {

                    mMemberAddFragmentListener.addMember(member);
                }

            }
        });



        return v;
    }


}
