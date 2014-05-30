package se.andreasottesen.yourmenu.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import se.andreasottesen.yourmenu.app.account.LoginActivity;


public class IntroActivity extends ActionBarActivity {
    private Button btnStartMenu;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnStartMenu = (Button) findViewById(R.id.btnStartMenu);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnStartMenuClick(View view){
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);

    }

    public void btnCreateAccountClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
