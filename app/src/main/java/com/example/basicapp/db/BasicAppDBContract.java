package com.example.basicapp.db;

import android.provider.BaseColumns;

public class BasicAppDBContract {

    private BasicAppDBContract() {}

    public static final class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_NAME_LOGIN = "login";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
