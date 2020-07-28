package com.codemybrainsout.rating;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RelativeLayout rlRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlRate = findViewById(R.id.rlRate);
        rlRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {

        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .session(3)
                .threshold(3)
                .ratingBarColor(R.color.yellow)
                .positiveButtonText("Later")
                .negativeButtonText("Never")
                .playstoreUrl("https://github.com/codemybrainsout/smart-app-rate")
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        Log.i(TAG, "Feedback:" + feedback);
                    }
                }).onDialogDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "dismissed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        boolean result = ratingDialog.showWithResult();
        Toast.makeText(this, "showResult: " + result, Toast.LENGTH_SHORT).show();
    }
}
