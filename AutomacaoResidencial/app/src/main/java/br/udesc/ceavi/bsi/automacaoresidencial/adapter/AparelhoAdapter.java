package br.udesc.ceavi.bsi.automacaoresidencial.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.bsi.automacaoresidencial.R;
import br.udesc.ceavi.bsi.automacaoresidencial.modelo.Aparelho;


public class AparelhoAdapter extends BaseAdapter {

    private final Context context;
    private final List<Aparelho> aparelhos;

    public AparelhoAdapter(Context context, List<Aparelho> aparelhos) {
        super();
        this.context = context;
        this.aparelhos = aparelhos;
    }

    @Override
    public int getCount() {
        return aparelhos.size();
    }

    @Override
    public Object getItem(int position) {
        return aparelhos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.aparelho,parent, false);
        TextView descricao = (TextView) v.findViewById(R.id.aparelho_descricao);
        Switch status = (Switch) v.findViewById(R.id.aparelho_status);

        descricao.setText(aparelhos.get(position).getDescricao());
        status.setChecked(aparelhos.get(position).isStatus());

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aparelhos.get(position).setStatus(!aparelhos.get(position).isStatus());
                String json = aparelhos.get(position).getJSON().toString();
                Log.i("JSON ->",json);
                new Http().execute(json);

            }
        });
        return v;
    }

    private class Http extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String json = params.clone()[0];
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpost = new HttpPost("http://10.1.1.111");
            StringEntity se = null;
            try {
                se = new StringEntity(json);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            httpost.setEntity(se);
            httpost.setHeader("Accept", "application/json");
            httpost.setHeader("Content-type", "application/json");
            ResponseHandler responseHandler = new BasicResponseHandler();
            try {
                return (String) httpclient.execute(httpost, responseHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}