package furafila.view;

/**
 * Created by Silvio on 09/09/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import furafila.database.CardapioDAO;
import furafila.furafilarestaurante.Cardapio;
import furafila.furafilarestaurante.R;


public class ViewCardapioBebidas extends Activity {

    private String id;
    TextView name, price, description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlelistview);



       // getActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        image = (ImageView) findViewById(R.id.image);
        description = (TextView) findViewById(R.id.description);

        try {
            Intent i = getIntent();
            Bundle b = i.getExtras();

            if (b != null) {
                id = String.valueOf(b.get("idD"));
                CardapioDAO cm = new CardapioDAO(this);
                Cardapio cardapio = cm.getDrinkItem(Integer.parseInt(id));

                String mName = cardapio.getName();
                String mPrice = cardapio.getPrice();
                String mDescription = cardapio.getDescription();

                name.setText(mName);
                price.setText(mPrice);
                description.setText(mDescription);

                int imageResource = getResources().getIdentifier(cardapio.getImage(), "drawable", getPackageName());
                image.setImageResource(imageResource);

            }
        } catch (Exception ex) {
            Log.v("Task", String.valueOf(ex));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
