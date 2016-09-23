package furafila.view;

/**
 * Created by Silvio on 09/09/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import furafila.database.CardapioDAO;
import furafila.furafilarestaurante.Cardapio;
import furafila.furafilarestaurante.ListViewAdapterItens;
import furafila.furafilarestaurante.R;

public class FragmentRefeicao extends Fragment {

    private CardapioDAO cd;
    private ListView list;
    private ListViewAdapterItens adapter;
    private List<Cardapio> arraylist = new ArrayList<Cardapio>();
    private List<Cardapio> listaPedidos = null;
    private ImageButton btCarrinho;
    private Context context;


    SQLiteDatabase database;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        context = this.getActivity();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup ct, Bundle par) {
        View v = inf.inflate(R.layout.fragment, ct, false);

        btCarrinho = (ImageButton) v.findViewById(R.id.btCarrinho);
        list = (android.widget.ListView)v.findViewById(R.id.lista);


        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        try {
            // cd = new CardapioDAO(FragmentRefeicao.this.getContext());
            // database = FragmentRefeicao.this.getContext().openOrCreateDatabase("CardapioDB.db", Context.MODE_PRIVATE, null);
        } catch (Exception e) {
            Log.e("Erro", "Erro ao abrir banco de dados" + e.getMessage());
        }

        cd = new CardapioDAO(getActivity());


        arraylist = cd.getAllFoods();
        adapter = new ListViewAdapterItens(getActivity(), arraylist,1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ViewCardapioRefeicao.class);
                i.putExtra("idF", "" + (int) id);
                startActivity(i);
            }
        });

        btCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarrinhoCompraActivity.class);
                context.startActivity(intent);
            }
        });



        super.onActivityCreated(savedInstanceState);
    }
}
