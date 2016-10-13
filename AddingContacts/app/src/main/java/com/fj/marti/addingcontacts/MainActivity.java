package com.fj.marti.addingcontacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

//    private ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://spreadsheets.google.com/tq?key=1sAPOIvsgyqh3lTyDFJyR5wsJLAnYTQOeIIcdJzcV0T0

        deleteContacts();

        new Operation().execute("Add");
    }

    private void deleteContacts() {
        try{
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            while (cur.moveToNext()) {
                try{
                    String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                    //System.out.println("The uri is " + uri.toString());
                    cr.delete(uri, null, null);
                }
                catch(Exception e)
                {
                    System.out.println(e.getStackTrace());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addContactEmail(String email) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

//        int rawContactInsertIndex = ops.size();
        //Account details
        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        //Email details
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, "2").build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

        Log.e("Contact Added: ", email);
    }

    private class Operation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                JSONObject sheet = DownloadSheet.downloadUrl("https://spreadsheets.google.com/tq?key=1sAPOIvsgyqh3lTyDFJyR5wsJLAnYTQOeIIcdJzcV0T0");

                JSONArray rows = (JSONArray) sheet.get("rows");
                for (int i = 0; i < rows.length(); i++) {
                    JSONObject rowObj = rows.getJSONObject(i);
                    JSONArray arr = (JSONArray) rowObj.get("c");
                    JSONObject email = arr.getJSONObject(1);
                    Log.e("Adding Contact: ", email.get("v").toString());
                    addContactEmail(email.get("v").toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if(true) {

                }
            } catch (Exception e) {

            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
