package cloud.dawid.kupto.productsList;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cloud.dawid.kupto.R;
import cloud.dawid.kupto.retrofit.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Response;

public class ProductsViewAdapter extends RecyclerView.Adapter<ProductsViewAdapter.ViewHolder>{

    public static final String TAG = "ProductsViewAdapter";

    private ArrayList<Product> arrayListProducts = new ArrayList<>();
    private Context context;

    public ProductsViewAdapter(ArrayList<Product> arrayListProducts, Context context) {
        this.arrayListProducts = arrayListProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.productNameTextView.setText(arrayListProducts.get(i).getName());

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + arrayListProducts.get(i).getName());

                Toast.makeText(context, "Kliknąłeś: " + arrayListProducts.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete: " + arrayListProducts.get(i).getName(), Toast.LENGTH_SHORT).show();
                RetrofitRequest.delete(arrayListProducts.get(i).getId());
                arrayListProducts.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productNameTextView;
        RelativeLayout parentLayout;
        ImageView deleteImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            deleteImage = itemView.findViewById(R.id.deleteImage);
        }
    }
}
