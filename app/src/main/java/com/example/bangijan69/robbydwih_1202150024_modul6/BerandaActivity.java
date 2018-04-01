package com.example.bangijan69.robbydwih_1202150024_modul6;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.provider.Contacts.SettingsColumns.KEY;

public class BerandaActivity extends AppCompatActivity {
    GridView grid;
    FirebaseStorage storage;
    StorageReference storageReference;
    String nama[] = {"ini", "itu"};
    ArrayList<Uri> gam = new ArrayList<>();
    ArrayList<String> judul = new ArrayList<>();
    ArrayList<String> post = new ArrayList<>();
    List<ImageUploadInfo> list = new ArrayList<>();

    //int gambar[] = {R.drawable.ic_3d_rotation, R.drawable.ic_accessibility};
    DatabaseReference databaseReference;
    public static final String Database_Path = "POST";
    public static final String file_Path = "images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        get_data();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(BerandaActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    void get_data() {
        databaseReference = FirebaseDatabase.getInstance().getReference(BerandaActivity.Database_Path);
        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot snapshot) {

                                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                                            String jdl = dataSnapshot.child("judul").getValue().toString();
                                                            String pst = dataSnapshot.child("post").getValue().toString();
                                                            judul.add(jdl);
                                                            post.add(pst);
                                                            Log.d("ini adalah judul", jdl);
                                                        }
                                                        getFoto();
                                                        grid();


                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                }

        );
    }

    void grid() {
        gridadaptor adapter = new gridadaptor(BerandaActivity.this, judul, post, gam,list);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(BerandaActivity.this, "You Clicked at " + judul.get(position), Toast.LENGTH_SHORT).show();

            }
        });


    }

    void getFoto() {
        /*
        FirebaseStorage storage = FirebaseStorage.getInstance();
        //storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        storageRef.child("image/*").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    */
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/");
        ref.getFile(Uri.parse("gs://modul6-6f5bd.appspot.com/images"));

        Log.d("ini anama file ", ref.getName());
        Log.d("ini adalah peth ", ref.getPath());

    }
}