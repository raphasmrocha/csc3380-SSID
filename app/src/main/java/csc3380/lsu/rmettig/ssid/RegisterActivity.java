package csc3380.lsu.rmettig.ssid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.net.wifi.WifiManager;
import android.content.Context;


public class RegisterActivity extends AppCompatActivity {


    public Shark newShark = null;
    protected String sharkType = "";
    protected String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); //binds the xml file to this class
        final WifiManager wfm=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        //gets content from the elements in the form
        final EditText edtNewUsername = (EditText) findViewById(R.id.edtNewUser);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final RadioButton rbtGreatWhite = (RadioButton) findViewById(R.id.rbtGreatWhite);
        final RadioButton rbtHammerHead = (RadioButton) findViewById(R.id.rbtHammerHead);
        final RadioButton rbtWhale = (RadioButton) findViewById(R.id.rbtWhale);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);

                username = edtNewUsername.getText().toString();

                if(rbtGreatWhite.isChecked())
                    sharkType = "greatwhite";
                if(rbtHammerHead.isChecked())
                    sharkType = "hammerhead";
                if(rbtWhale.isChecked())
                    sharkType = "whale";

                if (!sharkType.equals("")&&!username.equals("")) {
                    newShark = new Shark(sharkType, wfm);
                    //newShark.saveShark();
                    RegisterActivity.this.startActivity(registerIntent);
                }
                else {
                    if(sharkType.equals(""))
                        new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Error")
                            .setMessage("You must select an egg!!")
                            .setNeutralButton("Ok", null)
                            .create()
                            .show();
                    if(username.equals(""))
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Error")
                                .setMessage("Please enter a username!!")
                                .setNeutralButton("Ok", null)
                                .create()
                                .show();
                }
            }
        });

        rbtGreatWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtHammerHead.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        rbtHammerHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtGreatWhite.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        rbtWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtGreatWhite.setChecked(false);
                rbtHammerHead.setChecked(false);
            }
        });
    }
}
