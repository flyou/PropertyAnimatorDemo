---
title: 属性动画入门实践
date: 2017-03-01 08:46:32
categories:
- 技术领域
- Android
tags:
- 属性动画
- property animation
- animation
---

## 前言
说道动画肯定要先介绍一下，逐帧动画(frame-by-frame animation)和补间动画(tweened animation)。<br>逐帧动画和补间动画是Android出生时就具有的动画。所谓的补间动画就是一个视图上在指定的时间和时间长度下显示某张图片，一般用于加载动画或者做一些简单的展示。<br>
所谓的补间动画也很简单，学习过flash的读者肯定会很熟悉此类动画，因为它基本和flash的动画模式是一样的，可以对一个元素或者视图做透明度、缩放、平移、旋转动画，当然你也可以把四类组合起来使用完成比较炫酷的效果<br>

不过，你以为我给你具体介绍下上面两种动画的用法？<br>
你想太多了，我可没说今天要讲那玩意，需要的自行google去

![](http://ww1.sinaimg.cn/large/0060lm7Tgy1fd72y1al3uj309p06c748.jpg)

### 什么是属性动画
属性动画是google在Android3.0以后加入的新的动画机制，为什么叫属性动画呢？因为它是根据对象的属性来改变属性的值，最终达到属性动画的效果。<br>
这么说所有的对象都可以使用属性动画？对的，只要该对象具有相应属性并具有相应的的get和set方法就可以根据自己的需求来实现的自己的动画。
那么属性动画又有什么**优点**呢？

- 所有对象都支持
- view的坐标随着动画一起移动
- 更多的插值器，并支持自定义
- 配置方便自定义程度高

说了这么多，还是来看看怎么用吧
## ValueAnimator
值动画是属性动画中最基本也是最重要的一个类，因为属性动画是基于属性值改变来完成的，具体为什么这么重要，看下面的图就知道了。

![](http://ww2.sinaimg.cn/large/0060lm7Tgy1fd73rs72txj30k306o0tl.jpg)
从图中我们可以很清楚的看到，ValueAnimator可以设置插值器（TimeInterpolator）、值评估器（TypeEvaluator）、时间（duration）、以及开始值和结束的值，设置监听等。

     	ValueAnimator valueAnimator = new ValueAnimator().ofFloat(0, 500);
        valueAnimator.setDuration(3000);
        valueAnimator.start();

当然我们可以根据需要使用OfInt（）、ofFloat()、ofArgb（）、ofObject()完成不同的效果，当然里面可以传递多个参数值，这些值会在次序执行。

如上所示简单的几行语句就可以实现一个值动画，就是将值在3秒钟从0过渡到500，但是如何使用这个动画呢？<br>
从上面的图中我们可以可到ValueAnimator 中的UpdateListener，我们可以根据这个监听回调获得我我们想要的值，如何做呢?

![](http://ww1.sinaimg.cn/large/0060lm7Tgy1fd75jxkyy9j308c08cweh.jpg)

布局文件很简单，一个textView显示坐标，一个图片用来作用动画，还有一个按钮，不再贴出<br>

     ValueAnimator valueAnimator = new ValueAnimator().ofFloat(0, 500);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                imageView.setTranslationY(animatedValue);
                text.setText("imageView X:" + imageView.getY());
            }
        });
        valueAnimator.start();

上面的代码很简单，给刚才的动画设置了一个监听，当动画值发生改变时设置给ImageView。
![](http://ww3.sinaimg.cn/large/0060lm7Tgy1fd760tzsrcg30go0engxj.gif)

可以看到小炸弹的默认Y坐标是217，在经过3秒后他的坐标增加到了717.

## ObjectAnimator

### 基本用法

ObjectAnimator是ValueAnimator的子类，ObjectAnimato可以对任意对象进行操作，使用ObjectAnimato可以帮助我们很轻易的完成对任意对象的动画操作，比如View的alpha、scaleX、scaleY、rotationX、rotationY、translationX、translationY、X、Y等。<br>
为什么我们可以使用View的这些属性呢？因为它在View重定义的有，如下部分：
![](http://ww3.sinaimg.cn/large/0060lm7Tgy1fd7gdhznkoj30ge0dogmm.jpg)

例如	：<br>

- 实现对View的缩放操作。

	   	objectAnimator = new ObjectAnimator().ofFloat(image, "scaleX", 1f, 0f, 1f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();


- 实现对View的旋转操作。
	

		objectAnimator = new ObjectAnimator().ofFloat(image, "rotationX", 0, 360);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
- 实现对View的透明度操作
	

	  	objectAnimator = new ObjectAnimator().ofFloat(image, "alpha", 1f, 0f, 1f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();

- 实现对View的位移操作
 
		   objectAnimator = new ObjectAnimator().ofFloat(image, "translationX", 0, 300, 0);
                objectAnimator.setDuration(2000);
                objectAnimator.start();

**当然，我们还可以借助于animatorSet完成组合动画的操作，并控制动画播放的顺序**

- 实现组合动画

     	 ObjectAnimator alpha = new ObjectAnimator().ofFloat(image, "alpha", 1f, 0, 1);
                ObjectAnimator translationX = new ObjectAnimator().ofFloat(image, "translationX", 0, 300, 0);
                ObjectAnimator rotationX = new ObjectAnimator().ofFloat(image, "rotationX", 0, 360);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.playTogether(alpha, translationX, rotationX);
                animatorSet.start();

透明度变化动画：从完全不透明到透明再到完全不透明<br>
平移动画：将坐标从现在位置向X正方向偏移300然后再变到0<br>
旋转动画：将View旋转360°<br>
然后设置三个动画一起播放

- 实现次序播放控制

     	ObjectAnimator alpha1 = new ObjectAnimator().ofFloat(image, "alpha", 1f, 0.2f);
                ObjectAnimator alpha2 = new ObjectAnimator().ofFloat(image, "alpha", 0.2f, 1f);
                ObjectAnimator translationX1 = new ObjectAnimator().ofFloat(image, "translationX", 0, 300);
                ObjectAnimator translationX2 = new ObjectAnimator().ofFloat(image, "translationX", 300, 0);
                ObjectAnimator rotationX1 = new ObjectAnimator().ofFloat(image, "rotationX", 0, 360, 0);
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.setDuration(2000);
                animatorSet1.play(alpha1).before(translationX1);
                animatorSet1.play(rotationX1).after(alpha1);
                animatorSet1.play(rotationX1).before(translationX2);
                animatorSet1.play(translationX2).with(alpha2);
                animatorSet1.start();

透明度变化动画1：从完全不透明到0.2<br>
透明度变化动画2：从0.2到透明再到完全不透明<br>
平移动画1：将坐标从现在位置向X正方向偏移300<br>
平移动画2：回到原来位置<br>
旋转动画：将View旋转360°<br>

alpha1-->rotationX1、translationX1-->translationX2、alpha2

![](http://ww1.sinaimg.cn/large/0060lm7Tgy1fd7a4plej1g307i0boaz9.gif)

### 使用xml文件创建

	<animator xmlns:android="http://schemas.android.com/apk/res/android"  
    android:valueFrom="0"  
    android:valueTo="100"  
    android:valueType="intType"/> 

或者

	<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"  
    android:valueFrom="1"  
    android:valueTo="0"  
    android:valueType="floatType"  
    android:propertyName="alpha"/> 

或者是组合动画： 

	<set xmlns:android="http://schemas.android.com/apk/res/android"  
    android:ordering="sequentially" >  
  
    <objectAnimator  
        android:duration="2000"  
        android:propertyName="translationX"  
        android:valueFrom="-500"  
        android:valueTo="0"  
        android:valueType="floatType" >  
    </objectAnimator>  
  
    <set android:ordering="together" >  
        <objectAnimator  
            android:duration="3000"  
            android:propertyName="rotation"  
            android:valueFrom="0"  
            android:valueTo="360"  
            android:valueType="floatType" >  
        </objectAnimator>  
  
        <set android:ordering="sequentially" >  
            <objectAnimator  
                android:duration="1500"  
                android:propertyName="alpha"  
                android:valueFrom="1"  
                android:valueTo="0"  
                android:valueType="floatType" >  
            </objectAnimator>  
            <objectAnimator  
                android:duration="1500"  
                android:propertyName="alpha"  
                android:valueFrom="0"  
                android:valueTo="1"  
                android:valueType="floatType" >  
            </objectAnimator>  
        </set>  
    </set>  
  
	</set>  

**调用**

	Animator animator = AnimatorInflater.loadAnimator(context, R.animator.anim_file);  
	animator.setTarget(view);  
	animator.start();  

由于比较简单，就不再具体说明与演示，具体大家可以自己试下


### 自定义属性

前面就已经说过，属相动画可以作用任何对象，当然你也可以定义自己的属性，只要对象属性具有set和get方法。

接下来我们以改变Button的宽度。<br>
首先我们知道，Button 是有setWidth（）和getWidth（）方法的，但是当我们直接对对它做属性动画时却发现没有什么乱用，到底是为什么呢？
通过源码我们可以看到Button继承于TextView，而setWidth是TextView和其子类的专属方法，它的作用不是设置View的宽度，而是设置TextView的最大宽度和最小宽度的，这个和TextView的宽度不是一个东西，具体来说，TextView的宽度对应Xml中的android:layout_width属性，而TextView还有一个属性android:width，这个android:width属性就对应了TextView的setWidth方法。

那么，我们该如何通过属性动画来改变Button的宽度呢？

其实很简单只需要在Button外面包一层就好了，在外面设置相应的属性来来改变Button属性。

     private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

调用的地方：

     ViewWrapper viewWrapper = new ViewWrapper(button);
        ObjectAnimator objectAnimator = new ObjectAnimator().ofInt(viewWrapper, "width",buttonWidth,800);
        objectAnimator.setDuration(2000);
        objectAnimator.start();



![](http://ww1.sinaimg.cn/large/0060lm7Tgy1fd7k2oy9nug30co02s3zg.gif)

当然，使用**ValueAnimator**同样可以实现上面的效果

      @Override  
        public void onAnimationUpdate(ValueAnimator animator) {  
          
            int currentValue = (Integer)animator.getAnimatedValue();  
 
            button.getLayoutParams().width = buttonWidth+currentValue； 
            button.requestLayout();  
        }  
    });  

## Interpolator

Interpolator用于动画中的时间插值，其作用就是把0到1的浮点值变化映射到另一个浮点值变化

在属性动画中一共有10中插值器

![](http://ww1.sinaimg.cn/large/0060lm7Tgy1fd7kovzxzjj30nx06gmyb.jpg)
当然，support包中许多不同场景的插值器，这里我们不在做具体的介绍。



- 匀速线性
LinearInterpolator:

- 先加速后减速
AccelerateDecelerateInterpolator:

- 一直加速
AccelerateInterpolator:

- 反向移动然后正向加速
AnticipateInterpolator:

- 加速下落回弹
BounceInterpolator:

- 循环播放 参数指定循环次数
CycleInterpolator:

- 减速效果
DecelerateInterpolator:

- 反向超过原来位置 然后正向加速超过规定位置 返回
 AnticipateOvershootInterpolator:

- 向前甩一定值后再回到原来位置  可以传值指定加速度值
OvershootInterpolator:

- 按照一定路径完成相应运动的速度
PathInterpolator

具体实现如下：
初始化动画：

 	 private void initView() {
        objectAnimator = new ObjectAnimator().ofFloat(image, "translationY", 0, 1200);
        objectAnimator.setDuration(3000);
    }

设置插值器并运行动画：

     	public void onClick(View view) {
        switch (view.getId()) {
            //匀速线性
            case R.id.LinearInterpolator:
                objectAnimator.setInterpolator(new LinearInterpolator());
                break;
            //先加速后减速
            case R.id.AccelerateDecelerateInterpolator:
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            //一直加速
            case R.id.AccelerateInterpolator:
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                break;
            //反向移动然后正向加速
            case R.id.AnticipateInterpolator:
                objectAnimator.setInterpolator(new AnticipateInterpolator());
                break;
            //加速下落回弹
            case R.id.BounceInterpolator:
                objectAnimator.setInterpolator(new BounceInterpolator());
                break;
            //循环播放 参数指定循环次数
            case R.id.CycleInterpolator:
                objectAnimator.setInterpolator(new CycleInterpolator(2f));
                break;
            //减速效果
            case R.id.DecelerateInterpolator:
                objectAnimator.setInterpolator(new DecelerateInterpolator());
                break;
            //反向超过原来位置 然后正向加速超过规定位置 返回
            case R.id.AnticipateOvershootInterpolator:
                objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            //向前甩一定值后再回到原来位置  可以传值指定加速度值
            case R.id.OvershootInterpolator:
                objectAnimator.setInterpolator(new OvershootInterpolator());
                break;
            case R.id.PathInterpolator:
                Intent intent=new Intent(InterpolatorActivity.this,InterpolatorPathActivity.class);
                startActivity(intent);
                break;

        }
        objectAnimator.start();
   		 }
![](http://ww2.sinaimg.cn/large/0060lm7Tgy1fd7lchfqlfg30dc0kub29.gif)



### PathInterpolator
> 至于**PathInterpolator**这里需要特别做下说明（其他上面的差值器和这个类似，简单的多...），因为它真的很强大，可以实现很多漂亮的效果。


首先看PathInterpolator 的构造参数里面，可以传入一个path对象、一个控制点或者两个控制点。
![](http://ww2.sinaimg.cn/large/0060lm7Tgy1fd7oahph7kj30cj040mx4.jpg)

打开**PathInterpolator的源码**我们可以看到

		//构造函数1
     public PathInterpolator(Path path) {
        initPath(path);
    }

   		//构造函数2
    public PathInterpolator(float controlX, float controlY) {
        initQuad(controlX, controlY);
    }

  	 	//构造函数3
    public PathInterpolator(float controlX1, float controlY1, float controlX2, float controlY2) {
        initCubic(controlX1, controlY1, controlX2, controlY2);
    }
		//这个方法里面的东西是不是很熟悉，没错他就是二阶贝塞尔曲线
	 private void initQuad(float controlX, float controlY) {
        Path path = new Path();
        path.moveTo(0, 0);
        path.quadTo(controlX, controlY, 1f, 1f);
        initPath(path);
    }
		//当然他就是三阶贝塞尔曲线咯
    private void initCubic(float x1, float y1, float x2, float y2) {
        Path path = new Path();
        path.moveTo(0, 0);
        path.cubicTo(x1, y1, x2, y2, 1f, 1f);
        initPath(path);
    }


可以看待三个构造函数归根结底都是构建了一个Path对象，然后对path进行操作<br>
**initPath()**


     private void initPath(Path path) {
        float[] pointComponents = path.approximate(PRECISION);

        int numPoints = pointComponents.length / 3;
        if (pointComponents[1] != 0 || pointComponents[2] != 0
                || pointComponents[pointComponents.length - 2] != 1
                || pointComponents[pointComponents.length - 1] != 1) {
            throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1)");
        }

        mX = new float[numPoints];
        mY = new float[numPoints];
        float prevX = 0;
        float prevFraction = 0;
        int componentIndex = 0;
        for (int i = 0; i < numPoints; i++) {
            float fraction = pointComponents[componentIndex++];
            float x = pointComponents[componentIndex++];
            float y = pointComponents[componentIndex++];
            if (fraction == prevFraction && x != prevX) {
                throw new IllegalArgumentException(
                        "The Path cannot have discontinuity in the X axis.");
            }
            if (x < prevX) {
                throw new IllegalArgumentException("The Path cannot loop back on itself.");
            }
            mX[i] = x;
            mY[i] = y;
            prevX = x;
            prevFraction = fraction;
        }
    }

path.approximat()方法实际就是调用了Native层的方法，返回了一系列值。包括fraction、X、Y等<br>
通过这个方法我们就得到了我们想要的fraction，这个fraction是实时变化的。我们可以在addUpdateListener的回调中获取：

     valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
               
               float animatedFraction = valueAnimator.getAnimatedFraction();
              
            }
        });


animatedFraction这个值是0-1.0f的一个值，描述的是当前动画播放比例，我们回在下面具体介绍。

    objectAnimator.setInterpolator(new PathInterpolator(0.8f,0.1f));
:
	
	objectAnimator.setInterpolator(new PathInterpolator(0.1f,0.8f));


![](http://ww4.sinaimg.cn/large/0060lm7Tgy1fd7q8djrt6g307j0m8woc.gif)  ![](http://ww3.sinaimg.cn/large/0060lm7Tgy1fd7qd234tdg30800m8dpc.gif)


这样大概看不出什么效果，还是结合Path动画来看下吧 。

**首先创建Path动画**

    	Path path = new Path();
        path.moveTo(X,Y);
        path.quadTo(800, Y, 800, 800);
        ObjectAnimator objectAnimator1 = new ObjectAnimator().ofFloat(image, "x", "y", path);
        objectAnimator1.setDuration(3000);
        objectAnimator1.start();

上面看到创建的path 将起点定在view的初始位置（X,Y），控制点为(800,Y),结束点为（800,800）<br>
假设x=y=100

		Path path = new Path();
        path.moveTo(100,100);
        path.quadTo(800, 100, 800, 800);
![](http://wx4.sinaimg.cn/mw690/a2f7c645ly1fd7qxbdl1vj20ay0bndfm.jpg)

中间的曲线可以随着参数的改变来改变，那么到底是不是这个样子呢？

![](http://ww3.sinaimg.cn/large/0060lm7Tgy1fd7rapyd3gg30dw0cowid.gif)

接下来我们给他加上PathInterpolator 制定了连个控制点

objectAnimator1.setInterpolator(new PathInterpolator(0.9f,0.1f,0.5f,0.9f));

![](http://ww2.sinaimg.cn/large/0060lm7Tgy1fd7reu5itgg30dw0cygpl.gif)


PathInterpolator就先介绍到这里，接下来看下Evaluator

## Evaluator
Evaluator是值评估器的意思，帮助你很好的完成属性值的计算工作，当然你可以自定义自己的值评估器来完成自己的需要
下面是IntEvaluator的源码，可以看到这里有个亿参数叫做fraction，fraction就是上面我们提到的进度比例。

    public class IntEvaluator implements TypeEvaluator<Integer> {
	  @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(startInt + fraction * (endValue - startInt));
    }

这里首选获取开始的值，然后加上结束减去中间再乘以进度比例就获得了现在的值。如果起始值为0，那么上面的公式就变成了：

		 public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
       	 	
        	return (int)（fraction * endValue）;
    	}


那么如果我想让接受方的值扩大5倍怎么办？

当然是直接在这里乘以5就行了，这样外面接受属性值得地方，接收的值就扩大了5倍

    @Override
    	public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(startInt + fraction * (endValue - startInt))*5;
    }

### 如何自定义Evaluator

由IntEvaluator的源码我们可以看到其实现了TypeEvaluator<T>接口，并复写了evaluate函数。

那么我们也可以根据这个来自定义自己的估值器。


首先，我们有这样一个场景，学生的年龄年龄从出生到22岁每年增长一岁，学生的体重从出生时的40cm到22岁188cm。
Stduent：

    public class Student {
   private int age;
   private int height;

    public Student(int age, int height) {
        this.age = age;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
	}

StudentEvaluator：自定义估值器，获取对象并作用后返回。

    public class StudentEvaluator implements TypeEvaluator<Student> {

    @Override
    public Student evaluate(float fraction, Student startValue, Student endValue) {
        int startAge = startValue.getAge();
        int startHeight = startValue.getHeight();
        int endAge = endValue.getAge();
        int endHeight = endValue.getHeight();

        int currentAge = (int) (startAge + fraction * (endAge - startAge));

        int currentHeight = (int) (startHeight + fraction * (endHeight - startHeight));

        return new Student(currentAge, currentHeight);
    }
	}

使用估值器：

      ValueAnimator valueAnimator=new ValueAnimator().ofObject(new StudentEvaluator(),
		new Student(0,40),new Student(22,188));
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Student student = (Student) animation.getAnimatedValue();
                info.setText("age:"+student.getAge()+",height:"+student.getHeight());
            }
        });
    valueAnimator.start();
![](http://ww4.sinaimg.cn/large/0060lm7Tgy1fd7t3rupptg30go04z430.gif)


估值器先介绍到这里，当然你可以根据自己的业务逻辑完成自己的估值器。

## ViewPropertyAnimator

在3.0以后同样增加的就是ViewPropertyAnimator，它的用法比ObjectAnimator更加方便，更加好用。

> 
> 
> - 整个ViewPropertyAnimator的功能都是建立在View类新增的animate()方法之上的，这个方法会创建并返回一个ViewPropertyAnimator的实例，之后的调用的所有方法，设置的所有属性都是通过这个实例完成的。

> 
> - 在使用ViewPropertyAnimator时，我们无需调用start()方法，因为新的接口中使用了隐式启动动画的功能，只要我们将动画定义完成之后，动画就会自动启动。并且这个机制对于组合动画也同样有效，只要我们不断地连缀新的方法，那么动画就不会立刻执行，等到所有在ViewPropertyAnimator上设置的方法都执行完毕后，动画就会自动启动。当然如果不想使用这一默认机制的话，我们也可以显式地调用start()方法来启动动画。

> 
> - ViewPropertyAnimator的所有接口都是使用连缀的语法来设计的，每个方法的返回值都是它自身的实例，因此调用完一个方法之后可以直接连缀调用它的另一个方法，这样把所有的功能都串接起来，我们甚至可以仅通过一行代码就完成任意复杂度的动画功能。

事例：

	imageview.animate().x(500).y(500).alpha(0.8f)setDuration(5000); 

	imageview.animate().x(500).y(500).alpha(0.8f)setDuration(5000); 

        go.animate().setInterpolator(new LinearInterpolator()).rotationBy(360).scaleXBy(0.1f).translationZ(30).setDuration(3000)

但是，里面新增了相应的以By的结尾的方法，当然就是从现在状态执行相应值，translationZ（）指的是Z轴方向的偏移两
是在API21才加入的方法


举个栗子：

点击按钮时让image的Y坐标向下移动300像素，让image旋转720度：


    public void onClick() {
        image.animate().translationY(300).rotation(720).setDuration(3000);
    }

![](http://ww4.sinaimg.cn/large/0060lm7Tgy1fd88o5wedcg30cs0b9jxi.gif)


可以看到第一次点击，iamge向下面移动了300像素并伴随着720°的旋转，可以当后面再点击时，发现image并没有任何变化。

那么，现在我们就需要考虑下以By结尾的方法了。

     public void onClick() {
        image.animate().translationYBy(300).rotationBy(720).setDuration(3000);
    }


![](http://ww2.sinaimg.cn/large/0060lm7Tgy1fd88wfz0jcg30ci0iftm9.gif)


那么，再来看下Z和translationZBy（），下面就用translationZBy（）来作说明。

      public void onClick() {
        go.animate().translationZBy(30f);
    }

每次点击，我们让按钮在Z轴的偏移量增加30,效果如下
![](http://ww4.sinaimg.cn/large/0060lm7Tgy1fd892e9i3fg30ex04bgu0.gif)

当然，我们依然可以给动画设置Listener、UpdateListener、和插值器Interpolator，这些做法和上面讲到的用法一样，就不再具体说明，有兴趣的童鞋可以自己去尝试。

## 后记

怎么样，学完属性动画是不是已经爱上它了？没有也没关系，先用起来吧，相信你试过以后一定会爱上我，不，是它，是它！。

照例：




















