package cloud.dawid.kupto.retrofit;

import java.util.List;

import cloud.dawid.kupto.productsList.Product;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://80.211.79.203:8081/products/";

    @GET("all")
    Call<List<Product>> getAll();

    @DELETE("del/{id}")
    Call<Void> delete(@Path("id") long id);
}
