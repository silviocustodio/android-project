package furafila.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CustomStringRequest extends Request<String> {
	private Listener<String> response;
	private Map<String, String> params;


	public CustomStringRequest(int method, String url, Map<String, String> params, Listener<String> response, ErrorListener listener) {
		super(method, url, listener);
		this.params = params;
		this.response = response;
		// TODO Auto-generated constructor stub
	}
	public CustomStringRequest(String url, Map<String, String> params, Listener<String> response, ErrorListener listener) {
		super(Method.GET, url, listener);
		this.params = params;
		this.response = response;
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, String> getParams() throws AuthFailureError{
		return params;
	}
	
	public Map<String, String> getHeaders() throws AuthFailureError{
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("apiKey", "this is ape KEY: json array");
		
		return(header);
	}
	
	public Priority getPriority(){
		return(Priority.NORMAL);
	}
	
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		try {
			String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return(Response.success(new String(js), HttpHeaderParser.parseCacheHeaders(response)));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return null;
	}


	@Override
	protected void deliverResponse(String response) {
		this.response.onResponse(response);
	}

}
