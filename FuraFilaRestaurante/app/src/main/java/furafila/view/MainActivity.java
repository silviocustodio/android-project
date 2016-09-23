package furafila.view;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.TabHost;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import furafila.database.ConexaoBanco;
import furafila.furafilarestaurante.R;

public class MainActivity extends FragmentActivity {

    private static final String NAME_DATA = "CardapioDB";
    private FragmentTabHost mTab;


    String PATH = "/data/data/furafila.furafilarestaurante/databases/";
    private ConexaoBanco conexaoBanco;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        PATH = "/data/data/" + this.getPackageName() + "/databases";

        try {
            criaPastData();
            openBanco();
            // seletFraseRandon();
        } catch (Exception e) {
            Log.e("MAIN", "ERROR " + e.getMessage());
        }
        db = openOrCreateDatabase(NAME_DATA, Context.MODE_PRIVATE, null);



        mTab = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTab.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        TabHost.TabSpec spec1 = mTab.newTabSpec("refeicao");
        spec1.setIndicator("Refeição", null);
        mTab.addTab(spec1, FragmentRefeicao.class, null);

        TabHost.TabSpec spec2 = mTab.newTabSpec("bebidas");
        spec2.setIndicator("Bebidas", null);
        mTab.addTab(spec2, FragmentBebidas.class, null);

        TabHost.TabSpec spec3 = mTab.newTabSpec("sobremesa");
        spec3.setIndicator("Sobremesa", null);
        mTab.addTab(spec3, FragmentSobremesa.class, null);

        mTab.setCurrentTab(0);
    }

    public void openBanco() {
        PATH = PATH + "/" + NAME_DATA;
        conexaoBanco = new ConexaoBanco(this);
    }

    /**
     * Cria a pasta DataBases "/data/data/furafila.furafilarestaurante/databases/";
     * @throws IOException
     */






    public void criaPastData() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            Log.e("MAIN", "arquivo nao existe" + file.getAbsolutePath());
            file.mkdirs();
            InputStream is = getAssets().open(NAME_DATA);//abre o arquivo database que esta na pasta assets
            copiaBanco(is); //e faz copia
        } else {
            InputStream is = getAssets().open(NAME_DATA);//abre o arquivo database que esta na pasta assets
            copiaBanco(is);
        }
    }

    public void copiaBanco(InputStream inputStream) {
        try {
            PATH = PATH + "/" + NAME_DATA;
            OutputStream outputStream = new FileOutputStream(new File(PATH));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.i("MAIN", "Erro copiaBanco() " + e.getMessage());
        }

    }

}
