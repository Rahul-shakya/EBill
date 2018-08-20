package com.example.asus.ebill;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OrderProfileActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextView curr_time;
    private TextView addr;
    private TextView status;

    private TextView rate;
    private TextView tax;
    private TextView grand_total;
    private TextView s_time;
    private TextView e_time;
    private TextView pay_status;
    private TextView image_url;
    private ImageView profile_image;
    private TextView serv_for;
    private TextView serv_at;
    private TextView field;

    private Button btn_book;

    private DatabaseReference mOrdersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_profile);


        final String user_id=  getIntent().getStringExtra("user_id");
        final String tag_field=  getIntent().getStringExtra("tag_field");


        mToolbar=findViewById(R.id.orders_appBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(user_id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mOrdersDatabase= FirebaseDatabase.getInstance().getReference().child("Orders").child(user_id);

        curr_time=findViewById(R.id.time);
        addr=findViewById(R.id.addr);
        status=findViewById(R.id.status);

        rate=findViewById(R.id.tv2);
        tax=findViewById(R.id.tv4);
        grand_total=findViewById(R.id.tv6);
        s_time=findViewById(R.id.tv10);
        e_time=findViewById(R.id.tv2);
        pay_status=findViewById(R.id.tv12);
        profile_image=findViewById(R.id.pro_image);
        serv_for=findViewById(R.id.tv14);
        serv_at=findViewById(R.id.tv16);
        field=findViewById(R.id.field);
        btn_book=findViewById(R.id.btn);

        field.setText(tag_field);


        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(OrderProfileActivity.this,"Booking is cancelled",Toast.LENGTH_LONG).show();
                btn_book.setText("Your booking is cancelled");
            }
        });

        mOrdersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String time=dataSnapshot.child("time").getValue().toString();
                String add=dataSnapshot.child("address").getValue().toString();
                String mstatus=dataSnapshot.child("status").getValue().toString();

                String mrate=dataSnapshot.child("rate").getValue().toString();
                String mtax=dataSnapshot.child("tax").getValue().toString();
                String mgrand_total=dataSnapshot.child("grand_total").getValue().toString();
                String ms_time=dataSnapshot.child("s_time").getValue().toString();
                String me_time=dataSnapshot.child("e_time").getValue().toString();
                String mpay_status=dataSnapshot.child("pay_status").getValue().toString();
                String dp=dataSnapshot.child("image").getValue().toString();
                String s_for=dataSnapshot.child("serv_req_for").getValue().toString();
                String s_at=dataSnapshot.child("serv_req_at").getValue().toString();

                curr_time.setText(time);
                addr.setText(add);
                status.setText(mstatus);
                rate.setText(mrate);
                tax.setText(mtax);
                grand_total.setText(mgrand_total);
                s_time.setText(ms_time);
                e_time.setText(me_time);
                pay_status.setText(mpay_status);
                serv_for.setText(s_for);
                serv_at.setText(s_at);
                Picasso.get().load(dp).into(profile_image);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
