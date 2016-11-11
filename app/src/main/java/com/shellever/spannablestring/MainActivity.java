package com.shellever.spannablestring;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


// http://blog.csdn.net/harvic880925/article/details/38984705
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mSpanFlagsTv;
    private EditText mSpanFlagsEt;

    private TextView mForegroundColorSpanTv;
    private Button mSetForegroundColorSpanBtn;

    private TextView mBackgroundColorSpanTv;
    private Button mSetBackgroundColorSpanBtn;

    private TextView mAbsoluteSizeSpanTv;
    private Button mSetAbsoluteSizeSpanBtn;

    private TextView mRelativeSizeSpanTv;
    private Button mSetRelativeSizeSpanBtn;

    private TextView mTypefaceSpanTv;
    private Button mSetTypefaceSpanBtn;

    private TextView mStyleSpanTv;
    private Button mSetStyleSpanBtn;

    private TextView mStrikethroughSpanTv;
    private Button mSetStrikethroughSpanBtn;

    private TextView mUnderlineSpanTv;
    private Button mSetUnderlineSpanBtn;

    private TextView mImageSpanTv;
    private Button mSetImageSpanBtn;

    private TextView mUrlLinkTv;
    private TextView mEmailLinkTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        showAutoLinkTextView();
    }

    private void initView() {
        mSpanFlagsTv = (TextView) findViewById(R.id.tv_span_flags);
        mSpanFlagsEt = (EditText) findViewById(R.id.et_span_flags);
        SpannableStringBuilder spb = new SpannableStringBuilder();
        spb.append(getString(R.string.text_happy));
        spb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mSpanFlagsEt.setText(spb);
        mSpanFlagsTv.setText(R.string.text_span_flags);

        mForegroundColorSpanTv = (TextView) findViewById(R.id.tv_foreground_color_span);
        mSetForegroundColorSpanBtn = (Button) findViewById(R.id.btn_foreground_color_span_set);
        mForegroundColorSpanTv.setText(R.string.text_foreground_color_span);
        mSetForegroundColorSpanBtn.setOnClickListener(this);

        mBackgroundColorSpanTv = (TextView) findViewById(R.id.tv_background_color_span);
        mSetBackgroundColorSpanBtn = (Button) findViewById(R.id.btn_background_color_span_set);
        mBackgroundColorSpanTv.setText(R.string.text_background_color_span);
        mSetBackgroundColorSpanBtn.setOnClickListener(this);

        mAbsoluteSizeSpanTv = (TextView) findViewById(R.id.tv_absolute_size_span);
        mSetAbsoluteSizeSpanBtn = (Button) findViewById(R.id.btn_absolute_size_span_set);
        mAbsoluteSizeSpanTv.setText(R.string.text_absolute_size_span);
        mSetAbsoluteSizeSpanBtn.setOnClickListener(this);

        mRelativeSizeSpanTv = (TextView) findViewById(R.id.tv_relative_size_span);
        mSetRelativeSizeSpanBtn = (Button) findViewById(R.id.btn_relative_size_span_set);
        mRelativeSizeSpanTv.setText(R.string.text_relative_size_span);
        mSetRelativeSizeSpanBtn.setOnClickListener(this);

        mTypefaceSpanTv = (TextView) findViewById(R.id.tv_typeface_span);
        mSetTypefaceSpanBtn = (Button) findViewById(R.id.btn_typeface_span_set);
        mTypefaceSpanTv.setText(R.string.text_type_face_span);
        mSetTypefaceSpanBtn.setOnClickListener(this);

        mStyleSpanTv = (TextView) findViewById(R.id.tv_style_span);
        mSetStyleSpanBtn = (Button) findViewById(R.id.btn_style_span_set);
        mStyleSpanTv.setText(R.string.text_style_span);
        mSetStyleSpanBtn.setOnClickListener(this);

        mStrikethroughSpanTv = (TextView) findViewById(R.id.tv_strikethrough_span);
        mSetStrikethroughSpanBtn = (Button) findViewById(R.id.btn_strikethrough_span_set);
        mStrikethroughSpanTv.setText(R.string.text_strikethrough_span);
        mSetStrikethroughSpanBtn.setOnClickListener(this);

        mUnderlineSpanTv = (TextView) findViewById(R.id.tv_underline_span);
        mSetUnderlineSpanBtn = (Button) findViewById(R.id.btn_underline_span_set);
        mUnderlineSpanTv.setText(R.string.text_underline_span);
        mSetUnderlineSpanBtn.setOnClickListener(this);

        mImageSpanTv = (TextView) findViewById(R.id.tv_image_span);
        mSetImageSpanBtn = (Button) findViewById(R.id.btn_image_span_set);
        mImageSpanTv.setText(R.string.text_image_span);
        mSetImageSpanBtn.setOnClickListener(this);
    }

    private static boolean foreground_flag = false;
    private static boolean background_flag = false;
    private static boolean absolute_flag = false;
    private static boolean relative_flag = false;
    private static boolean typeface_flag = false;
    private static boolean style_flag = false;
    private static boolean strikethrough_flag = false;
    private static boolean underline_flag = false;
    private static boolean image_flag = false;

    @Override
    public void onClick(View v) {
        SpannableString sp;
        switch (v.getId()) {
            case R.id.btn_foreground_color_span_set:
                if (!foreground_flag) {
                    String foreground = getResources().getString(R.string.text_foreground_color_span);
                    sp = new SpannableString(foreground);
                    sp.setSpan(new ForegroundColorSpan(Color.RED), 0, foreground.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mForegroundColorSpanTv.setText(sp);
                    foreground_flag = true;
                } else {
                    mForegroundColorSpanTv.setText(R.string.text_foreground_color_span);
                    foreground_flag = false;
                }
                break;

            case R.id.btn_background_color_span_set:
                if (!background_flag) {
                    String background = getResources().getString(R.string.text_background_color_span);
                    sp = new SpannableString(background);
                    sp.setSpan(new BackgroundColorSpan(Color.parseColor("#8FBC8F")), 0, background.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mBackgroundColorSpanTv.setText(sp);
                    background_flag = true;
                } else {
                    mBackgroundColorSpanTv.setText(R.string.text_background_color_span);
                    background_flag = false;
                }
                break;

            case R.id.btn_absolute_size_span_set:
                if (!absolute_flag) {
                    String absolute = getString(R.string.text_absolute_size_span);
                    sp = new SpannableString(absolute);
                    // 设置字体大小为12dp
                    sp.setSpan(new AbsoluteSizeSpan(12, true), 0, absolute.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mAbsoluteSizeSpanTv.setText(sp);
                    absolute_flag = true;
                } else {
                    mAbsoluteSizeSpanTv.setText(R.string.text_absolute_size_span);
                    absolute_flag = false;
                }
                break;

            case R.id.btn_relative_size_span_set:
                if (!relative_flag) {
                    String relative = getString(R.string.text_relative_size_span);
                    sp = new SpannableString(relative);
                    sp.setSpan(new RelativeSizeSpan(1.5f), 0, relative.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mRelativeSizeSpanTv.setText(sp);
                    relative_flag = true;
                } else {
                    mRelativeSizeSpanTv.setText(R.string.text_relative_size_span);
                    relative_flag = false;
                }
                break;

            case R.id.btn_typeface_span_set:
                if (!typeface_flag) {
                    String typeface = getString(R.string.text_type_face_span);
                    sp = new SpannableString(typeface);
                    // 设置字体(default,default-bold,monospace,serif,sans-serif)
                    sp.setSpan(new TypefaceSpan("serif"), 0, typeface.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mTypefaceSpanTv.setText(sp);
                    typeface_flag = true;
                } else {
                    mTypefaceSpanTv.setText(R.string.text_type_face_span);
                    typeface_flag = false;
                }
                break;

            case R.id.btn_style_span_set:
                if (!style_flag) {
                    String style = getString(R.string.text_style_span);
                    sp = new SpannableString(style);
                    sp.setSpan(new StyleSpan(Typeface.ITALIC), 0, style.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mStyleSpanTv.setText(sp);
                    style_flag = true;
                } else {
                    mStyleSpanTv.setText(R.string.text_style_span);
                    style_flag = false;
                }
                break;

            case R.id.btn_strikethrough_span_set:
                if (!strikethrough_flag) {
                    String strike = getString(R.string.text_strikethrough_span);
                    sp = new SpannableString(strike);
                    sp.setSpan(new StrikethroughSpan(), 0, strike.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mStrikethroughSpanTv.setText(sp);
                    strikethrough_flag = true;
                } else {
                    mStrikethroughSpanTv.setText(R.string.text_strikethrough_span);
                    strikethrough_flag = false;
                }
                break;

            case R.id.btn_underline_span_set:
                if (!underline_flag) {
                    String underline = getString(R.string.text_underline_span);
                    sp = new SpannableString(underline);
                    sp.setSpan(new UnderlineSpan(), 0, underline.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mUnderlineSpanTv.setText(sp);
                    underline_flag = true;
                } else {
                    mUnderlineSpanTv.setText(R.string.text_underline_span);
                    underline_flag = false;
                }
                break;

            case R.id.btn_image_span_set:
                if (!image_flag) {
                    String image = getString(R.string.text_image_span);
                    sp = new SpannableString(image);
                    // 获取Drawable资源
                    Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_face);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    sp.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), 0, image.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mImageSpanTv.setText(sp);
                    image_flag = true;
                } else {
                    mImageSpanTv.setText(R.string.text_image_span);
                    image_flag = false;
                }
                break;
        }
    }

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
}
