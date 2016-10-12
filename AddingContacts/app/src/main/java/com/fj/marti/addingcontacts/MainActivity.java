package com.fj.marti.addingcontacts;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;

public class MainActivity extends Activity {

    private ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*
       * Uri newPerson = addContactName();
       *
       * addMobilePhoneNo(newPerson); addEmail(newPerson);
       * addPostalAddress(newPerson); addOrganization(newPerson);
       */

//        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
//                .withValue(RawContacts.ACCOUNT_TYPE, null)
//                .withValue(RawContacts.ACCOUNT_NAME, null).build());
//
//        //Phone Number
//        ops.add(ContentProviderOperation
//                .newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
//                        rawContactInsertIndex)
//                .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
//                .withValue(Phone.NUMBER, "9X-XXXXXXXXX")
//                .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
//                .withValue(Phone.TYPE, "1").build());
//
//        //Display name/Contact name
//        ops.add(ContentProviderOperation
//                .newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(Data.RAW_CONTACT_ID,
//                        rawContactInsertIndex)
//                .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
//                .withValue(StructuredName.DISPLAY_NAME, "Mike Sullivan")
//                .build());
//
//
//        //Postal Address
//        ops.add(ContentProviderOperation
//                .newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
//                        rawContactInsertIndex)
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POBOX, "Postbox")
//
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET, "street")
//
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, "city")
//
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.REGION, "region")
//
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, "postcode")
//
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY, "country")
//
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.TYPE, "3")
//
//
//                .build());
//
//
//        //Organization details
//        ops.add(ContentProviderOperation
//                .newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(Data.RAW_CONTACT_ID,
//                        rawContactInsertIndex)
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, "Devindia")
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, "Developer")
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, "0")
//
//                .build());
//        //IM details
//        ops.add(ContentProviderOperation
//                .newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(Data.RAW_CONTACT_ID,
//                        rawContactInsertIndex)
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Im.DATA, "ImName")
//                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE )
//                .withValue(ContactsContract.CommonDataKinds.Im.DATA5, "2")
//                .build());
//        try {
//            ContentProviderResult[] res = getContentResolver().applyBatch(
//                    ContactsContract.AUTHORITY, ops);
//        } catch (RemoteException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

    public void addContactEmail(String email) {
        int rawContactInsertIndex = ops.size();

        //Email details
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, "2").build());
    }
}
