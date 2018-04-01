package com.example.bangijan69.robbydwih_1202150024_modul6;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class InputActivity extends AppCompatActivity {

    private DatabaseReference database;
    EditText editText_judul,editText_post;
    Button button_chooser;
    String m_chosen;
    ImageView imageView_input;
    private static final int SELECT_PHOTO = 100;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_data_);
        editText_judul =(EditText)findViewById(R.id.judul_input);
        editText_post = (EditText)findViewById(R.id.post_input);
        database = FirebaseDatabase.getInstance().getReference();
        button_chooser =(Button)findViewById(R.id.chooser_button);
        imageView_input =(ImageView)findViewById(R.id.image_input);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        button_chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose_foto();

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                String judul = editText_judul.getText().toString();
                String post = editText_post.getText().toString();
                submitBarang( new data(judul,post));
                upload_foto();

            }
        });
    }

    private void submitBarang(data dat) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("POST").push().setValue(dat).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(InputActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, InputActivity.class);
    }
    void chose_foto(){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);

    }

void file_chose(){
    SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(InputActivity.this, "FileOpen",
            new SimpleFileDialog.SimpleFileDialogListener()
            {
                @Override
                public void onChosenDir(String chosenDir)
                {
                    // The code in this function will be executed when the dialog OK button is pushed
                    m_chosen = chosenDir;
                    Toast.makeText(InputActivity.this, "Chosen FileOpenDialog File: " +
                            m_chosen, Toast.LENGTH_LONG).show();
                }
            });

    //You can change the default filename using the public variable "Default_File_Name"
    FileOpenDialog.Default_File_Name = "";
    FileOpenDialog.chooseFile_or_Dir();
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView_input.setImageURI(selectedImage);
                    filePath=selectedImage;


                }
        }
    }
    void upload_foto(){
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(InputActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(InputActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }

    }
}
