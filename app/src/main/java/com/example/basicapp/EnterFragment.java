package com.example.basicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.basicapp.db.AccountTableManager;
import com.example.basicapp.model.Account;

public class EnterFragment extends Fragment {

    public AccountTableManager accountTableManager;

    public EnterFragment() {
        super(R.layout.fragment_enter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountTableManager = AccountTableManager.getInstance(getContext());

//        accountTableManager.createAccount(new Account("ann", "123"));

        EditText loginEdit = view.findViewById(R.id.login_et);
        EditText passwordEdit = view.findViewById(R.id.password_et);

        Button login_btn = view.findViewById(R.id.login_button);
        login_btn.setOnClickListener(view1 -> {

            String login = loginEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            boolean isExisting = accountTableManager.isAccountExisting(login);
            boolean isPassCorrect = accountTableManager.isPasswordCorrect(
                    new Account(login, password));

            if (isExisting && isPassCorrect) {
                Toast.makeText(getContext(), "Passed!!!", Toast.LENGTH_LONG).show();
            } else if (isExisting) {
                Toast.makeText(getContext(), "Password incorrect!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "No such account", Toast.LENGTH_SHORT).show();
            }
        });
    }
}