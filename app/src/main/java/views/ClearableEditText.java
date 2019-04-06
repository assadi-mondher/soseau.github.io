package views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import app.AppController;

/**
 * Created by jazz on 14/03/2017.
 */

public class ClearableEditText extends AppCompatEditText implements
        View.OnTouchListener, View.OnFocusChangeListener, GenericTextWatcher.GenericTextWatcherListener {

    private Drawable mDrawable;
    private OnTouchListener mOnTouchListener;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTextChangedListener mTextListener;

    public ClearableEditText(Context context) {
        super(context);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.mOnTouchListener = l;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.mOnFocusChangeListener = f;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - mDrawable.getIntrinsicWidth());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                    setRightIconVisible(false);
                }
                return true;
            }
        }
        return (mOnTouchListener != null) && mOnTouchListener.onTouch(v, event);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (StringUtils.isBlank(getText())) {
                //no text
                setRightIconVisible(false);
            } else {
                displayClearIcon();
            }
        } else {
            setRightIconVisible(false);
        }
        if (mOnFocusChangeListener != null && !(mOnFocusChangeListener instanceof ClearableEditText)) {
            mOnFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            if (StringUtils.isBlank(getText())) {
                //no text
                setRightIconVisible(false);
            } else {
                displayClearIcon();
            }
        }
    }

    @Override
    public void refreshDisplay(EditText view, String text, boolean shouldShowOptinMail, boolean shouldShowOptinSMS, boolean shouldShowOptinPost) {
        if (this.mTextListener != null) {
            this.mTextListener.refreshDisplayAfterTextChange(shouldShowOptinMail, shouldShowOptinSMS, shouldShowOptinPost);
        }
    }

    private void init() {
        mDrawable = getCompoundDrawables()[2];
        if (mDrawable != null) {
            mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
            setRightIconVisible(true);
        } else {
            mDrawable = ContextCompat.getDrawable(AppController.getInstance().getApplicationContext(), android.R.drawable.presence_offline);
            mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        }
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
    }

    protected void setRightIconVisible(boolean shouldShowRigthIcon) {
        Drawable x;
        if (shouldShowRigthIcon) {
            x = getCompoundDrawables()[2];
        } else {
            x = null;
        }
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
    }

    private void displayClearIcon() {
        Drawable mDrawable = ContextCompat.getDrawable(AppController.getInstance().getApplicationContext(), android.R.drawable.presence_offline);
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mDrawable, getCompoundDrawables()[3]);
    }

    public void setTextWatcherActive() {
        if (this.mTextListener != null) {
            addTextChangedListener(new GenericTextWatcher(this, this, true));
        }
    }

    public void setListener(OnTextChangedListener listener) {
        this.mTextListener = listener;
        setTextWatcherActive();
    }

    public interface OnTextChangedListener {
        void refreshDisplayAfterTextChange(boolean shouldShowOptinMail, boolean shouldShowOptinSMS, boolean shouldShowOptinPost);
    }
}