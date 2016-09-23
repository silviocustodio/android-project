package furafila.view;

/**
 * Created by Silvio on 09/09/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import furafila.database.CardapioDAO;
import furafila.furafilarestaurante.Cardapio;
import furafila.furafilarestaurante.ListViewAdapterItens;
import furafila.furafilarestaurante.R;

public class FragmentSobremesa extends Fragment {

    private CardapioDAO cd;
    private ListView list;
    private ListViewAdapterItens adapter;
    private List<Cardapio> arraylist = new ArrayList<Cardapio>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup ct, Bundle par) {
        View v = inf.inflate(R.layout.fragment, ct, false);

        cd = new CardapioDAO(getActivity());
        list = (ListView)v.findViewById(R.id.lista);

        arraylist = cd.getAllDessert();
        adapter = new ListViewAdapterItens(getActivity(), arraylist,3);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ViewCardapioSobremesa.class);
                i.putExtra("idE", "" + (int) id);
                startActivity(i);
            }
        });

        return v;
    }
}
