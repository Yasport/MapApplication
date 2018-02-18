package pac.example.com.mapapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannick on 11/02/2018.
 */

public class ListViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference dataRef;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mapapplication-daf7c.firebaseio.com/");

        Query query = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://mapapplication-daf7c.firebaseio.com/Parking")
                .limitToLast(50);

        FirebaseRecyclerOptions<Parking> options = new FirebaseRecyclerOptions.Builder<Parking>()
                .setQuery(query, Parking.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Parking, ParkingViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull ParkingViewHolder holder, int position, @NonNull Parking model) {

                holder.setTitle(model.getName());
                holder.setDescription(model.getAdresse());
                holder.setImage(model.getImgUrl());
                holder.setPrice(model.getPrice());
            }

            @Override
            public ParkingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.individual_row, parent, false);

                return new ParkingViewHolder(view);
            }
        };

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.i("DEBUG", "void onChildAdded");
                //Parking parking = dataSnapshot.getValue(Parking.class);
                //ArrayList<Parking> arrayList = new ArrayList<>();
                //arrayList.add(parking);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.i("DEBUG", "void onChildChanged");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("DEBUG", "void onChildRemoved");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.i("DEBUG", "void onChildMoved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DEBUG", "void onCancelled");
            }
        };

        query.addChildEventListener(childEventListener);

        adapter.startListening();

        recyclerView.setAdapter(adapter);
    }

    public static class ParkingViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView textView_name;
        TextView textView_decription;
        ImageView imgUrl;
        TextView textView_price;


        public ParkingViewHolder(View itemView) {
            super(itemView);

            mView=itemView;
            textView_price = (TextView)itemView.findViewById(R.id.price);
            textView_name = (TextView)itemView.findViewById(R.id.name);
            textView_decription = (TextView) itemView.findViewById(R.id.description);
            imgUrl = (ImageView)itemView.findViewById(R.id.imgUrl);
        }

        public void setTitle(String title)
        {
            textView_name.setText(title+"");
        }
        public void setPrice(String price) { textView_price.setText(price+"");}

        public void setDescription(String description)
        {
            textView_decription.setText(description);
        }

        public void setImage(String image)
        {
            Picasso.with(mView.getContext())
                    .load(image)
                    .into(imgUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_1:
                ListViewActivity.this.startActivity(new Intent(ListViewActivity.this, MapsActivity.class));
                return true;
            case R.id.item_2:
                ListViewActivity.this.startActivity(new Intent(ListViewActivity.this, ListViewActivity.class));
                return true;
            case R.id.item_3:
                ListViewActivity.this.startActivity(new Intent(ListViewActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
