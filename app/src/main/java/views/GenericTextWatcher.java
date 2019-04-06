package views;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import app.com.soseau.R;

/**
 * Created by jazz on 14/03/2017.
 */

public class GenericTextWatcher implements TextWatcher {

    private final EditText mEditText;
    private GenericTextWatcherListener mListener;
    private boolean isTextWatcherActive;

    private String textBeforeChange;

    public GenericTextWatcher(EditText editText, GenericTextWatcherListener listener, boolean isTextWatcherActive) {
        this.mEditText = editText;
        this.mListener = listener;
        this.isTextWatcherActive = isTextWatcherActive;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isTextWatcherActive()) {
            boolean shouldShowOptinPost = false;
            boolean shouldShowOptinSMS = false;
            boolean shouldShowOptinMail = false;



            if (mListener != null) {
                mListener.refreshDisplay(mEditText, s.toString(), shouldShowOptinMail, shouldShowOptinSMS, shouldShowOptinPost);
            }
        }
    }

    public boolean isTextWatcherActive() {
        return this.isTextWatcherActive;
    }

    public void setListener(GenericTextWatcherListener listener) {
        this.mListener = listener;
    }

    public interface GenericTextWatcherListener {
        void refreshDisplay(EditText view, String text, boolean shouldShowOptinMail, boolean shouldShowOptinSMS, boolean shouldShowOptinPost);
    }
}
