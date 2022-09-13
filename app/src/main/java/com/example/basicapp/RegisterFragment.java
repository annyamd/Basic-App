package com.example.basicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicapp.db.AccountTableManager;
import com.example.basicapp.model.Account;

public class RegisterFragment extends Fragment {

    private AccountTableManager accountTableManager;

    private EditText loginEdit;
    private EditText passwordEdit;
    private Button registerBtn;
    private TextView returnTv;

    public RegisterFragment() {
        super(R.layout.fragment_register);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accountTableManager = AccountTableManager.getInstance(getContext());

        bindViews(view);

        registerBtn.setOnClickListener(view1 -> {
            //validate values first
            String login = loginEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            if (accountTableManager.isAccountExisting(login)) {
                Toast.makeText(getContext(), "Account with such login already exists!",
                        Toast.LENGTH_LONG).show();
            } else {
                accountTableManager.createAccount(new Account(login, password));
                Toast.makeText(getContext(), "Account: " + login + " created!!!",
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    private void bindViews(View view) {
        loginEdit = view.findViewById(R.id.reg_login_et);
        passwordEdit = view.findViewById(R.id.reg_password_et);
        registerBtn = view.findViewById(R.id.register_button);
        returnTv = view.findViewById(R.id.return_link);
    }

}
