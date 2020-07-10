package com.icandothisallday2020.kingofcooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class FragmentTalk extends Fragment {
    CircleImageView civ;
    EditText name;
    FloatingActionButton fab;
    boolean isSaved=false;

    Uri imgUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_talk,container,false);
        civ=view.findViewById(R.id.civ_profile);
        name=view.findViewById(R.id.et_name);
        fab=view.findViewById(R.id.upload);


        return  view;
    }

    public void clickCIV(View view){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            imgUri=data.getData();
            if(imgUri!=null){
                Glide.with(this).load(imgUri).into(civ);
                isSaved=true;
            }
        }
    }





    void saveData(){
        G.nickName=name.getText().toString();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName=sdf.format(new Date())+".png";
        FirebaseStorage storage=FirebaseStorage.getInstance();
        final StorageReference imgRef=storage.getReference("profiles/"+fileName);

        UploadTask task=imgRef.putFile(imgUri);
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        G.profileUrl=uri.toString();

                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        DatabaseReference profileRef=database.getReference();
                        profileRef.child(G.nickName).setValue(G.profileUrl);

                        SharedPreferences pref=getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("nickName",G.nickName);
                        editor.putString("profile",G.profileUrl);
                        editor.commit();
                        //프로필저장 완료 메세지 띄우기


                    }
                });
            }
        });
    }
}