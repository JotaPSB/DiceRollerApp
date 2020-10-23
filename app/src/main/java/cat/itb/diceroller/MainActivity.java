package cat.itb.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button rollButton;
    ImageView resultImageViewOne;
    Resources res;
    ImageView resultImageViewTwo;
    int randomNumberDice1;
    int randomNumberDice2;
    Button restartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int[] dicesImages = {
                R.drawable.dice_1,
                R.drawable.dice_2,
                R.drawable.dice_3,
                R.drawable.dice_4,
                R.drawable.dice_5,
                R.drawable.dice_6
        };
        restartButton = findViewById(R.id.restartButton);

        rollButton = findViewById(R.id.roll_button);

        resultImageViewOne = findViewById(R.id.resultatImage1);

        resultImageViewTwo = findViewById(R.id.resultatImage2);

        res = getResources();

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rollButton.setText(R.string.button_clicked);

                randomNumberDice1 = (int) (Math.random()*6);

                randomNumberDice2 = (int) (Math.random()*6);

                resultImageViewOne.setImageDrawable(ResourcesCompat.getDrawable(res,dicesImages[randomNumberDice1],null));

                resultImageViewTwo.setImageDrawable(ResourcesCompat.getDrawable(res,dicesImages[randomNumberDice2],null));

                resultImageViewOne.setVisibility(View.VISIBLE);

                resultImageViewTwo.setVisibility(View.VISIBLE);

                jackpot(randomNumberDice1,randomNumberDice2);

                Toast.makeText(MainActivity.this,"You have clicked the button",Toast.LENGTH_SHORT).show();

                restartButton.setVisibility(View.VISIBLE);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartButton.setVisibility(View.INVISIBLE);
                resultImageViewOne.setVisibility(View.INVISIBLE);
                resultImageViewTwo.setVisibility(View.INVISIBLE);
                rollButton.setText(R.string.roll_button_text);
            }
        });

        resultImageViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNumberDice1 =(int) (Math.random()*6);
                resultImageViewOne.setImageDrawable(ResourcesCompat.getDrawable(res,dicesImages[randomNumberDice1],null));
                jackpot(randomNumberDice1,randomNumberDice2);
            }
        });
        resultImageViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNumberDice2 =(int) (Math.random()*6);
                resultImageViewTwo.setImageDrawable(ResourcesCompat.getDrawable(res,dicesImages[randomNumberDice2],null));
                jackpot(randomNumberDice1,randomNumberDice2);
            }
        });

    }
    public void jackpot(int dice1, int dice2){
        if(dice1 == 5 && dice2==5){
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.custom_toast_container));
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(R.string.jackpot);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP,0,0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }

}