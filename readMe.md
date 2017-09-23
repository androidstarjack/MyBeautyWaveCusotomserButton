一个炫酷的跑马路灯效果

效果图如下：

![跑马路灯效果图.gif](http://upload-images.jianshu.io/upload_images/4614633-9fb719d71c12df6c.gif?imageMogr2/auto-orient/strip)



#### 具体逻辑

- 首先自定义View实现Runnable接口
- 控制向左或者是向右的方向
- 调用scrollTo方法进行滑动
- 事件的处理10毫秒执行机制


部分代码
实现runnable接口

```
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
```

开始和停止滚动
```
public void startScroll() {
		isStop = false;
		this.removeCallbacks(this);// 清楚消息队列里的runnable对象
		post(this);// 把这个runnable对象放入消息队列中 然后开始执行run里面的方法
	}

	public void stopScroll() {
		currentScrollX = 0;
		isStop = true;
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
		public void startFor0() {
		currentScrollX = 0;
		startScroll();
	}
```
向左
```


	public void left() {
		currentScrollX = 0;
		scrollOrientation = LEFT;
		startScroll();
	}
```

效果如下：

![向左跑起来.gif](http://upload-images.jianshu.io/upload_images/4614633-624475cc13d74833.gif?imageMogr2/auto-orient/strip)


向右

```
	public void right() {
		currentScrollX = 0;
		scrollOrientation = RIGHT;
		startScroll();
	}

```
效果如下：


![向右跑起来.gif](http://upload-images.jianshu.io/upload_images/4614633-abf54a0cca62365d.gif?imageMogr2/auto-orient/strip)

### 更多文章

[ 2017上半年技术文章集合—184篇文章分类汇总](http://blog.csdn.net/androidstarjack/article/details/77923753)

[ NDK项目实战—高仿360手机助手之卸载监听](http://blog.csdn.net/androidstarjack/article/details/77984865)


[高级UI特效仿直播点赞效果—一个优美炫酷的点赞动画](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100000969&idx=1&sn=626d821d16346764fdce33e65f372031&chksm=6b4768575c30e14163ae8fb9f0406db0b3295ce47c4bc27b1df7a3abee1fa0bb71ef27b4e959#rd)

[一个实现录音和播放的小案例](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100000959&idx=1&sn=a5acb0f44fbadeaa9351df067438922c&chksm=6b4768215c30e1371a3c750f2b826f38b3a263c937272ae208717f73f92ed3e8fd8b6a674686#rd)

#### 相信自己，没有做不到的，只有想不到的

![加入大牛圈](http://img.blog.csdn.net/20170910215455020?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYW5kcm9pZHN0YXJqYWNr/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

 如果你觉得此文对您有所帮助，欢迎入群 QQ交流群 ：644196190
微信公众号：终端研发部

![技术+职场](https://user-gold-cdn.xitu.io/2017/8/1/d354d51a5c58fb8a5ba576f2d9ea7a8e)




