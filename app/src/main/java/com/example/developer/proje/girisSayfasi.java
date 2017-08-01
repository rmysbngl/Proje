package com.example.developer.proje;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class girisSayfasi extends AppCompatActivity {
    FirebaseAuth mAuth;//Kimlik doğrulama (login olma) işlemleri için
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText sifre, kullaniciAdi;
    TextView sifremiunuttum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_sayfasi);
        mAuth = FirebaseAuth.getInstance();// getInstance() metoduyla da bu sınıfın referans olduğu nesneleri kullanabilmekteyiz.
        mAuthListener = new FirebaseAuth.AuthStateListener() {//login olup olmadığımızı sürekli dinleyecek
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();//get current user ile oturum açmış kullanıcıya erişin
                if (user != null) {
                    // User is signed in

                    Log.d("Kontrol", "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(girisSayfasi.this,anaSayfa.class));
                } else {
                    // User is signed out
                    Log.d("Kontrol", "onAuthStateChanged:signed_out");
                }

            } };



        kullaniciAdi = (EditText) findViewById(R.id.kullaniciAdi);
        sifre = (EditText) findViewById(R.id.sifre);



        Button giris = (Button) findViewById(R.id.giris);
        giris.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(kullaniciAdi.getText().toString().trim())){
                    kullaniciAdi.requestFocus();
                    sifre.setError("Lütfen Kullanıcı Adı veya Email Adresinizi Giriniz");
                    //   Toast.makeText(getApplicationContext(),"Lütfen Kullanıcı Adı veya Email Adresinizi Giriniz",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(sifre.getText().toString())){
                    sifre.requestFocus();
                    sifre.setError("Lütfen Şifrenizi Giriniz.");
                    //  Toast.makeText(girisSayfasi.this,"Lütfen Şifrenizi Giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(kullaniciAdi.getText().toString(), sifre.getText().toString()).addOnCompleteListener(girisSayfasi.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(girisSayfasi.this,anaSayfa.class));
                        }
                        else {
                            Log.e("Giriş Hatası",task.getException().getMessage());
                            Toast.makeText(getApplicationContext(),"Kullanıcı Adı veya Şifre Hatalı  Tekrar Deneyin",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }});


        sifremiunuttum=(TextView) findViewById(R.id.unuttum);
        sifremiunuttum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(kullaniciAdi.getText().toString().trim())) {
                    Toast.makeText(getApplication(), "Lütfen email adresinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }



                mAuth.sendPasswordResetEmail(kullaniciAdi.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(girisSayfasi.this, "Yeni parola için gerekli bağlantı adresinize gönderildi!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(girisSayfasi.this, "Mail gönderme hatası!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }

}

