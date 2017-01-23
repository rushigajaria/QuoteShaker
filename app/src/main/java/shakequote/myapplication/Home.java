package shakequote.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import java.util.Random;


public class Home extends AppCompatActivity {

    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAcceleremoter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        quote = "\"I skate to where the puck is going to be, not where it has been\" - Wayne Gretzky";
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAcceleremoter = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
            @Override
            public void onShake() {
                generateQuoteShake();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAcceleremoter, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }


    String[] quotes = {"“There is only one thing that makes a dream impossible to achieve: the fear of failure.” – Paulo Coelho",
            "“If you really want to do something you’ll find a way, if you don’t you’ll find an excuse.” – Jim Rohn",
            "“Great people, no matter their field, have similar habits. Learn them and use them in your own quest for greatness.” – Paula Andress",
            "“Obstacles are those things you see when you take your eyes off the goal.” – Henry Ford",
            "“Choose a job you love, & you will never have to work a day in your life. – Confucius",
            "“Great things never came from comfort zones.” – Unknown",
            "“If we don’t change, we don’t grow. If we don’t grow, we aren’t really living.” – Gail Sheehy",
            "“Two things define you. Your patience when you have nothing, your attitude when you have everything.” – Unknown",
            "“STOP looking back!!!! When your past calls do NOT pick up!! it has nothing new to say.” – Rev Run",
            "“You choose the #life you live. If you don’t like it, it’s on you to change it because no one else is going to do it for you.” – Kim Kiyosaki",
            "“The biggest mistake you’ll ever make is letting people stay in your life for longer than they deserve.” – Unknown",
            "“Greatness is not found in possessions, power, position, or prestige. It is discovered in goodness, humility, service, & character.” – WA Ward",
            "“Critical feedback is the breakfast of champions. Defensiveness is the dinner of losers.” – Dharmesh Shah",
            "“Before you dismiss a beginner’s work, remember how much you sucked when you started. You probably sucked worse, actually.” – Jason Fried",
            "“You cannot control everything that happens to you; you can only control the way you respond to what happens. In your response is your power.” – Unknown",
            "“Don’t be pushed by your problems. Be led by your dreams.” – Ralph Waldo Emerson",
            "“You don’t have to be great to start, but you have to start to be great.” – Zig Ziglar",
            "“You have not lived today until you have done something for someone who can never repay you.” – John Bunyan",
            "“Success consists of going from failure to failure without loss of enthusiasm.” – Winston Churchill",
            "“If you want it, become it.” – Mastin Kipp",
            "“In the middle of every difficulty lies opportunity.” – Albert Einstein",
            "“If it isn’t a little scary it probably isn’t worth your time.” – Ted Murphy",
            "“You were born with the ability to change someone’s life – don’t ever waste it.” – Dale Partridge",
            "“If you worry about what might be, and wonder what might have been, you will ignore what is.” – Unknown",
            "“There are no secrets to success. It is the result of preparation, hard work and learning from failure.” – Colin Powell",
            "“Life is accumulative – Either our errors accumulate to what we don’t get, or our wise decisions accumulate into what we do get.” – Jim Rohn",
            "“Don’t allow your past or present condition to control you. It’s just a process that you’re going through to get you to the next level.” – T.D Jakes",
            "“Often what may appear as a detour in life is actually the most direct and empowering path to your destination.” – James Arthur Ray",
            "“You will meet two kinds of people in life: ones who build you up and ones who tear you down. But in the end, you’ll thank them both.” – Unknown",
            "“The biggest risk is not taking any risk… In a world that changing really quickly, the only strategy that is guaranteed to fail is not taking risks.” – Mark Zuckerberg",
            "“Don’t be afraid of failure, be afraid of not trying in the first place.” – Marie Forleo",
            "“Never ignore someone who cares for you because someday you’ll realize you’ve lost a diamond while you were busy collecting stones.” – Unknown",
            "“If you are honest, truthful, and transparent, people trust you. If people trust you, you have no grounds for fear, suspicion or jealousy.” – Dalai Lama",
            "“Don’t let your happiness depend on something you may lose.” – C. S. Lewis",
            "“Don’t worry what others are doing. Do you!” – Russell Simmons",
            "“Stay strong. Even when it feels like everything’s falling apart.” – Unknown",
            "“Happiness is not achieved by the conscious pursuit of happiness; it is generally the by-product of other activities.” – Aldous Huxley",
            "“Silence your critics. Ignore your haters. Delete your cynics.” – Robin Sharma",
            "“Your mind is like water, when agitated it becomes difficult to see but if allowed to settle, the answer becomes clear.” – Tsem Tulku Rinpoche",
            "“Failure is a prerequisite for great success. If you want to succeed faster, double your rate of failure.” – Brian Tracy",
            "“I learned that courage was not the absence of fear, but the triumph over it.” – Nelson Mandela",
            "“Be willing to go all out, in pursuit of your dream. Ultimately it will pay off. You are more powerful than you think you are.” – Les Brown",
            "“Nothing is impossible, the word itself says ‘I’m possible'” – Audrey Hepburn",
            "“Don’t speak unless you can improve the silence.” – Spanish Proverb",
            "“Just Remember: The people that say, “your dreams are impossible ” have already quit on theirs.” – Grant Cardone",
            "“Eventually all things fall into place. Until then, laugh at the confusion, live for the moments, and know everything happens for a reason.” – Albert Schweitzer",
            "“Kiss slowly, laugh insanely, live truly and forgive quickly.” – Paulo Coelho",
            "“The only wealth which you will keep forever is the wealth you have given away.” – Marcus Aurelius",
            "“If you’re never scared or embarrassed or hurt, it means you never take any chances.” – Julia Sorel"};


    String quote = new String();


    public void generateQuote(View view) {
        generateQuoteShake();

    }

    public void generateQuoteShake() {
        Random rand = new Random();

        int n = rand.nextInt(48) + 1;
        quote = quotes[n];
        TextView changeText = (TextView) findViewById(R.id.quotebox);
        changeText.setText(quotes[n]);
    }

    public void shareQuote(View view) {

        String message = quote;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Share the quote with others"));

    }


};





