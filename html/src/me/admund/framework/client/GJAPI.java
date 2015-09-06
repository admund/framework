package me.admund.framework.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class GJAPI implements Net.HttpMethods {

	public String baseUrl = "http://gamejolt.com/api/game/v1_1/";

	private String key;
	private int gameId;

	public interface callback{
		void execute(String response);
	}
				  		   
	protected GJAPI(String nKey, int nGameId){
		key = nKey;
		gameId = nGameId;
	}

	public String generateUrl(String type, String url){
		String baseUrl = this.baseUrl + type + "/?game_id=" + Integer.toString(gameId) + "&" + url + "&format=keypair";
		return baseUrl + "&signature=" + MD5(baseUrl+key);
	}

	public String MD5(String input)
	{
		String res = "";
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(input.getBytes());
			byte[] md5 = algorithm.digest();
			String tmp = "";
			for (int i = 0; i < md5.length; i++) {
				tmp = (Integer.toHexString(0xFF & md5[i]));
				if (tmp.length() == 1) {
					res += "0" + tmp;
				} else {
					res += tmp;
				}
			}
		} catch (NoSuchAlgorithmException ex) {}
		return res;
	}

	public void sendRequest(String type, String url,final callback callback){
		String finalUrl = generateUrl(type,url);
		Gdx.app.log("HTML", "finalUrl " + finalUrl);
		HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
		httpGet.setUrl(finalUrl);

		Gdx.net.sendHttpRequest (httpGet, new HttpResponseListener() {
			@Override
		    public void handleHttpResponse(HttpResponse httpResponse) {
				String status = httpResponse.getResultAsString();
				Gdx.app.log("GJAPI", "handleHttpResponse " + status);
		    	callback.execute(status);
		    }
	 
		    @Override
		    public void failed(Throwable t) {
				String status = "Failed to send the request";
		    	Gdx.app.log("GJAPI", "failed " + status);
		    }

			@Override
			public void cancelled() {
				String status = "Cancelled... for some reason";
				Gdx.app.log("GJAPI", "cancelled " + status);
			}
 		});
	}

	public boolean addScore(int score, String userName){
		sendRequest("scores/add",
						"guest="+userName+
						"&score="+score + " s"+
						"&sort="+Integer.toString(score),
				new GJAPI.callback(){
			@Override
		public void execute(String response) {
				Gdx.app.log("GJAPI", "Score added!");
		}});
		return true;
	}
}