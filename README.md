# 一些通用的工具库

封装了一些通用工具类，可以直接使用，不用每次再单独复制粘贴

## 框架引用方法

- 在gradle中:
```gradle
    implementation 'com.github.liyuhaolol:CommonUtils:1.4.4'
```

## 1.4.4更新

- 增加继承自DialogFragment的FullDialog基类
#### FullDialog特性
- 默认dialog可用空间达到全屏，去除了dialog默认的padding以及对状态栏和导航栏的限制
- 默认开启了沉浸式，所以UI的实际显示区域不在安全区里，如果需求出现UI下沉到导航栏或上抬到状态栏里，需要用户自己按照需求在不布局中添加状态栏和导航栏的占位View并托管给FullDialog处理
- `isUIimmerseNavbar()`默认为false，所有的导航栏占位View都会占用高度，返回true则小白条状态下，导航栏占位View不会占用高度
- `setStyleId()`非必传，设置Dialog的主题id
- `setBackgroundId()`非必传(但是推荐传)设置Dialog布局的根布局Id，如果不返回id，则点击灰色（空白）区域关闭Dialog方法永远无法生效，由于开启了全屏沉浸，并把显示区域拓展到全屏，Dialog原本的灰色遮罩已被完全遮罩，无法响应任何事件，故出此下策
- `setStatusBarId()`非必传，根据需求如果需要出现顶部状态栏高度占位View的时候，则传递其id，高度由父类`FullDialog`自动负责匹配
- `setNavigationBarId()`非必传，根据需求如果出现弹窗紧靠下方导航栏，导航栏需要跟随UI的颜色时，需要添加导航栏高度占位View，则传递其id，高度由父类`FullDialog`自动负责匹配
#### 注意事项
- Dialog的UI从`onCreateView()`方法中添加，与`Fragment`添加布局方式一致
- 用户需要自己根据UI颜色来判读，是否需要状态栏和导航栏的文字转色，转色逻辑必须写在`onResume()`里的`super`后面，写在更前面的生命周期里，`FullDialog`无法获取正确的Window对象，所有设置无法生效

## 1.4.3更新

- 将InsetsController替换为WindowInsetsController，应该从根源上避免了日志报异常的问题

## 1.4.2更新

- 修正InsetsController会报NULL崩溃的问题

## 1.4.1更新

- 移除状态栏对老MIUI的支持
- 状态栏和导航栏文字转色增加对Android11以上新API的支持

## 1.4.0更新

- 修正华为系手机(华为，荣耀，Nova)高版本系统，状态栏永远不会是透明的问题。

## 1.3.9更新

- 增加获取线程名称的方法

## 1.3.8更新

- 进一步处理Context为空的问题

## 1.3.7更新

- NTP服务器时间加入Context为空时的逻辑，避免空指针

## 1.3.6更新

- NTP服务器时间加入持久化避免竞速问题

## 1.3.5更新

- 添加NTP服务器校对时间模块

## 1.3.4更新

- 增加使用View来控制软键盘的打开关闭，增强使用场景兼容性

## 1.3.3更新

- 获取状态栏高度换为Context增强兼容性

## 1.3.2更新

- 中间更新了什么不记得了，这个版本修改dp与px互相转换的方法，修正逻辑错误。px必须为int，dp可以为float

## 1.2.6更新

- 增加计算时间的国际化方法

## 1.2.5更新

- 尝试修正一些虚拟按键手机，会认为自己有物理按键，造成导航栏判断失败的问题

## 1.2.4更新

- 尝试修正存在物理按键的手机，导航栏判断存在错误的问题

## 1.2.3更新

- 重写自适应状态栏导航栏的相关代码，增强兼容性，保证可以在系统改变时跟随。

## 1.2.2更新

- 恢复刘海的判断，我这里没有足够的机型测试，不过错误似乎只发生在某些机型的虚拟机上，真机未发生此问题。

## 1.2.1更新

- 去除自定义Toast，因为Android11彻底封禁自定义Toast的可能
- 沉浸式使用刘海区域无论沉浸的是状态栏，还是导航栏
- 去除判断是否存在刘海的判断逻辑，在Android11上似乎判断的结果有问题

## 1.2.0更新

- 更换源到jitpack

## 1.1.9更新

- 修正沉浸式启动顺序错误，造成某些弱智手机OS会全部黑掉的问题

## 1.1.8更新

- 增加导航栏种类的判断，判断阈值是0.03

## 1.1.7更新

- 增加状态栏，导航栏对View的直接支持，用来兼容Fragment中的适配问题

## 1.1.6更新

- 增加Android9.0以上对刘海屏的判断

## 1.1.5更新

- 沉浸式修正对输入法的一些兼容

## 1.1.4更新

- 移除状态栏对flyme老版本的支持
- 移除状态栏对colorOS老版本的支持
- 移除沉浸式对activity的依赖

## 1.1.3更新

- 修正Android11的导航栏阴影问题

## 1.1.2更新

- 修正高版本Android导航栏高度问题

## 1.1.1更新

- 更新最新lib库

## 1.1.0更新

- 添加兼容性读取res文件夹内容的方法

## 1.0.9更新

- 添加状态栏的显示和隐藏

## 1.0.7更新

- 修正透明遮罩为黑色底

## 1.0.6更新

- 增加navigationBar的相关适配

## 1.0.5更新

- 修正一些错误

## 1.0.4更新

- 增加一些类的方法

## 1.0.3更新

- 修正一些类的名字，避免冲突，或者理解困难
- 

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

#### 读取Res文件夹中的图片资源

- getImageFromRes();

```java
/**
* @param context 上下文
* @param resId 图片资源id
* @return 对应图片的bitmap
*/
Bitmap getImageFromRes(Context context, int resId)
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

## SPUtils

#### SharedPreferences工具类

```java
/**
* 对应的方法顺序为
* 写入boolean
* 读取boolean
* 写入string
* 读取string
* 写入int
* 读取int
* 写入long
* 读取long
* 移除某一项
* 完全移除sp的内容
*/
void putBoolean(String key, boolean value, Context context)

boolean getBoolean(String key, boolean defValue, Context context)

void putString(String key, String value, Context context)

String getString(String key, String defValue, Context context)

void putInt(String key, int value, Context context)

int getInt(String key, int defValue, Context context)

void putLong(String key, long value, Context context)

long getLong(String key, int defValue, Context context)

void remove(String key, Context context)

void clear(Context context)
```

## TranslucentUtils

#### 状态栏和导航栏沉浸式的相关代码

```java
/**
* 只沉浸状态栏
* Android4.4的手机为阴影沉浸
* Android5.0以上为完全透明沉浸
*/
void setTranslucentTOP(Activity activity)

/**
* 只沉浸导航栏
* Android7.0以下的手机为阴影沉浸
* Android8.0以上为完全透明沉浸
*/
void setTranslucentBottom(Activity activity)

/**
* 同时沉浸状态栏和导航栏
* Android4.4的手机为阴影渐变沉浸
* Android5.0以上，Android7.0以下为完全透明状态栏，阴影沉浸导航栏
* Android8.0以上为完全透明沉浸
*/
void setTranslucentBoth(Activity activity)
```

## BarUtils

#### 状态栏跟导航栏，用来动态撑空间的工具类

```java
/**
* 自动撑状态栏的高度
* @param statusBarId 状态栏位置的ResId
*/
void autoFitStatusBar(Activity activity, int statusBarId)

/**
* 自动撑导航栏的高度
* @param navigationBarId 导航栏位置的ResId
*/
void autoFitNavBar(Activity activity, int navigationBarId)

/**
* 同时撑导航栏和状态栏
* @param statusBarId 状态栏位置的ResId
* @param navigationBarId 导航栏位置的ResId
*/
void autoFitBothBar(Activity activity, int statusBarId, int navigationBarId)

/**
* 隐藏导航栏
* @param activity 上下文
*/
void hideNavigationBar(Activity activity)

/**
* 显示状态栏
* @param activity 上下文
*/
void showStatusBar(Activity activity)

/**
* 隐藏状态栏
* @param activity 上下文
*/
void hideStatusBar(Activity activity)
```

## StatusBarFontColorControler

#### 状态栏字体颜色

- setStatusBarMode();

```java
/**
* 动态适配状态栏的字体颜色，大多数情况实在Android6.0以上生效
* @param activity 上下文
* @param darkFont 是否为深色字体
* @return 是否匹配成功，true代表执行了某个区块的逻辑，false代表没有匹配成功
*/
boolean setStatusBarMode(Activity activity, boolean darkFont)
```

## NavBarFontColorControler

#### 导航栏字体颜色

- setNavBarMode();

```java
/**
* Android8.0以上才会生效，与沉浸式工具配合使用
* @param activity 上下文
* @param darkFont 是否为深色字体
*/
void setNavBarMode(Activity activity,boolean darkFont)
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
