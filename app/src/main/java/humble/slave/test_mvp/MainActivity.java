package humble.slave.test_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import humble.slave.test_mvp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.output.setText("");
        getCityList();
    }


    public void getCityList() {

        String jsonFileString = getJsonFromAssets(getApplicationContext());
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<City>>() { }.getType();

        List<City> cityList = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < cityList.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + cityList.get(i));
            binding.output.append(cityList.get(i).name);
        }

//        callback.onRequestSuccess(cityList);
    }


    private static String getJsonFromAssets(Context context) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open("city_list.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }


}