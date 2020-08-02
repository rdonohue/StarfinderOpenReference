package edu.tacoma.uw.ryandon.starfinderopenreference.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import edu.tacoma.uw.ryandon.starfinderopenreference.R;

public class MemberAddFragment extends Fragment {
    private AddListener mAddListener;

    public interface AddListener {
        public void addMember(Members member);
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MemberAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberAddFragment newInstance(String param1, String param2) {
        MemberAddFragment fragment = new MemberAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddListener = (AddListener) getActivity();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.member_add_fragment, container, false);
        getActivity().setTitle("Add a New Member");
        final EditText memberIdEditText = v.findViewById(R.id.add_member_id);
        final EditText memberFirstNameEditText = v.findViewById(R.id.add_member_first_name);
        final EditText memberLastNameEditText = v.findViewById(R.id.add_member_last_name);
        final EditText memberUsernameEditText = v.findViewById(R.id.add_member_username);
        final EditText memberEmailEditText = v.findViewById(R.id.add_member_email);
        final EditText memberPwdEditText = v.findViewById(R.id.add_member_pwd);
        Button addButton = v.findViewById(R.id.btn_add_member);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memberID = memberIdEditText.getText().toString();
                String memberFirstName = memberFirstNameEditText.getText().toString();
                String memberLastName = memberLastNameEditText.getText().toString();
                String memberUsername = memberUsernameEditText.getText().toString();
                String memberEmail = memberEmailEditText.getText().toString();
                String memberPwd = memberPwdEditText.getText().toString();

                Members member = new Members(memberID, memberFirstName, memberLastName, memberUsername, memberEmail, memberPwd);
                if (mAddListener != null) {
                    mAddListener.addMember(member);
                }
            }
        });


        return v;
    }
}
