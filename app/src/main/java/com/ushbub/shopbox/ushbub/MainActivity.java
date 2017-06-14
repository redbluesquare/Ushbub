package com.ushbub.shopbox.ushbub;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.ushbub.shopbox.ushbub.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.ushbub.shopbox.ushbub.R.id.ivImage_link;
import static com.ushbub.shopbox.ushbub.R.id.tvProductTitle;
import static com.ushbub.shopbox.ushbub.R.id.tvWeight;
import static com.ushbub.shopbox.ushbub.R.id.tvWeightuom;

public class MainActivity extends AppCompatActivity {


    private ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProducts = (ListView)findViewById(R.id.lvProducts);


        // new JSONTask().execute("http://api.ushbub.co.uk/products");

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start


    }

    public class JSONTask extends AsyncTask<String, String, List<ProductModel>>{
        @Override
        protected List<ProductModel> doInBackground(String... params){

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line ="";
                while((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("products");

                List<ProductModel> productModelList = new ArrayList<ProductModel>();

                for(int i=0; i < parentArray.length(); i++){

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    ProductModel productModel = new ProductModel();
                    productModel.setVendor_product_name(finalObject.getString("vendor_product_name" ));
                    productModel.setDdc_vendor_product_id(finalObject.getString("ddc_vendor_product_id"));
                    productModel.setProduct_weight(finalObject.getString("product_weight"));
                    productModel.setProduct_weight_uom(finalObject.getString("product_weight_uom"));
                    //productModel.setVendor_name(finalObject.getString("title"));
                    productModel.setImage_link(finalObject.getString("image_link"));
                    //Add the final model to the list
                    productModelList.add(productModel);
                }
                return productModelList;

            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null){
                    connection.disconnect();
                }
                try{
                    if(reader != null){
                        reader.close();
                    }
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ProductModel> result){
            super.onPostExecute(result);

            if(result != null) {
                //TODO need to set the data to the list

                //create an adapter
                ProductAdapter adapter = new ProductAdapter(getApplicationContext(), R.layout.row,result);
                lvProducts.setAdapter(adapter);
            }
            else{
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            new JSONTask().execute("http://api.ushbub.co.uk/products");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProductAdapter extends ArrayAdapter{

        private List<ProductModel> productModelList;
        private int resource;
        private LayoutInflater inflater;

        public ProductAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ProductModel> objects) {
            super(context, resource, objects);
            productModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                holder.ivImage_link = (ImageView)convertView.findViewById(ivImage_link);
                holder.tvProductTitle = (TextView)convertView.findViewById(tvProductTitle);
                holder.tvWeight = (TextView)convertView.findViewById(tvWeight);
                holder.tvWeightuom = (TextView)convertView.findViewById(tvWeightuom);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.tvProductTitle.setText(productModelList.get(position).getVendor_product_name());
            holder.tvWeight.setText(productModelList.get(position).getProduct_weight());
            holder.tvWeightuom.setText(productModelList.get(position).getProduct_weight_uom());
            final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);;


            // Then later, when you want to display image
            ImageLoader.getInstance().displayImage("https://ushbub.co.uk/" + productModelList.get(position).getImage_link(), holder.ivImage_link, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            }); // Default options will be used

            return convertView;
        }

        class ViewHolder{
            private ImageView ivImage_link;
            private TextView tvProductTitle;
            private TextView tvWeight;
            private TextView tvWeightuom;


        }
    }

}
