## SpannableString - 实现富文本样式

* 使用下面两个类的setSpan()方法设置不同的样式Span来实现富文本：

SpannableString - 实例化对象时必须传入一个字符串String，之后内容无法改变

SpannableStringBuilder - 实例化对象时可不传入一个String字符串，而用append()来添加内容，可动态拼接多个字符串String

* **setSpan(Object what, int start, int end, int flags);**

**函数说明：**

给SpannableString或SpannableStringBuilder指定范围内的字符串设定Span样式，可以设置多个Span样式，
flags参数标识了当在所标记范围前和标记范围后紧贴着插入新字符时的动作，即是否对新插入的字符应用同样的样式。

**参数说明：**

Object what：对应的各种Span样式

int start：开始应用指定Span的位置，索引从0开始

int end：结束应用指定Span的位置，作用范围为[start, end)

int flags：

```
Spanned.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括。即在指定范围的前面和后面插入新字符都不会应用新样式 
Spanned.SPAN_EXCLUSIVE_INCLUSIVE：前面不包括，后面包括。即仅在指定范围字符的后面插入新字符时会应用新样式
Spanned.SPAN_INCLUSIVE_EXCLUSIVE：前面包括，后面不包括。即仅在指定范围字符的前面插入新字符时会应用新样式
Spanned.SPAN_INCLUSIVE_INCLUSIVE：前后都包括。即在指定范围字符的前面和后面插入新字符时都会应用新样式
```

----

## Linkify - TextView自动跳转应用

通过TextView.setAutoLink(int)设置其Linkify属性，TextView会自动检查其内容，并识别出电话号码、网址、邮箱地址，并标记为超链接，可以点击，点击后便跳转到相应的应用服务程序。

```
Linkify.EMAIL_ADDRESSES - 仅识别出TextView中的邮箱地址并标记为超链接，点击后会弹出发送邮件的对话框来提示用户是否进一步操作
Linkify.PHONE_NUMBERS   - 仅识别出TextView中的电话号码并标记为超链接，点击后会弹出拨打电话的对话框来提示用户是否进一步操作
Linkify.WEB_URLS        - 仅识别出TextView中的网址并标记为超链接，点击后会使用浏览器打开网址
Linkify.ALL             - 识别出所有系统所支持的特殊URI，然后做相应的操作
```

## String/SpannableString/SpannableStringBuilder的类图

从类图中可以清晰的看出：

1. SpannableString实现了Spannable接口，而Spannable接口定义了可变标记的操作集，用于关联或分离标记对象，故SpannableString必须在实例化对象时传入文本内容，实例化后就不能改变其文本内容，只能设置文本的标记对象，即Span样式；

2. SpannableStringBuilder实现Spannable接口的同时也实现了Editable接口，而Editable定义了可变标记和可变文本内容的操作集，故SpannableStringBuilder可以在实例化对象时不传入文本内容，而使用append()方法类逐步添加内容，并通过setSpan()方法设置Span样式。

3. String、SpannableString和SpannableStringBuilder同时实现了CharSequence接口，都可以实现字符的操作。

![uml_class_spannable_string.png](./docs/uml_class_spannable_string.png)

## Span样式设置示例

1. setSpan()方法参数flags中`INCLUSIVE`和`EXCLUSIVE`区别

    ```java
    mSpanFlagsTv = (TextView) findViewById(R.id.tv_span_flags);
    mSpanFlagsEt = (EditText) findViewById(R.id.et_span_flags);
    SpannableStringBuilder spb = new SpannableStringBuilder();
    spb.append(getString(R.string.text_happy));
    spb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    mSpanFlagsEt.setText(spb);
    mSpanFlagsTv.setText(R.string.text_span_flags);
    ```

2. ForegroundColorSpan - 字体颜色 (Color.CYAN)

    ```java
    String foreground = getResources().getString(R.string.text_foreground_color_span);
    SpannableString sp = new SpannableString(foreground);
    sp.setSpan(new ForegroundColorSpan(Color.RED), 0, foreground.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mForegroundColorSpanTv.setText(sp);
    ```

3. BackgroundColorSpan - 字体背景颜色 (Color.parseColor("#8FBC8F"))

    ```java
    String background = getResources().getString(R.string.text_background_color_span);
    SpannableString sp = new SpannableString(background);
    sp.setSpan(new BackgroundColorSpan(Color.parseColor("#8FBC8F")), 0, background.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mBackgroundColorSpanTv.setText(sp);
    ```

4. AbsoluteSizeSpan - 字体大小 (参数是绝对数值)

    ```java
    String absolute = getString(R.string.text_absolute_size_span);
    SpannableString sp = new SpannableString(absolute);
    // 设置字体大小为12，true表示单位为dp
    sp.setSpan(new AbsoluteSizeSpan(12, true), 0, absolute.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mAbsoluteSizeSpanTv.setText(sp);
    ```

5. RelativeSizeSpan - 字体大小 (参数是相对于默认字体大小的倍数)

    ```java
    String relative = getString(R.string.text_relative_size_span);
    SpannableString sp = new SpannableString(relative);
    sp.setSpan(new RelativeSizeSpan(1.5f), 0, relative.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mRelativeSizeSpanTv.setText(sp);
    ```

6. TypefaceSpan - 字体 (serif/sans-serif)

    ```java
    String typeface = getString(R.string.text_type_face_span);
    SpannableString sp = new SpannableString(typeface);
    // 设置字体(default,default-bold,monospace,serif,sans-serif)
    sp.setSpan(new TypefaceSpan("serif"), 0, typeface.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mTypefaceSpanTv.setText(sp);
    ```

7. StyleSpan - 字体样式 (粗体/斜体) (Typeface.BOLD_ITALIC)

    ```java
    String style = getString(R.string.text_style_span); 
    SpannableString sp = new SpannableString(style);
    sp.setSpan(new StyleSpan(Typeface.ITALIC), 0, style.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mStyleSpanTv.setText(sp);
    ```

8. StrikethroughSpan - 删除线

    ```java
    String strike = getString(R.string.text_strikethrough_span);
    SpannableString sp = new SpannableString(strike);
    sp.setSpan(new StrikethroughSpan(), 0, strike.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mStrikethroughSpanTv.setText(sp);
    ```

9. UnderlineSpan - 下划线

    ```java
    String underline = getString(R.string.text_underline_span);
    SpannableString sp = new SpannableString(underline);
    sp.setSpan(new UnderlineSpan(), 0, underline.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mUnderlineSpanTv.setText(sp);
    ```

10. ImageSpan - 图片置换 (使用图片将文字替换)

    ```java
    String image = getString(R.string.text_image_span);
    SpannableString sp = new SpannableString(image);
    // 获取Drawable资源
    Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_face);
    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    sp.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), 0, image.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mImageSpanTv.setText(sp);
    ```

## TextView自动跳转设置

```java
private void showAutoLinkTextView() {
    mUrlLinkTv = (TextView) findViewById(R.id.tv_link_url);
    mEmailLinkTv = (TextView) findViewById(R.id.tv_link_email);

    // Set the attribute first, then set the text. Otherwise, it won't work
    // or set 'android:autoLink' in layout xml
    mUrlLinkTv.setAutoLinkMask(Linkify.WEB_URLS);
    mEmailLinkTv.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);

    mUrlLinkTv.setText("Home: www.baidu.com");
    mEmailLinkTv.setText("Email: shellever@163.com");
}
```

#### 效果演示

![spannable_string_linkify_5m.gif](./docs/spannable_string_linkify_5m.gif)

## Links

[SpannableString与SpannableStringBuilder][1]

[android - SpannableString或SpannableStringBuilder以及string.xml文件中的整型和string型代替][2]

[1]: http://blog.csdn.net/harvic880925/article/details/38984705
[2]: http://blog.csdn.net/fengkuanghun/article/details/7904284