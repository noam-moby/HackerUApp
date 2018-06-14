package noam.app.hackeruapp;

import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NetworkApi
{

    private static final String BASE_SERVER_URL = "http://nikita.hackeruweb.co.il/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null)
        {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = client.build();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_SERVER_URL)
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    public interface HackDroid {

        @GET("hackDroid/items.json")
        Call<JsonObject> getJson();
    }
}
