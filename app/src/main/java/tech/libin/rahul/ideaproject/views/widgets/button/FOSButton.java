package tech.libin.rahul.ideaproject.views.widgets.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.utils.Fonts;

/**
 * Created by 10945 on 10/26/2016.
 */

public class FOSButton extends Button {
    public FOSButton(Context context) {
        super(context);

        init(context, null);
    }

    public FOSButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public FOSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FOSButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FOSButton, 0, 0);

            try {
                int font = attributes.getInteger(R.styleable.FOSButton_fos_button_font, 0);
                int fontStyle = attributes.getInteger(R.styleable.FOSButton_fos_button_fontstyle, 0);

                switch (font) {
                    case 0:
                        setFont(fontStyle, context, Fonts.GEOSANS_FONT);

                    case 1:
                        setFont(fontStyle, context, Fonts.METANOIA_FONT);
                        break;

                    default:
                        Typeface type = Typeface.createFromAsset(context.getAssets(), Fonts.DEFAULT_FONT);
                        this.setTypeface(type);
                }


            } finally {
                attributes.recycle();
            }

        } else {
            Typeface type = Typeface.createFromAsset(context.getAssets(), Fonts.DEFAULT_FONT);
            this.setTypeface(type);
        }
    }

    private void setFont(int fontStyle, Context context, String font) {

        switch (fontStyle) {
            case 0:
                Typeface type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            case 1:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            case 2:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            case 3:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;

            default:
                type = Typeface.createFromAsset(context.getAssets(), font);
                this.setTypeface(type);
                break;
        }
    }
}
