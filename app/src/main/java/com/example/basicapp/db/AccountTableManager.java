package com.example.basicapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basicapp.model.Account;

public class AccountTableManager {

    private static AccountTableManager accountTableManager;
    private final BasicAppDbHelper dbHelper;
    private final SQLiteDatabase db;

    private AccountTableManager(Context context) {
        dbHelper = new BasicAppDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static AccountTableManager getInstance(Context context) {
        if (accountTableManager == null) {
            accountTableManager = new AccountTableManager(context);
        }
        return accountTableManager;
    }


    public void createAccount(Account account) {
        ContentValues values = getContentValues(account);
        db.insert(BasicAppDBContract.LoginEntry.TABLE_NAME, null, values);
    }

    public boolean isAccountExisting(String login) {
        Cursor cursor =  queryAccountByLogin(login);
        int cols = cursor.getCount();
        cursor.close();
        return cols > 0;
    }

    //true -- correct password, false -- incorrect password or account does not exist
    public boolean isPasswordCorrect(Account account) {
        Cursor cursor = queryAccountByLogin(account.getLogin());
        cursor.moveToFirst();
        if (cursor.isAfterLast()) return false;
        String password = cursor.getString(cursor.getColumnIndexOrThrow(
                BasicAppDBContract.LoginEntry.COLUMN_NAME_PASSWORD));
        cursor.close();
        return password.equals(account.getPassword());

    }

    // change password operation
    public void updateAccount(Account account) {
        String login = account.getLogin();
        ContentValues values = getContentValues(account);

        db.update(BasicAppDBContract.LoginEntry.TABLE_NAME, values,
                BasicAppDBContract.LoginEntry.COLUMN_NAME_LOGIN + " = ?",
                new String[]{login});
    }

    public void deleteAccount(String login) {
        db.delete(login, BasicAppDBContract.LoginEntry.COLUMN_NAME_LOGIN + " = ?",
                new String[]{login});
    }

    private Cursor queryAccountByLogin(String login) {
        return db.query(
                BasicAppDBContract.LoginEntry.TABLE_NAME,
                null,
                BasicAppDBContract.LoginEntry.COLUMN_NAME_LOGIN + " = ?",
                new String[]{login},
                null,
                null,
                null
        );
    }

    private ContentValues getContentValues(Account account) {
        ContentValues values = new ContentValues();
        values.put(BasicAppDBContract.LoginEntry.COLUMN_NAME_LOGIN, account.getLogin());
        values.put(BasicAppDBContract.LoginEntry.COLUMN_NAME_PASSWORD, account.getPassword());
        return values;
    }

}
