package com.icandothisallday2020.kingofcooking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFind extends Fragment {
    EditText et;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find,container,false);
        et=view.findViewById(R.id.search);
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_ENTER:
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri uri=Uri.parse("https://www.google.com/search?q="+et.getText().toString()
                                +"&rlz=1C1CHBD_koKR893KR893&oq=%EA%B5%AC%EA%B8%80%EB%A7%81&aqs=chrome.0.69i59j35i39j0l6.1001j1j7&sourceid=chrome&ie=UTF-8");//걍 구글링
                        intent.setData(uri);
                        startActivity(intent);
                        et.setText("");

                        break;
                }
                return true;
            }
        });
        return  view;
    }

}
