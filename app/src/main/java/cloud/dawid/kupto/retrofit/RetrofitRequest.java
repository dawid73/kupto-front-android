package cloud.dawid.kupto.retrofit;

import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static String TAG = "RetrofitRequest";

    private static Retrofit getRetrofit(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }

    public static void delete(Long i){

        Call<Void> call = getApi().delete(i);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: Usunięto ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public static Api getApi(){
        Api api = getRetrofit().create(Api.class);
        return api;
    }

}
