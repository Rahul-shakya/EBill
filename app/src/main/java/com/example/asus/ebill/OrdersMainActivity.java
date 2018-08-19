package com.example.asus.ebill;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrdersMainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_main);

        mToolbar=(Toolbar)findViewById(R.id.orders_appBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mOrdersList=findViewById(R.id.ordersList);
        mOrdersList.setHasFixedSize(true);
        mOrdersList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();

        startListening();
    }

    private void startListening() {

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Orders")
                .limitToLast(50);

        FirebaseRecyclerOptions<Orders> options=new FirebaseRecyclerOptions.Builder<Orders>()
                .setQuery(query,Orders.class)
                .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Orders, OrderViewHolder>(options) {
            @Override
            public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_single_layout, parent, false);

                return new OrderViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull Orders model) {

                holder.setFieldt(model.field);
                holder.setTimet(model.time);
                holder.setServicet(model.service);

                final String id=getRef(position).getKey();

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(OrdersMainActivity.this,"Id is "+id,Toast.LENGTH_LONG).show();

                    }
                });
            }
        };


        mOrdersList.setAdapter(adapter);
        adapter.startListening();






    }


    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public OrderViewHolder (View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setFieldt(String field) {

            TextView orderField=mView.findViewById(R.id.field);
            orderField.setText(field);

        }

        public void setTimet(String time) {

            TextView orderTime=mView.findViewById(R.id.time);
            orderTime.setText(time);

        }

        public void setServicet(String service) {

            TextView orderService=mView.findViewById(R.id.service);
            orderService.setText(service);

        }





    }


}
