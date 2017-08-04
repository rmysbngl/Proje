package com.example.developer.proje;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Kaydol extends AppCompatActivity {
    private EditText name,password,confirmPassword, email, doğumTarih,Sehir,kullanıcıAdi;
    public CheckBox kız, erkek;
    Button Onay;
    private String cinsiyet;
    List<Kisiler> person=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);
        name=(EditText) findViewById(R.id.IsımEdit);
        password=(EditText) findViewById(R.id.PasswordEdit);
        confirmPassword=(EditText) findViewById(R.id.ConfirmPasswordEdit);
        email=(EditText) findViewById(R.id.EmailEdit);
        doğumTarih=(EditText) findViewById(R.id.DoğumTarihi);
        Sehir=(EditText) findViewById(R.id.SehirEdit);
        kullanıcıAdi=(EditText)  findViewById(R.id.KullanıcıAdıEdit);
        kız=(CheckBox) findViewById(R.id.Kız);
        erkek=(CheckBox) findViewById(R.id.Erkek);

        Onay=(Button) findViewById(R.id.Onayla);
        Onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String username=name.getText().toString();
               final String Password=password.getText().toString();
                final String Email=email.getText().toString();
                final String DoğumTarihi=doğumTarih.getText().toString();
               final String sehir=Sehir.getText().toString();
                final String KullaniciAdi=kullanıcıAdi.getText().toString();


/*

                    
*/

                    if (Password.equals(confirmPassword.getText().toString())) {



                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");
                                        users.child(task.getResult().getUser().getUid()).setValue(new Kisiler(username,KullaniciAdi, Email, Password, DoğumTarihi, sehir, cinsiyet));
                                        Toast.makeText(Kaydol.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Kaydol.this, girisSayfasi.class);
                                        startActivity(intent);
                                    }
                                }
                            });




                    } else {
                        Toast.makeText(Kaydol.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }


    public void onCheckBoxButtonClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();


        switch(view.getId()) {
            case R.id.Erkek:
                if (checked)
                    cinsiyet="erkek";
                kız.setChecked(false);

                    break;
            case R.id.Kız:
                if (checked)
                    cinsiyet="kız";
                erkek.setChecked(false);
                    break;
        }
    }



}
