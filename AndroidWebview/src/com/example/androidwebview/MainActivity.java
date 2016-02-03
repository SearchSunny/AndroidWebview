package com.example.androidwebview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.androidwebview.dialog.WebViewDialog;
/**
 * webView��ʹ��
 * ������webviewЧ��
 */
public class MainActivity extends Activity implements OnClickListener{

	private Button btn_web;
	private ViewStub viewStub;
	private WebView webView;
	private String errorHtml;
	
	private WebViewDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_web = (Button)findViewById(R.id.btn_web);
		viewStub = (ViewStub)findViewById(R.id.viewStub);
		btn_web.setVisibility(View.GONE);
		btn_web.setOnClickListener(this);
		
		dialog = new WebViewDialog(this);
		dialog.show();
		
		/*View view = viewStub.inflate();
		webView = (WebView)view.findViewById(R.id.wbeview);
		webView.getSettings().setJavaScriptEnabled(true); 
		webView.loadUrl("http://player.youku.com/embed/XNTM5MTUwNDA0");
		webView.requestFocus();
		webView.setWebViewClient(new MyWebViewClient());*/
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
		  }
		  //����ҳ�����
		  @Override
		  public void onPageFinished(WebView view, String url) {
		   // TODO Auto-generated method stub
		   super.onPageFinished(view, url);
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
		  }
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_web:
			//startActivity(new Intent(this, WebViewActivity.class));
			break;
		default:
			break;
		}
		
	}
}
