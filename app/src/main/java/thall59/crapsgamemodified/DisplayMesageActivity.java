package thall59.crapsgamemodified;

import android.content.DialogInterface;
import android.content.Intent;
import java.text.NumberFormat; // currency formatting

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity; // base class for activities
import android.os.Bundle; // saving state information
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.view.View;
import android.widget.Button;
import android.widget.EditText; // for buyin amount input
import android.widget.TextView; // displaying text


public class DisplayMesageActivity extends AppCompatActivity {

    // currency formatter
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private Button newgameButton;
    private Button rulesButton;
    private TextView buyinAmountTextView; //shows formatted bill amount
    private EditText buyinAmount;
    double buyinMoney = 0.0;
    Intent newGameIntent;



    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass's version
        setContentView(R.layout.activity_display_mesage); // inflate the GUI
        getSupportActionBar().setDisplayShowHomeEnabled(true); // display icon on actionbar
        getSupportActionBar().setIcon(R.mipmap.ic_launcher); // icon
        // Locate the button in activity_display_mesage.xml
        newgameButton = (Button) findViewById(R.id.newgameButton);
        buyinAmount = (EditText) findViewById(R.id.buyinAmount);


        // Capture button clicks
        newgameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GroupAssignment2.class
                Intent newGameIntent = new Intent(DisplayMesageActivity.this, GroupAssignment2.class);

                newGameIntent.putExtra("buyin", buyinAmountTextView.getText().toString());

                startActivity(newGameIntent);
                recreate();
            }
        }); // end newgameButton OnClickListener


        // get reference to the TextViews
        // that the craps game interacts with programmatically
        buyinAmountTextView =
                (TextView) findViewById(R.id.amount_display_textview);

        // update GUI based on buyinAmount
        buyinAmountTextView.setText(
                currencyFormat.format(buyinMoney));

        // set buyinAmount edit text's TextWatcher
        EditText buyinAmountEditText =
                (EditText) findViewById(R.id.buyinAmount);
        buyinAmountEditText.addTextChangedListener(buyinAmountEditTextWatcher);


    } // end method onCreate


    // event-handling object that responds to buyinAmountEditText's events
    private TextWatcher buyinAmountEditTextWatcher =
            new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count){

                    // convert buyinAmountEditText's into a double
                    try{
                        buyinMoney = Double.parseDouble(s.toString()) / 100.0;
                    } // end try

                    catch (NumberFormatException e){

                        buyinMoney = 0.0; // default if an exception occurs
                    } // end catch

                    // display currency formatted bill amount
                    buyinAmountTextView.setText(currencyFormat.format(buyinMoney));

                } // end method onTextChanged

                @Override
                public void afterTextChanged(Editable s){
                } // end afterTextChanged

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after){
                } // end method beforeTextChanged

            }; // end buyinAmountEditTextWatcher


    public double getBuyinAmount(){
        return buyinMoney;
    }


    public void rulesClick(){
        AlertDialog.Builder rules = new AlertDialog.Builder(DisplayMesageActivity.this);
        rules.setCancelable(false);
        rules.setTitle("RULES");
        rules.setMessage("Here are the rules: "
        + "\n"
        + "\n1. Minimum you can buy-in is $10"
        + "\n2. Maximum that you can buy-in is $1000"
        + "\n3. The amount for each chip is 10"
        + "\n4. If you get 7 or 11 on your first roll, you win."
        + "\n5. House wins if your first roll is 2, 3, or 12."
        + "\n6. If you roll 4, 5, 6, 8, 9, or 10 the number"
        + "\n    will become the players point and the same"
        + "\n    number must be rolled again to win."
        + "\n    If you roll a 7, House wins.");
        rules.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = rules.create();
        alert.show();
    }

    public void rulesButton (View view){

        rulesClick();
    }

}
