package geekbrains.androidlevel1;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

class EmailSender implements View.OnClickListener {
    private final Activity sourceActivity;

    private final String s = String.valueOf(R.string.sendmail);

    public EmailSender(Activity sourceActivity) {
        this.sourceActivity = sourceActivity;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , "help@mail.ru");
        i.putExtra(Intent.EXTRA_SUBJECT, "App issue");
        i.putExtra(Intent.EXTRA_TEXT   , "Error:");

        try {
            sourceActivity.startActivity(Intent.createChooser(i,s));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(sourceActivity, R.string.nomailclienterror, Toast.LENGTH_SHORT).show();
        }
    }
}
