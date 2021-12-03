package com.sauapps.codemylink.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.sauapps.codemylink.MainActivity;
import com.sauapps.codemylink.R;
import com.sauapps.codemylink.data.MyData;
import com.sauapps.codemylink.data.MyListener;
import com.sauapps.codemylink.databinding.ActivityLoginBinding;

import static com.sauapps.codemylink.data.MyData.createUserData;
import static com.sauapps.codemylink.data.MyData.loaduserdata;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    private Dialog progress_Dialog;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());

        View view = activityLoginBinding.getRoot();
        setContentView(view);

        progress_Dialog = new Dialog(LoginActivity.this);
        progress_Dialog.setContentView(R.layout.progress_dialog_layout_2);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        activityLoginBinding.signinWithGoogleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();


            }
        });


        activityLoginBinding.signinWithFacebookB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                facebookSignIn();

            }
        });

    }

    private void googleSignIn() {
        progress_Dialog.show();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (task.getResult().getAdditionalUserInfo().isNewUser()) {

                                createUserData(user.getEmail(), user.getDisplayName(), "0", LoginActivity.this, new MyListener() {
                                    @Override
                                    public void onSuccess() {

                                        loaduserdata(LoginActivity.this, new MyListener() {
                                            @Override
                                            public void onSuccess() {

                                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(i);
                                                LoginActivity.this.finish();
                                                progress_Dialog.dismiss();

                                            }

                                            @Override
                                            public void onFailer() {

                                                Toast.makeText(LoginActivity.this, "Please Restart App", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(i);
                                                LoginActivity.this.finish();
                                                progress_Dialog.dismiss();



                                            }
                                        });


                                    }

                                    @Override
                                    public void onFailer() {

                                        Toast.makeText(LoginActivity.this, "Something went wrong! please try again latger with another Gmail Id", Toast.LENGTH_SHORT).show();

                                        progress_Dialog.dismiss();

                                    }
                                });
                            } else {

                                loaduserdata(LoginActivity.this, new MyListener() {
                                    @Override
                                    public void onSuccess() {

                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);
                                        LoginActivity.this.finish();
                                        progress_Dialog.dismiss();

                                    }

                                    @Override
                                    public void onFailer() {

                                        Toast.makeText(LoginActivity.this, "Please Restart App", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);
                                        LoginActivity.this.finish();
                                        progress_Dialog.dismiss();



                                    }
                                });
                            }


                        } else {
                            // If sign in fails, display a message to the user.
                            progress_Dialog.dismiss();


                            Toast.makeText(LoginActivity.this, task.getException().getMessage().toString(),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private void facebookSignIn() {

        Toast.makeText(this, "Please SignIn with google... facebook option will be available soon....", Toast.LENGTH_SHORT).show();

    }


}