package thall59.crapsgamemodified;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Currency;
import java.util.Random;
import android.content.Intent;





public class GroupAssignment2 extends AppCompatActivity {


    // create random number generator for use in method rollDice
    private Random randomNumbers = new Random();


    private TextView winslossTextView;
    private TextView rollNumberTextView;
    private TextView resultsView;
    private TextView bankAmount;
    private TextView betAmount;
    private ImageView dieOneImage;
    private ImageView dieTwoImage;
    private Button homeButton;
    private Button rollButton;
    int dice1;
    int dice2;
    int sum;
    private int point = 0;
    private int wins;
    private int loss;
    int over = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_assignment2);
        getSupportActionBar().setDisplayShowHomeEnabled(true); // display icon on actionbar
        getSupportActionBar().setIcon(R.mipmap.ic_launcher); // icon

        final DisplayMesageActivity globalVariable = (DisplayMesageActivity) getApplicationContext();

        final double buyinAmount = globalVariable.getBuyinAmount();


        resultsView = (TextView) findViewById(R.id.results);
        betAmount = (TextView) findViewById(R.id.betAmount);
        dieOneImage = (ImageView) findViewById(R.id.diceOneView);
        dieTwoImage = (ImageView) findViewById(R.id.diceTwoView);
        winslossTextView = (TextView) findViewById(R.id.winsloss);
        rollNumberTextView = (TextView) findViewById(R.id.rollNumber);
        rollButton = (Button) findViewById(R.id.rollDieButton);
        bankAmount = (TextView) findViewById(R.id.bankAmount);

        // Locate the button in activity_group_assignment2.xml
        homeButton = (Button) findViewById(R.id.homeButton);

        Intent bankAmountIntent = getIntent();
        Bundle bundle = bankAmountIntent.getExtras();
        String buyin = bundle.getString("buyin");

        bankAmount.setText(buyin.toString());




        // Capture button clicks
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start DisplayMesageActivity.class
                Intent homePage = new Intent(GroupAssignment2.this, DisplayMesageActivity.class);
                startActivity(homePage);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }



    public void craps() {

        int sumOfDice = rollDice();

        if (over == 30) {
            over = 40;
        }
        if (over == 20) {

            switch (sumOfDice) {
                case 7:
                case 11:
                    resultsView.setText("YOU WON!");
                    rollButton.setEnabled(false);
                    wins++;
                    displayWins();
                    break;
                case 2: // lose with 2 on first roll
                case 3: // lose with 3 on first roll
                case 12: // lose with 12 on first roll
                    resultsView.setText("Sorry, you lose!");
                    rollButton.setEnabled(false);
                    loss++;
                    displayWins();
                    break;
                default: // did not win or lose, so remember point
                    point = sumOfDice; // remember the point
                    over = 30;
                    resultsView.setText("You rolled " + point + ".\n" + "You must roll " + point
                            + " to win." + "\n" + "If you roll a 7 you lose." + "\n" + "Roll Again!");
                    displayWins();
                    break;

            }

        }
        if (over == 40){

            if (point == sumOfDice){
                resultsView.setText("YOU WON!");
                rollButton.setEnabled(false);
                over = 20;
                wins++;
                displayWins();
            }
            if (sumOfDice == 7){
                resultsView.setText("Sorry, you lose!");
                rollButton.setEnabled(false);
                over = 20;
                loss++;
                displayWins();
            }
            if (sumOfDice != point && sumOfDice != 7){
                //over =30;
                resultsView.setText("You rolled " + point + ".\n" + "You must roll " + point
                        + " to win." + "\n" + "If you roll a 7 you lose." + "\n" + "Roll Again!");
                displayWins();
            }
        }
    }




    public int rollDice() {

        dice1 = 1 + randomNumbers.nextInt(6);

        dice2 = 1 + randomNumbers.nextInt(6);

        int sum = dice2 + dice1;

        rollNumberTextView.setText(String.valueOf("Roll Number: " + dice1 +
                " + " + dice2 + " = " + sum));


        switch (dice1){
            case 1:
                dieOneImage.setImageResource(R.drawable.die1);
                break;
            case 2:
                dieOneImage.setImageResource(R.drawable.die2);
                break;
            case 3:
                dieOneImage.setImageResource(R.drawable.die3);
                break;
            case 4:
                dieOneImage.setImageResource(R.drawable.die4);
                break;
            case 5:
                dieOneImage.setImageResource(R.drawable.die5);
                break;
            case 6:
                dieOneImage.setImageResource(R.drawable.die6);
                break;

        }switch (dice2){
            case 1:
                dieTwoImage.setImageResource(R.drawable.die1);
                break;
            case 2:
                dieTwoImage.setImageResource(R.drawable.die2);
                break;
            case 3:
                dieTwoImage.setImageResource(R.drawable.die3);
                break;
            case 4:
                dieTwoImage.setImageResource(R.drawable.die4);
                break;
            case 5:
                dieTwoImage.setImageResource(R.drawable.die5);
                break;
            case 6:
                dieTwoImage.setImageResource(R.drawable.die6);
                break;

        }


        return dice1 + dice2;
    }


    // will start the craps game when the button is rolled
    public void rollButton(View view) {

        craps();
    }

    // displays the player wins and house wins
    private void displayWins(){
        winslossTextView.setText("Player Wins: " + wins + "   House Wins: " + loss);
    }

    // newGame clears the screen of everything but the Wins/Loss score and the bankAmount
    public void newGame (View view){

        rollButton.setEnabled(true);

        rollNumberTextView.setText("Roll Number: ");
        resultsView.setText(String.valueOf("Results: "));

        dice1 = 0;

        dice2 = 0;

        sum = 0;

        point = 0;

    }


}
