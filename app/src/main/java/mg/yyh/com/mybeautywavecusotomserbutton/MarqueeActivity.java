package mg.yyh.com.mybeautywavecusotomserbutton;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import mg.yyh.com.mybeautywavecusotomserbutton.widget.MarqueeText;
/**
 * 类功能描述：</br>
 * 一个炫酷的跑马路灯效果
 *测试app
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class MarqueeActivity extends Activity {
	private MarqueeText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marquee);
		text = (MarqueeText) findViewById(R.id.marqueeText);
	}

	public void start(View v) {
		text.startScroll();
	}

	public void stop(View v) {
		text.stopScroll();
	}

	public void startFor0(View v) {
		text.startFor0();
	}

	public void left(View v) {
		text.left();
	}

	public void right(View v) {
		text.right();
	}

	public void addSpeed(View v) {
		text.addSpeed();
	}

	public void reduceSpeed(View v) {
		text.reduceSpeed();
	}
}
