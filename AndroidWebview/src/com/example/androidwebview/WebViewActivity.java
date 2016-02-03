package com.example.androidwebview;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
/**
 * webView��ʹ��Activity��ʽ
 */
public class WebViewActivity extends Activity {

	private WebView webView;
	private ProgressBar progress;
	private String errorHtml;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		errorHtml = "<html><body><h1>Page not find��</h1></body></html>"; 
		webView = (WebView)findViewById(R.id.wbeview);
		progress = (ProgressBar)findViewById(R.id.loading);
		
		webView.getSettings().setJavaScriptEnabled(true); 
		//webView.loadUrl("http://www.baidu.com");
		webView.loadUrl("http://player.youku.com/embed/XNTM5MTUwNDA0");
		webView.requestFocus();
		webView.setWebViewClient(new MyWebViewClient());
		
		//�޷����������������ʾ�����������
		//webView.getSettings().setLoadWithOverviewMode(true);
		//webView.getSettings().setUseWideViewPort(true);
		
	}
	
	//���ڼ��ء�ȡ��loading
	 private Handler mHandler = new Handler(){ 
		 
		 public void handleMessage(android.os.Message msg) {
			   
			   switch (msg.what) {
			   case 10:
			    addProgressBar();
			    break;
			   case 20:
			    closeProgressBar();
			   default:
			    break;
			   }
			 };
		 
	 };
	  
	public void addProgressBar() {
		if (progress != null) {

			progress.setVisibility(View.VISIBLE);
		}
	}

	public void closeProgressBar() {

		if (progress != null) {

			progress.setVisibility(View.GONE);
		}
	}
	private class MyWebViewClient extends WebViewClient{
		  //����ҳ�濪ʼ
		  @Override
		  public boolean shouldOverrideUrlLoading(WebView view, String url) {
		   view.loadUrl(url); 
		   return true;
		  }
		  
		  //����ҳ�濪ʼ��
		  @Override
		  public void onPageStarted(WebView view, String url, Bitmap favicon) {
		   super.onPageStarted(view, url, favicon);
		   mHandler.sendEmptyMessage(10);
		  }
		  //����ҳ�����
		  @Override
		  public void onPageFinished(WebView view, String url) {
		   // TODO Auto-generated method stub
		   super.onPageFinished(view, url);
		   mHandler.sendEmptyMessage(20);
		  }
		  
		  /**
		   * ����������¼򵥴���/
		   * ����webҳ�涼�������������£�
		   * һ��û������ͻ���ʾ"�޷��ҵ�����ҳ"����Ϣ��
		   * �����ᱩ¶���ǵ����ӣ�����������Ҫһ���кõ���ʾ��
		   * ���Ҳ��ᱩ¶���ӵķ�������ʱ��WebViewClient��onReceivedError�������������ó�
		   *
		   */
		  @Override
		  public void onReceivedError(WebView view, int errorCode,
		    String description, String failingUrl) {
		   super.onReceivedError(view, errorCode, description, failingUrl);
		   //�������������������������Ը���errorCode��ֵ�����жϣ�������ϸ�Ĵ��� 
		            view.loadData(errorHtml, "text/html", "UTF-8"); 
		            mHandler.sendEmptyMessage(20);
		  }
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			webView.getClass().getMethod("onResume").invoke(webView,(Object[])null);
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			webView.getClass().getMethod("onPause").invoke(webView,(Object[])null);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	

}
