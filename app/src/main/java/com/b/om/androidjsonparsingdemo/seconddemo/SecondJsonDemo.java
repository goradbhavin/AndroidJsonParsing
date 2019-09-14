package com.b.om.androidjsonparsingdemo.seconddemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.b.om.androidjsonparsingdemo.R;
import com.b.om.androidjsonparsingdemo.controller.HttpHandler;
import com.b.om.androidjsonparsingdemo.controller.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhavin on 1/21/2019.
 */

public class SecondJsonDemo extends AppCompatActivity {

    private ListView listView;
    //private static String url = "https://api.androidhive.info/contacts"; httpUrlConnection
    private static String url = "https://api.androidhive.info/"; //Retrofit

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_json_demo);

        listView = (ListView)findViewById(R.id.listView_activity_contact);


        // First method
        new GetContacts().execute(url);

        // second method
        //loadContacts();

    }

    private void loadContacts() {

        Call<ContactsResponse> call = RetrofitClient.getInstance(url).apiContacts().getContacts();
        call.enqueue(new Callback<ContactsResponse>() {
            @Override
            public void onResponse(Call<ContactsResponse> call, Response<ContactsResponse> response) {

                List<Contacts> contacts = new ArrayList<>();
                ContactsResponse contactsResponse = response.body();
                if (response.isSuccessful()){
                    contacts = contactsResponse.getContacts();
                    ContactAdapter adapter = new ContactAdapter(getApplicationContext(),R.layout.item_contact,contacts);
                    listView.setAdapter(adapter);
                    final List<Contacts> finalContacts = contacts;
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getApplicationContext(), finalContacts.get(i).getName(),Toast.LENGTH_LONG).show();
                        }

                    });
                    Log.d("Response",response.body().getContacts().toString());

                }else {
                    Log.d("Response",response.body().getContacts().toString());
                }
            }

            @Override
            public void onFailure(Call<ContactsResponse> call, Throwable t) {
                Log.d("Response",t.getMessage());
            }
        });
    }

    private class GetContacts extends AsyncTask<String,String,List<Contacts>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Contacts> doInBackground(String... strings) {

            HttpHandler handler = new HttpHandler();
            String finalObject = handler.makeServiceCall(strings[0]);

            try {
                JSONObject parentObject = new JSONObject(finalObject);
                JSONArray parentArray = parentObject.getJSONArray("contacts");

                List<Contacts> contactsList = new ArrayList<>();

                for (int i=0;i<parentArray.length();i++){

                    JSONObject object = parentArray.getJSONObject(i);
                    JSONObject phoneObject = object.getJSONObject("phone");
                    Contacts contacts = new Contacts();

                    contacts.setId(object.getString("id"));
                    contacts.setName(object.getString("name"));
                    contacts.setEmail(object.getString("email"));
                    contacts.setAddress(object.getString("address"));
                    contacts.setGender(object.getString("gender"));
                    contacts.setMobile(phoneObject.getString("mobile"));
                    contacts.setHome(phoneObject.getString("home"));
                    contacts.setOffice(phoneObject.getString("office"));
                    contactsList.add(contacts);
                    Log.d("Contacts",contacts.toString());
                }
                return contactsList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Contacts> contactses) {
            super.onPostExecute(contactses);

            if (contactses != null){
                ContactAdapter adapter = new ContactAdapter(getApplicationContext(),R.layout.item_contact,contactses);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getApplicationContext(),contactses.get(i).getName(),Toast.LENGTH_LONG).show();
                    }

                });
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
