package com.example.safeinternetwebview.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.safeinternetwebview.R;
import com.example.safeinternetwebview.common.Common;
import com.example.safeinternetwebview.interfaces.ItemClickListener;
import com.example.safeinternetwebview.model.CategoryQuizItem;
import com.example.safeinternetwebview.viewHolder.CategoryQuizViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class QuizItemActivity extends AppCompatActivity {
    private static final String TAG = "rabbi";


    private RecyclerView recyclerView;
    private DatabaseReference rotref;
    private FirebaseRecyclerOptions<CategoryQuizItem> options;
    private FirebaseRecyclerAdapter<CategoryQuizItem, CategoryQuizViewHolder> adapter;

    int cSerial = 0;
    //ShPreference
    SharedPreferences cPreferences;
    SharedPreferences.Editor cEditor;
    long total_question = 0;
    int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item);

        //Firebase
        rotref= FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            final String ch = bundle.getString("chapter");
            p = bundle.getInt(ch);

            Log.e("totalq",ch);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(ch);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    total_question = dataSnapshot.getChildrenCount();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        //RecyclerView
        recyclerView=findViewById(R.id.allcardview_recycularid);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(QuizItemActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //ui database
        options =new FirebaseRecyclerOptions.Builder<CategoryQuizItem>()
                .setQuery(rotref.child(Common.allcategory_card),CategoryQuizItem.class)
                .build();

        adapter =new FirebaseRecyclerAdapter<CategoryQuizItem, CategoryQuizViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryQuizViewHolder holder, int position, @NonNull final CategoryQuizItem model) {



                holder.chaptername.setText(model.getChaptername());
                Bundle bundle = getIntent().getExtras();
               /* if (bundle != null) {
                    p = bundle.getInt(model.getChaptername());
                    final String ch = bundle.getString("chapter");
                    Log.e("totalq",ch);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(ch);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            total_question = dataSnapshot.getChildrenCount();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(total_question == (p-1))
                    {

                        final int fPosition = bundle.getInt(model.getChaptername());
                        holder.totalquize.setText(String.valueOf(fPosition));

                    }
                    else if(model.getChaptername().equals(ch)){
                        holder.totalquize.setText(String.valueOf(p));

                    }
                }*/
               // holder.totalquize.setText("0/");


                // gradient random color from firebase
                int[] colors = {Color.parseColor(model.getColors()),Color.parseColor(model.getColors2())};
                //create a new gradient color
                GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
                gd.setCornerRadius(0f);
                holder.parent.setBackground(gd);

                //set a image
                Picasso.with(getBaseContext())
                        .load(model.getImagelink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }
                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.getImagelink())
                                        .error(R.drawable.ic_launcher_background)
                                        .into(holder.imageView, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Toast.makeText(QuizItemActivity.this, "image fetch failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                //intent
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Common.CATEGORY_ID_SELECTED = adapter.getRef(position).getKey();
                        Common.CATEGORY_SELECTED = model.getChaptername();
                        Intent intent = new Intent(QuizItemActivity.this, QuizShowActivity.class);
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public CategoryQuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_quiz_item,parent,false);
                CategoryQuizViewHolder cardviewholder=new CategoryQuizViewHolder(view);
                return cardviewholder;
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

    public void getQuizChildName(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(Common.allcategory_card);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CategoryQuizItem item = dataSnapshot.getValue(CategoryQuizItem.class);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
