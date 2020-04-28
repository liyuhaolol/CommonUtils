# 一些通用的工具库

封装了一些通用工具类，可以直接使用，不用每次再单独复制粘贴

## 1.0.3更新

- 修正一些类的名字，避免冲突，或者理解困难

## 框架引用方法

- 在gradle中:
```gradle
    implementation 'spa.lyh.cn:lib_utils:1.0.3'
```

## 引用的主要类

- `implementation 'com.google.android.material:material'` material类

# 实体类方法说明

## AESencryptUtil

#### AES加密

- encrypt();

```java
/**
* @param seed 密钥
* @param cleartext 明文
* @return 密文
*/
String encrypt(String seed, String cleartext)
```

#### AES解密

- decrypt();

```java
/**
* @param seed 密钥
* @param cleartext 密文
* @return 明文
*/
String encrypt(String seed, String cleartext)
```
## StatusBarFontColorControler

#### 状态栏字体颜色

- setStatusBarMode();

```java
/**
* @param activity 上下文
* @param darkFont 是否为深色字体
* @return 是否匹配成功，true代表执行了某个区块的逻辑，false代表没有匹配成功
*/
boolean setStatusBarMode(Activity activity, boolean darkFont)
```

## AppUtils

#### 判断当前应用是否是debug状态

- isApkInDebug();

```java
/**
* @param context 上下文
* @return true代表是debug，false代表是realse
*/
boolean isApkInDebug(Context context)
```

#### 判断手机是否存在导航栏

- checkDeviceHasNavigationBar();

```java
/**
* @param context 上下文
* @return true代表有虚拟按键或者其他模式的导航栏，false代表没有虚拟按键等，但也不代表有物理按键
*/
boolean checkDeviceHasNavigationBar(Context context)
```

#### 读取Assets文件夹中的图片资源

- getImageFromAssetsFile();

```java
/**
* @param context 上下文
* @param fileName 图片文件名
* @return 对应图片的bitmap
*/
Bitmap getImageFromAssetsFile(Context context, String fileName)
```

#### 检查网络是否可用

- isNetworkAvailable();

```java
/**
* 最低运行版本是Andriod6.0
* @param context 上下文
* @return 对应图片的bitmap
*/
@TargetApi(Build.VERSION_CODES.M)
boolean isNetworkAvailable(Context context)
```

## ImageUtils

#### 停止gif播放

- stopGIF();

```java
/**
* @param imageView imageview对象
*/
stopGIF(ImageView imageView)
```

#### 开始gif播放

- startGIF();

```java
/**
* @param imageView imageview对象
*/
startGIF(ImageView imageView)
```

## MathUtils

#### float转int四舍五入

- floatToInt();

```java
/**
* @param f float数
* @return 四舍五入后的int数
*/
int floatToInt(float f)
```

## PixelUtils

#### dp转px

- dip2px();

```java
/**
* @param context 上下文
* @param dpValue dp值
* @return px值
*/
int dip2px(Context context, float dpValue)
```

#### px转dp

- px2dip();

```java
/**
* @param context 上下文
* @param pxValue px值
* @return dp值
*/
int px2dip(Context context, float pxValue)
```

#### 用于获取状态栏的高度

- getStatusBarHeight();

```java
/**
* @param context 上下文
* @return 状态栏高度
*/
int getStatusBarHeight(Context context)
```

#### 用于获取导航栏的高度

- getNavigationBarHeight();

```java
/**
* @param context 上下文
* @return 导航栏高度
*/
int getNavigationBarHeight(Context context)
```

## TextDetailUtils

#### 得到TextView一行最多显示多少个字

- getLineMaxNumber();

```java
/**
* 最低运行版本是Andriod6.0
* @param text 文本内容
* @param paint 要判断的textview的textpaint类
* @param maxWidth textview所能达到的最大宽度
* @param lines 第几行
* @return 最多显示的字数
*/
@TargetApi(Build.VERSION_CODES.M)
int getLineMaxNumber(String text, TextPaint paint, int maxWidth, int lines)
```

#### 禁止EditText输入空格

- setEditTextInhibitInputSpace();

```java
/**
* @param editText edittext对象
*/
setEditTextInhibitInputSpace(EditText editText)
```

#### 禁止EditText输入特殊字符

- setEditTextInhibitInputSpeChat();

```java
/**
* 特殊字符为: `~!@#$%^&*()+=|{}':;',\[\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？
* @param editText edittext对象
*/
setEditTextInhibitInputSpeChat(EditText editText)
```

#### 打开软键盘

- openKeybord();

```java
/**
* @param mContext 上下文
*/
openKeybord(Activity mContext)
```

#### 关闭软键盘

- closeKeybord();

```java
/**
* @param mContext 上下文
*/
closeKeybord(Activity mContext)
```

## TimeUtils

#### 毫秒转换分：秒

- getGapTime();

```java
/**
* @param time 毫秒的时间
* @return 转换为分：秒
*/
String getGapTime(long time)
```

## View的一些封装类

- AutoScrollView <br/>
继承自ScrollView,用来解决ScrollView嵌套RecyclerView时，在Android5.0以上设备丢失滑动惯性问题<br/>
使用方法：在xml中插入view，actvity中注册实例使用

- EmptyItemAnimator <br/>
继承自SimpleItemAnimator,用来去除recyclerview刷新时闪烁的动画<br/>
使用方法：Recyclerview.setItemAnimator(new EmptyItemAnimator());

- PopupWindowAndroidN <br/>
继承自PopupWindow,用来解决Android7.0这个版本popupwindows设置位置无效等问题<br/>
使用方法：与PopupWindow的使用方法一样

- TopCropImageView <br/>
继承自AppCompatImageView,用来对图片进行TopCorp裁切，保证顶部不被裁切掉，应用场景：后台不按规范传图保证不切掉人物脑袋<br/>
使用方法：在xml中插入view，actvity中注册实例使用

- Toast <br/>
这个Toast类是专门用来去除某些国产OS，比如MIUI，在Toast时会强制加上AppName的问题。如无需求必要性，可以不用<br/>
使用方法：与原Toast用法完全一样
