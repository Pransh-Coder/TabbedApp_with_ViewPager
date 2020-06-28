package com.example.edvizotask.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.edvizotask.DataPojo;
import com.example.edvizotask.R;
import com.example.edvizotask.RecyclerAdapterItems;
import com.example.edvizotask.ui.main.PageViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JEE_M_A_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;


    public static JEE_M_A_Fragment newInstance(int index) {
        JEE_M_A_Fragment fragment = new JEE_M_A_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue requestQueue;
    List<DataPojo> dataPojoList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_j_e_e__m__a, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://worldtpm.dx.am/api/demoApi.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("status").equals("true")){

                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        //DataPojo dataPojo = new DataPojo();   // If it is kept outside the same data would be filled in the arraylist becoz after terminating of loop last value will be filled in dataPojo obj

                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            DataPojo dataPojo = new DataPojo();

                            String TestName = jsonObject1.getString("Test_Name");
                            String obj = jsonObject1.getString("Test_Type");
                            String date = jsonObject1.getString("Date");
                            String TestPattern= jsonObject1.getString("Test_Pattern");
                            String Max_Marks= jsonObject1.getString("Maximum_Marks");
                            String Marks_Scored= jsonObject1.getString("Marks_Scored");

                            Log.e("values",TestName+"-->"+obj+"-->"+date+"-->"+TestPattern+"--**-->"+Max_Marks+"--**-->"+Marks_Scored);

                            dataPojo.setTestname(jsonObject1.getString("Test_Name"));
                            dataPojo.setTesttype(jsonObject1.getString("Test_Type"));
                            dataPojo.setDate(jsonObject1.getString("Date"));
                            dataPojo.setTestpattern(jsonObject1.getString("Test_Pattern"));
                            dataPojo.setMaxMarks(jsonObject1.getString("Maximum_Marks"));
                            dataPojo.setMarksScored(jsonObject1.getString("Marks_Scored"));

                            dataPojoList.add(dataPojo);
                        }
                        adapter = new RecyclerAdapterItems(getContext(),dataPojoList);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error!",error.toString());
            }
        });
        requestQueue.add(stringRequest);

//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//            }
//        });

        return view;
    }
}