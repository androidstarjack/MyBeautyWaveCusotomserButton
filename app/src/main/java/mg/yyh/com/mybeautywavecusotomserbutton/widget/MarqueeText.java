package mg.yyh.com.mybeautywavecusotomserbutton.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


 /**
 * 类功能描述：</br>
 * 一个炫酷的跑马路灯效果
 *自定义View
  * 走马灯文字控件
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class MarqueeText extends TextView implements Runnable {

	private int currentScrollX;// 当前滚动的横向位置
	private boolean isStop = false;// 是否停止
	private int speed = 3;// 滚动速度
	private int textWidth;// 跑马灯字体的宽度
	private boolean isMeasure = false;// 是否已经测量来这个view要展示的字体长度
	public final static int RIGHT = 0;
	public final static int LEFT = 1;
	private int scrollOrientation = MarqueeText.LEFT;// 滚动方向 （0是向右滚 1是向左滚）
	public final static int[] colors = { Color.BLUE, Color.GREEN, Color.WHITE,
			Color.YELLOW, Color.RED, Color.BLACK };
	private int i = 0;// 文字变换颜色的索引

	/**
	 * 自定义控件的构造函数
	 */
	public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeText(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (!isMeasure) {
			getTextWidth();
			isMeasure = true;
		}
	}

	/**
	 * 重写父类的setText方法，给跑马灯设置展示的文字
	 */
	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text, type);
		this.isMeasure = false;
	}

	private void getTextWidth() {
		Paint paint = this.getPaint();// 得到画笔
		String str = this.getText().toString();// 得到给这个view设置的文字
		textWidth = (int) paint.measureText(str);// 得到了文字的宽度并赋值给这个对象的textWidth属性
	}

	@Override
	public void run() {
		if (scrollOrientation == MarqueeText.LEFT) {
			currentScrollX += speed;
			scrollTo(currentScrollX, 0);
			if (isStop) {
				return; // 停止
			}
			if (getScrollX() >= textWidth) {// 当滚动到文字的末尾时
				scrollTo(-this.getWidth(), 0);// 滚回到
				currentScrollX = -this.getWidth();
			}

		} else if (scrollOrientation == MarqueeText.RIGHT) {
			currentScrollX -= speed;// 滚动的速度
			scrollTo(currentScrollX, 0);// 滚动到currentScrollX位置(此处是文字滚动,实际上这个view并没有滚动)
			if (isStop) {
				return; // 停止
			}
			if (getScrollX() <= -(this.getWidth())) {// 当滚动的位移超过这个view的宽度的时候
				scrollTo(textWidth, 0);// 滚回到
				currentScrollX = textWidth;
			}
		}

		this.setTextColor(colors[i]);
		i++;
		if (i == colors.length) {
			i = 0;
		}
		postDelayed(this, 10);// 10毫秒后把这个runnable对象再次放入消息队列中，使其可以再次执行run方法
	}

	public void startScroll() {
		isStop = false;
		this.removeCallbacks(this);// 清楚消息队列里的runnable对象
		post(this);// 把这个runnable对象放入消息队列中 然后开始执行run里面的方法
	}

	public void stopScroll() {
		currentScrollX = 0;
		isStop = true;
	}

	public void startFor0() {
		currentScrollX = 0;
		startScroll();
	}

	public void left() {
		currentScrollX = 0;
		scrollOrientation = LEFT;
		startScroll();
	}

	public void right() {
		currentScrollX = 0;
		scrollOrientation = RIGHT;
		startScroll();
	}

	public void addSpeed() {
		if (speed < 10) {
			speed += speed;
		}
	}

	public void reduceSpeed() {
		if (speed > 1) {
			speed=speed-1;
		}
	}

}
