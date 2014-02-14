package com.example.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//webviewの生成
	WebView myWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // myWebViewのインスタンスを取得
        myWebView = (WebView)findViewById(R.id.webView1);

        // カスタムWebViewを設定する
        myWebView.setWebViewClient(new CustomWebView());

        // Googleを表示する(デフォルト)
        myWebView.loadUrl("http://www.google.co.jp/");
        
        Toast.makeText(this,"menuを押すとサイトを選択できます。",Toast.LENGTH_LONG).show();
}

// オプションメニューの初期化
@Override
public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "Google");
        menu.add(0, 1, 1, "Yahoo");
        menu.add(0, 2, 2, "Github");
        return true;

}

// オプションメニュー選択された場合、選択項目に合わせて
// WebViewの表示先URLを変更する。
@Override
public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int itemId = item.getItemId();
        switch(itemId) {
        
        case 0:
                myWebView.loadUrl("http://www.google.co.jp/");
                break;
        
        case 1:
                myWebView.loadUrl("http://www.yahoo.co.jp/");
                break;
        
        case 2:
                myWebView.loadUrl("https://github.com/");
                break;
        }
        
        return true;
}

// AndroidのBackキーを押された時のイベントを受け取り、
// 前回表示したページに戻る。
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
                myWebView.goBack();
                return true;
        }
        return super.onKeyDown(keyCode, event);
}

/**
 * WebViewClientクラスを継承したカスタムWebView（内部クラス）
 *
 */
private class CustomWebView extends WebViewClient {

        //ページの読み込み開始
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Toast.makeText(
                                view.getContext(),
                                "ページを読み込み中です。",
                                Toast.LENGTH_LONG).show();
        }

        //ページの読み込み完了
        @Override
        public void onPageFinished(WebView view, String url) {
                Toast.makeText(
                                view.getContext(),
                                "ページを読み込みました。",
                                Toast.LENGTH_LONG).show();
        }

        //ページの読み込み失敗
        @Override
        public void onReceivedError(
                        WebView view,
                        int errorCode,
                        String description,
                        String failingUrl) {

                Toast.makeText(
                                view.getContext(),
                                "ページの読み込みに失敗しました。",
                                Toast.LENGTH_LONG).show();
                
                }
        }
}

