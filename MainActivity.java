package mx.com.krisma.webbrowser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String completeUrl;
    WebView webv;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        url= (EditText)findViewById(R.id.editText);
        webv=(WebView)findViewById(R.id.webView);
        webv.setWebViewClient(new WebViewClient()); //To open web page inside my activity and not on other web browser
        webv.getSettings().setJavaScriptEnabled(true);
        webv.getSettings().setDomStorageEnabled(true);
        Navigate(null);

    }

    boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    void Navigate (View view)
    {
        if (isConnected())
        {
            if(url.getText().toString().contains("http://"))
            {
                completeUrl=url.getText().toString();
            }
            else
            {
                completeUrl="http://".concat(url.getText().toString());
            }
            webv.loadUrl(completeUrl);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
        }
    }
}
