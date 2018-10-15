package cloud.dawid.kupto.productsList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cloud.dawid.kupto.R;
import cloud.dawid.kupto.retrofit.Api;
import cloud.dawid.kupto.retrofit.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsList extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<Product> productArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        Log.d(TAG, "onCreate: started");

        getProducts();


    }

    private void getProducts(){

        Call<List<Product>> call = RetrofitRequest.getApi().getAll();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productArrayList = (ArrayList<Product>) response.body();
                initRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsList.this, "Problem z dostępem do serwera REST", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: tworzę recykler");

        RecyclerView recyclerView = findViewById(R.id.products_recylerView);
        ProductsViewAdapter adapter = new ProductsViewAdapter(productArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
