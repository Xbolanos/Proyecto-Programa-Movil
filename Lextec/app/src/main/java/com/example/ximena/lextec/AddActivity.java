package com.example.ximena.lextec;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import java.sql.Date;


public class AddActivity extends AppCompatActivity {
    FirebaseStorage storage;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void addInformation(View view){

            FirebaseUser user = firebaseAuth.getCurrentUser();
            mDatabase = FirebaseDatabase.getInstance().getReference("/users/"+user.getUid()+"/visitas/");
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Uploading....");
            pd.show();
            final String visitaId = mDatabase.push().getKey();
            final TextView name =findViewById(R.id.nombreUsuario);
            final TextView experiment =findViewById(R.id.nombreExperimento);
            final TextView phone =findViewById(R.id.telefono);
            final TextView email =findViewById(R.id.email);
            Date date=new Date(System.currentTimeMillis());

            String sdate=date.toString();
            String sname=name.getText().toString();
            String sexperiment=experiment.getText().toString();
            String semail=email.getText().toString();
            String iphone=phone.getText().toString();
            Visita visita=new Visita(visitaId, sname,sexperiment,sdate,semail,iphone);

            try {
                mDatabase.child(visitaId).setValue(visita)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                change_ProfileActivity();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
            }catch (Exception e){
                Log.d("exception",e.getMessage());
            }


        }

    public void change_ProfileActivity(){
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
