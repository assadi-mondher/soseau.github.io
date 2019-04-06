package app.com.soseau.fragments;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.Volley;
import com.heinrichreimersoftware.materialintro.app.SlideFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import Utils.Util;
import app.com.soseau.R;
import app.com.soseau.activity.WebService;
import app.com.soseau.adapters.ImageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jazz on 05/01/2017.
 */
public class FourthReportFragment extends SlideFragment implements Response.ErrorListener, Response.Listener<String> {

    private static final int PICK_IMAGE = 1;

    private static final int PICK_Camera_IMAGE = 2;

    final private int REQUEST_CODE_ASK_PERMISSIONS_CAMERA = 125;

    final private int REQUEST_CODE_ASK_PERMISSIONS_STORAGE = 126;

    private String[] list_choice = {"Caméra", "Galerie"};

    private int pick_photo;

    private Uri imageUri;

    private ArrayList<String> imageUrls = new ArrayList<>();

    private ArrayList<String> selectedItems;

    private ImageAdapter imageAdapter;

    @BindView(R.id.gridview)
    GridView image_gridView;

    private FirstReportFragment mFirstReportFragment;

    private SecondReportFragment mSecondReportFragment;

    private ThirdReportFragment mThirdReportFragment;

    private SecondPrimeReportFragment mSecondPrimeReportFragment;

    public static FourthReportFragment newInstance() {
        return new FourthReportFragment();
    }

    private RequestQueue requestQueue;


    @BindView(R.id.mTextViewPrenom)
    EditText mTextViewPrenom;

    @BindView(R.id.mTextViewNomFamille)
    EditText mTextViewNomFamille;

    @BindView(R.id.mTextViewEmail)
    EditText mTextViewEmail;

    private boolean sendInProgress = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.report_form_4, container, false);
        ButterKnife.bind(this, root);
        ImageLoader imageLoader = ImageLoader.getInstance();
        image_gridView.setVisibility(View.VISIBLE);
        imageAdapter = new ImageAdapter(getActivity(), imageUrls, imageLoader);
        image_gridView.setAdapter(imageAdapter);
        mFirstReportFragment = FirstReportFragment.get();
        mSecondReportFragment = SecondReportFragment.get();
        mThirdReportFragment = ThirdReportFragment.get();
        mSecondPrimeReportFragment = SecondPrimeReportFragment.get().get();


        /*
        if(BuildConfig.DEBUG)
        {
            //mTextViewEmail.setText("aa@gmail.com");
            mTextViewNomFamille.setText("hatem");
            mTextViewPrenom.setText("Gomez");
        }
        */

        return root;
    }

    @Override
    public boolean canGoForward()
    {


         if(!Util.getContent(mTextViewEmail).isEmpty() &&!Util.isValidEmail(Util.getContent(mTextViewEmail)))
        {
            Snackbar snackbar = Snackbar
                    .make(getView(), getString(R.string.msg_invalid_email), Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();

            return false;
        }

        else if(mTextViewEmail != null && !Util.getContent(mTextViewEmail).isEmpty() &&
                Util.isValidEmail(Util.getContent(mTextViewEmail)))

        {
            requestQueue = Volley.newRequestQueue(getActivity());
            sendReport();
            return false;
        }

        else
        {
            return false;
        }


    }

    public void getCameraPermission() {
        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
            return;
        }
    }

    public void getStoragePermission() {
        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS_STORAGE);
            return;
        }
    }

    @OnClick(R.id.mImageButtonAddImage)
    void addPhoto() {

        getCameraPermission();
        getStoragePermission();

        selectPhotoDialog();
    }

    private void selectPhotoDialog() {

        new MaterialDialog.Builder(getActivity())
                .title(R.string.ajouterphoto)
                .items(list_choice)
                .titleColorRes(R.color.mi_text_color_primary_light)
                .itemsColor(ContextCompat.getColor(getActivity(), R.color.mi_text_color_primary_light))
                .itemsColor(ContextCompat.getColor(getActivity(), R.color.mi_text_color_primary_light))
                .backgroundColorRes(R.color.white)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        pick_photo = which;
                        pickPhoto(pick_photo);
                        return true;
                    }
                })

                .show();
    }

    private void pickPhoto(int pick_photo) {

        switch (pick_photo) {
            case 0:
                getCameraPermission();
                ContentValues values = new ContentValues();

                values.put(MediaStore.Images.Media.DESCRIPTION, "Image captured by camera");
                imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, PICK_Camera_IMAGE);
                break;

            case 1:
                getStoragePermission();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = null;
        //deletePhoto.setVisibility(View.GONE);

        switch (requestCode) {

            case PICK_IMAGE:

                if(data != null) {

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();


                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    if (!imageUrls.contains(picturePath)) {
                        Toast.makeText(getActivity(), picturePath, Toast.LENGTH_SHORT).show();
                        imageUrls.add(picturePath);
                        imageAdapter.notifyDataSetChanged();
                        image_gridView.setVisibility(View.VISIBLE);
                        /*
                        if (!imageUrls.isEmpty()) {
                            savephoto.setEnabled(true);
                            isSave = false;
                        }

                        message.setVisibility(View.GONE);
                        */
                    }

                    else
                    {
                        Toast.makeText(getActivity(), getString(R.string.erreuraddphoto), Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case PICK_Camera_IMAGE:
                if (resultCode == getActivity().RESULT_OK) {

                    selectedImageUri = imageUri;

                    if (selectedImageUri != null) {

                        Toast.makeText(getActivity(), getPath(selectedImageUri), Toast.LENGTH_SHORT).show();
                        imageUrls.add(getPath(selectedImageUri));
                        imageAdapter.notifyDataSetChanged();
                        image_gridView.setVisibility(View.VISIBLE);
                        /*
                        if(!imageUrls.isEmpty()) {
                            savephoto.setEnabled(true);
                            isSave = false;
                        }


                        message.setVisibility(View.GONE);
                        */

                    }

                } else if (resultCode == getActivity().RESULT_CANCELED) {
                    Toast.makeText(getActivity(), "Erreur", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Erreur", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if (cursor != null)
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


    /**
     * send report
     */

    private void sendReport()
    {
        if(sendInProgress == false)
        {
            sendInProgress = true;
            new MaterialDialog.Builder(getActivity())
                    .title(getString(R.string.app_name))
                    .content("Envoi en cours...")
                    .theme(Theme.LIGHT)
                    .progress(true, 0)
                    .show();
            WebService.getInstance().updateProfile(getActivity(), mFirstReportFragment, mSecondReportFragment,mSecondPrimeReportFragment, mThirdReportFragment, this, imageUrls, this, this);

        }
    }



    @Override
    public void onErrorResponse(VolleyError error) {

        Log.i("onErrorResponse",error.getMessage());
    }

    @Override
    public void onResponse(String response) {

        //Your response here
        MaterialDialog alert = new MaterialDialog.Builder(getActivity())
                .title("SOS EAU")
                .positiveText("Ok")
                .theme(Theme.LIGHT)
                .cancelable(false)
                .content("Votre entrée a été bien enregistrée et elle sera  publiée sur la carte après validation de l'administrateur merci")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        getActivity().finish();
                    }
                })
                .show();
    }


    public EditText getmTextViewEmail() {
        return mTextViewEmail;
    }

    public EditText getmTextViewNomFamille() {
        return mTextViewNomFamille;
    }

    public EditText getmTextViewPrenom() {
        return mTextViewPrenom;
    }
}
