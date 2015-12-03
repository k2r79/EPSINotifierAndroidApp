package fr.epsi.epsinotifier.app;

import android.app.IntentService;
import android.content.Intent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import cz.msebera.android.httpclient.Header;

public class EPSIIntentService extends IntentService {

    private AsyncHttpClient asyncHttpClient = new SyncHttpClient();

    public EPSIIntentService() {
        super("EPSIIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        asyncHttpClient.get(this, "http://epsi-planning.herokuapp.com/cours/prochain", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
