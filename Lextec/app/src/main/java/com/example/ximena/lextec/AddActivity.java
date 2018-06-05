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
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private TextView name;
    private TextView experiment;
    private TextView phone;
    private TextView email;

    private String visitaId;
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        name =findViewById(R.id.nombreUsuario);
        experiment =findViewById(R.id.nombreExperimento);
        phone =findViewById(R.id.telefono);
        email =findViewById(R.id.email);
        editing = false;

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            name.setText(intent.getStringExtra("Name"));
            experiment.setText(intent.getStringExtra("ExperimentName"));
            phone.setText(intent.getStringExtra("Phone"));
            email.setText(intent.getStringExtra("Email"));
            visitaId = intent.getStringExtra("IDVisita");
            editing = true;
        }
    }

    public void addInformation(View view){
            FirebaseUser user = firebaseAuth.getCurrentUser();
            mDatabase = FirebaseDatabase.getInstance().getReference("/users/"+user.getUid()+"/visitas/");
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Uploading....");
            pd.show();

            if(!editing) {
                visitaId = mDatabase.push().getKey();
            }

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
