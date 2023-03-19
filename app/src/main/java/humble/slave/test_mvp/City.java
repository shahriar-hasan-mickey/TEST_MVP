package humble.slave.test_mvp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    @Expose
    int id = 0;
    @Expose
    @SerializedName("name")
    public String name = "";
    @Expose
    @SerializedName("country")
    String country = "";
}