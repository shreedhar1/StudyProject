package com.softcore.studyproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softcore.studyproject.AppUtils.AppUtil;
import com.softcore.studyproject.Model.CommanResorce;
import com.softcore.studyproject.Model.UserModel;
import com.softcore.studyproject.ViewModel.LoginViewModel;
import com.softcore.studyproject.databinding.ActivityLoginBinding;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // setContentView(R.layout.activity_login);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (TextUtils.isEmpty(binding.edDatabasename.getText()) ||
//                        TextUtils.isEmpty(binding.edUsername.getText())||
//                        TextUtils.isEmpty(binding.edPassword.getText()))
//                {
//                    AppUtil.showTost(getApplicationContext(),"Please Enter All Mandatory Fields");
//                }

                AppUtil.showProgressDialog(binding.getRoot(),"Loading");

                String dbName = binding.edDatabasename.getText().toString().trim();
                String userName = binding.edUsername.getText().toString().trim();
                String password = binding.edPassword.getText().toString().trim();

//                dbName = "ENVIIRO_LIVE";
//                userName = "V12943";
//                password = "12345";

                if (dbName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                    // Show error text in EditText
                    binding.edDatabasename.setError("Database Name is required");
                    binding.edUsername.setError("Username is required");
                    binding.edPassword.setError("Password is required");
                    binding.edUsername.showContextMenu();
                    AppUtil.hideProgressDialog();
                }else {
                    GetLoginDetails(dbName,userName,password);
                  //  AppUtil.hideProgressDialog();
                }


//                System.out.print("helo");
//                GetLoginDetails(dbName,userName,password);

               // startActivity(new Intent(LoginActivity.this,MainActivity2.class));
            }
        });




    }
    private void GetLoginDetails(String DbName,String username, String password) {
        System.out.println(DbName+" "+username+" "+password);
    // list_branch=new ArrayList<>();
        System.out.println("here");
        LoginViewModel loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getloginInfo(DbName,username,password).observe(this, new Observer<CommanResorce<List<UserModel>>>() {
            @Override
            public void onChanged(CommanResorce<List<UserModel>> listCommanResorce) {

                if (listCommanResorce.data != null && !listCommanResorce.data.isEmpty()) {
                    String apiPassword = listCommanResorce.data.get(0).getUser_Password();
                    if (apiPassword.equals(password)) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Other actions after successful login
                        AppUtil.hideProgressDialog();
                        startActivity(new Intent(LoginActivity.this,MainActivity2.class));

                    } else {
                        AppUtil.showTost(getApplicationContext(), "Password incorrect. Please try again.");
                        System.out.println("Password incorrect");
                        AppUtil.hideProgressDialog();
                    }
                } else {
                    AppUtil.showTost(getApplicationContext(), "User not found. Please check your username.");
                    System.out.println("User not found");
                    AppUtil.hideProgressDialog();
                }


            }
        });



    }
}