package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.tacoma.uw.ryandon.starfinderopenreference.R;


public class LoginFragment extends Fragment {
    private LoginFragmentListener mLoginFragmentListener;

    public LoginFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getActivity().setTitle("Sign In");
        mLoginFragmentListener = (LoginFragmentListener) getActivity();
        final EditText emailText = view.findViewById(R.id.editTextUsername);
        final EditText pwdText = view.findViewById(R.id.editTextPassword);

        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = emailText.getText().toString();
                String pwd = pwdText.getText().toString();
                if(TextUtils.isEmpty(email) || !email.contains("@")) {
                    Toast.makeText(v.getContext(),"Enter valid email address",
                            Toast.LENGTH_SHORT)
                            .show();
                    emailText.requestFocus();
                }
                else if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
                    Toast.makeText(v.getContext(),"Enter a valid password( atleast 6 characters)"
                            ,Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();

                }


                mLoginFragmentListener.login(email, pwd);
            }
        });
        Button registerButton = view.findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mLoginFragmentListener.register();
            }
        });

        return  view;
    }


    public static interface LoginFragmentListener {

        public void login(String email, String pwd);
        public void register();

    }

}