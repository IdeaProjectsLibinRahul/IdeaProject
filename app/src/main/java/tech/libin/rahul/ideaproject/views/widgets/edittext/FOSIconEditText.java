package tech.libin.rahul.ideaproject.views.widgets.edittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.utils.Fonts;


public class FOSIconEditText extends LinearLayout {
    private ImageView imageViewLeft;
    private EditText editText;

    public FOSIconEditText(Context context) {
        super(context);

        init(context);
    }

    public FOSIconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

        setAttributes(context, attrs);
    }

    public FOSIconEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
        setAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FOSIconEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
        setAttributes(context, attrs);
    }

    private void init(Context context) {
        try {


            this.setGravity(Gravity.CENTER_VERTICAL);
            imageViewLeft = new ImageView(context);
            editText = new EditText(context);
//            editText.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            // editText.setBackgroundResource(R.drawable.edit_text_bottom_border);

            editText.setTextColor(getResources().getColor(R.color.gray2X));
            editText.setHintTextColor(getResources().getColor(R.color.baseGray));
            editText.setSingleLine(true);

            Typeface type = Typeface.createFromAsset(context.getAssets(), Fonts.DEFAULT_FONT);
            editText.setTypeface(type);
            LayoutParams editTextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            editTextParams.setMargins(25, 0, 0, 0);
            editText.setLayoutParams(editTextParams);

            LayoutParams imageViewParams = new LayoutParams(50, 50);
            imageViewLeft.setLayoutParams(imageViewParams);

            this.setOrientation(HORIZONTAL);
            this.addView(imageViewLeft);
            this.addView(editText);
            this.setBackgroundColor(getResources().getColor(R.color.lightYellow));
            this.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));
            this.setPadding(25, 10, 25, 1);
        } catch (Exception ex) {
            Log.e("Ecxeption", ex.toString());
        }


    }

    private void setAttributes(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FOSIconEditText, 0, 0);

        try {
            String hint = attributes.getString(R.styleable.FOSIconEditText_hint);
            String text = attributes.getString(R.styleable.FOSIconEditText_textString);
            boolean enableKeyboard = attributes.getBoolean(R.styleable.FOSIconEditText_setKeyboardEnabled,true);
            int inputType = attributes.getInt(R.styleable.FOSIconEditText_inputType, 0);
            int maxLength = attributes.getInt(R.styleable.FOSIconEditText_maxLength, 0);
            Drawable image = attributes.getDrawable(R.styleable.FOSIconEditText_drawableLeft);

            if (hint != null) {
                editText.setHint(hint);
            }

            if(!enableKeyboard)
            {
                editText.setInputType(InputType.TYPE_NULL);

            }

            if (text != null) {
                editText.setText(text);
            }

            if (image != null) {
                imageViewLeft.setImageDrawable(image);
            }
            if (maxLength != 0) {
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(maxLength);
                editText.setFilters(filterArray);
            }

            if (inputType == 0) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            } else if (inputType == 1) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else if (inputType == 3) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
            } else {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            }
        } finally {
            attributes.recycle();
        }
    }

    public String getText() {
        return this.editText.getText().toString();
    }

    public void setText(String text) {
        this.editText.setText(text);
    }

    public EditText getEditText() {
        return editText;
    }

    public void setError(String error) {
        this.editText.setError(error);
    }
}
