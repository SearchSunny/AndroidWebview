package com.example.androidwebview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.androidwebview.R;
/**
 * webview������Ч��
 * ������Dialog��ʽ
 */
public class WebViewDialog extends Dialog implements
		android.view.View.OnClickListener {

	private Context mContext;

	public WebViewDialog(Context context) {
		super(context, R.style.ShareDialog);
		Log.i("MV", "context");
		mContext = context;
		initView();

	}

	private WebView webView;
	private ProgressBar progress;
	private String errorHtml;
	private RelativeLayout relative;

	private void initView() {

		Log.i("MV", "initView");
		View convertView = getLayoutInflater().inflate(
				R.layout.activity_webview, null);
		progress = (ProgressBar) convertView.findViewById(R.id.loading);
		webView = (WebView) convertView.findViewById(R.id.wbeview);
		relative = (RelativeLayout) convertView.findViewById(R.id.relative);

		relative.setOnClickListener(this);
		errorHtml = "<html><body><h1>Page not find��</h1></body></html>";
		webView.getSettings().setJavaScriptEnabled(true);
		//�ɽ��webview��xml����background��Ч�����
		webView.setBackgroundColor(0); // ���ñ���ɫ

		// webView.loadUrl("http://www.google.com");
		webView.loadUrl("http://player.youku.com/embed/XNTM5MTUwNDA0");
		webView.setWebViewClient(new MyWebViewClient());
		// setContentView(convertView);

		/*
		 * Window win = getWindow(); win.getDecorView().setPadding(0, 0, 0, 0);
		 * WindowManager.LayoutParams lp = win.getAttributes(); lp.width =
		 * WindowManager.LayoutParams.FILL_PARENT; lp.height =
		 * WindowManager.LayoutParams.WRAP_CONTENT; win.setAttributes(lp);
		 */

		setContentView(convertView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = getWindow();
		window.setGravity(Gravity.TOP);
		// ������ʾ����
		// ����dialogռ��������
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = LayoutParams.MATCH_PARENT;
		window.setAttributes(lp);

		setCanceledOnTouchOutside(true);

	}

	@Override
	public void cancel() {
		super.cancel();
		try {
			webView.getClass().getMethod("destroy")
					.invoke(webView, (Object[]) null);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private class MyWebViewClient extends WebViewClient {
		// ����ҳ�濪ʼ
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		// ����ҳ�濪ʼ��
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			// mHandler.sendEmptyMessage(10);
			addProgressBar();
		}

		// ����ҳ�����
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			// mHandler.sendEmptyMessage(20);
			closeProgressBar();
		}

		/**
		 * ����������¼򵥴���/ ����webҳ�涼�������������£� һ��û������ͻ���ʾ"�޷��ҵ�����ҳ"����Ϣ��
		 * �����ᱩ¶���ǵ����ӣ�����������Ҫһ���кõ���ʾ��
		 * ���Ҳ��ᱩ¶���ӵķ�������ʱ��WebViewClient��onReceivedError�������������ó�
		 * 
		 */
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			// �������������������������Ը���errorCode��ֵ�����жϣ�������ϸ�Ĵ���
			view.loadData(errorHtml, "text/html", "UTF-8");
			// mHandler.sendEmptyMessage(20);
			closeProgressBar();
		}
	}

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

	@Override
	public void onClick(View v) {
		Log.i("MV", "onClick");
		switch (v.getId()) {
		case R.id.relative:
			WebViewDialog.this.cancel();
			break;
		default:
			break;
		}

	}

}
