package com.sunity.fridgeinventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sunity.fridgeinventory.databinding.ActivityMainBinding;
import com.sunity.fridgeinventory.entity.Aliment;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        MainActivity.this
                );
                intentIntegrator.setPrompt("For flash, use volume up");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );

        if (intentResult.getContents() != null) {
            binding.textView.setText(intentResult.getContents());

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://world.openfoodfacts.org/api/v0/product/%.json";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url.replace("%", intentResult.getContents()),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*// Display the first 500 characters of the response string.
                            textView.setText("Response is: " + response.substring(0, 500));*/

                            String jsonString = response;
                            JSONObject obj = null;
                            Aliment temp = new Aliment();
                            try {
                                obj = new JSONObject(jsonString);
                                temp.setBrand(obj.getJSONObject("product").getString("brands"));
                                temp.setName(obj.getJSONObject("product").getString("generic_name_fr"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            binding.textView.setText(temp.getBrand() + " " + temp.getName());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    binding.textView.setText("That didn't work!");
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        } else {
            binding.textView.setText("Vide");
        }
    }
}