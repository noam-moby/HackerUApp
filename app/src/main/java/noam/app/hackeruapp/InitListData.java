package noam.app.hackeruapp;

import android.support.v7.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InitListData
{
    private AdapterData adapterData;
    private Item currentItem;
    private List<Item> itemList;

    public InitListData(RecyclerView recycViewData)
    {
        itemList = new ArrayList<>();
        getDataFromServer(recycViewData);
    }

    private void getDataFromServer(final RecyclerView recycViewData)
    {
        // Init the Retrofit Object
        Retrofit retrofit = NetworkApi.getRetrofit();
        NetworkApi.HackDroid hackDroid = retrofit.create(NetworkApi.HackDroid.class);
        Call<JsonObject> call = hackDroid.getJson();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Prepare some temp vars for parsing Json
                JsonObject sourceObj;
                JsonElement requirementsObj;
                int level;

                JsonArray itemsListFromServer = response.body().getAsJsonArray("hits");
                for (int i = 0; i < itemsListFromServer.size(); i++)
                {
                    sourceObj = (itemsListFromServer.get(i).getAsJsonObject().get("_source").getAsJsonObject());
                    requirementsObj = sourceObj.get("requirements");

                    // check if there is a "requirements" property for current item
                    if (requirementsObj == null)
                        level = 0;
                    else
                        level = requirementsObj.getAsJsonObject().get("Level").getAsInt();

                    currentItem = new Item(
                            sourceObj.get("info").getAsJsonObject().get("fullName").getAsString(),
                            level,
                            sourceObj.get("attributes").getAsJsonObject().get("identified").getAsBoolean());
                    itemList.add(currentItem);
                }

                adapterData = new AdapterData(itemList);
                recycViewData.setAdapter(adapterData);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
