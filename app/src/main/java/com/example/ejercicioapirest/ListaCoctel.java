package com.example.ejercicioapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioapirest.adaptadores.adaptadorCoctel;
import com.example.ejercicioapirest.interfaces.CoctailAPI;
import com.example.ejercicioapirest.modelos.Coctail;
import com.example.ejercicioapirest.modelos.Drinks;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaCoctel extends AppCompatActivity
{
    TextView tvTitulo;
    ListView listaCocteles;

    //Este es el ArrayList que recogerá los cócteles devueltos por la API y los meterá en la listview
    ArrayList<Coctail> lista_coctails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coctel);

        //Asiganmos las variables a la vista
        tvTitulo = (TextView) findViewById(R.id.tv_titulo);
        listaCocteles = (ListView) findViewById(R.id.lv_listaCocteles);

        tvTitulo.setText("Lista de cócteles disponibles con la letra "+getIntent().getStringExtra("letra"));

        //Llamamos al método que nos llenará la ListView con los resultados de la API pasando
        //como parámetro la letra seleccionada en el spinner de la activity anterior
        encontrarCoctailPorLetra(getIntent().getStringExtra("letra"));

        //Creamos un método para que muestre el detalle del cóctel al presionar sobre el mismo
        //para mostrarlo en detalle
        listaCocteles.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(getApplicationContext(), "Seleccionaste el cóctel " + lista_coctails.get(i).getstrDrink(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetalleCoctail.class);
                intent.putExtra("coctail", lista_coctails.get(i).getstrDrink());
                intent.putExtra("id", lista_coctails.get(i).getIdDrink());
                intent.putExtra("descripcion", lista_coctails.get(i).getstrInstructions());
                intent.putExtra("imagen", lista_coctails.get(i).getstrDrinkThumb());
                intent.putExtra("ingre1", lista_coctails.get(i).getStrIngredient1());
                intent.putExtra("ingre2", lista_coctails.get(i).getStrIngredient2());
                intent.putExtra("ingre3", lista_coctails.get(i).getStrIngredient3());
                intent.putExtra("ingre4", lista_coctails.get(i).getStrIngredient4());
                intent.putExtra("ingre5", lista_coctails.get(i).getStrIngredient5());
                intent.putExtra("ingre6", lista_coctails.get(i).getStrIngredient6());
                intent.putExtra("ingre7", lista_coctails.get(i).getStrIngredient7());
                intent.putExtra("ingre8", lista_coctails.get(i).getStrIngredient8());
                intent.putExtra("ingre9", lista_coctails.get(i).getStrIngredient9());
                intent.putExtra("ingre10", lista_coctails.get(i).getStrIngredient10());
                intent.putExtra("ingre11", lista_coctails.get(i).getStrIngredient11());
                intent.putExtra("ingre12", lista_coctails.get(i).getStrIngredient12());
                intent.putExtra("ingre13", lista_coctails.get(i).getStrIngredient13());
                intent.putExtra("ingre14", lista_coctails.get(i).getStrIngredient14());
                intent.putExtra("ingre15", lista_coctails.get(i).getStrIngredient15());
                intent.putExtra("medida1", lista_coctails.get(i).getStrMeasure1());
                intent.putExtra("medida2", lista_coctails.get(i).getStrMeasure2());
                intent.putExtra("medida3", lista_coctails.get(i).getStrMeasure3());
                intent.putExtra("medida4", lista_coctails.get(i).getStrMeasure4());
                intent.putExtra("medida5", lista_coctails.get(i).getStrMeasure5());
                intent.putExtra("medida6", lista_coctails.get(i).getStrMeasure6());
                intent.putExtra("medida7", lista_coctails.get(i).getStrMeasure7());
                intent.putExtra("medida8", lista_coctails.get(i).getStrMeasure8());
                intent.putExtra("medida9", lista_coctails.get(i).getStrMeasure9());
                intent.putExtra("medida10", lista_coctails.get(i).getStrMeasure10());
                intent.putExtra("medida11", lista_coctails.get(i).getStrMeasure11());
                intent.putExtra("medida12", lista_coctails.get(i).getStrMeasure12());
                intent.putExtra("medida13", lista_coctails.get(i).getStrMeasure13());
                intent.putExtra("medida14", lista_coctails.get(i).getStrMeasure14());
                intent.putExtra("medida15", lista_coctails.get(i).getStrMeasure15());

                //Inicalizamos la nueva actividad con todos los parámetros
                startActivity(intent);
            }
        });
    }

    //////////Método que recupera el listado de cócteles de la API según la letra especificada
    // y rellena el arraylist y la lista con el adaptador.
    private void encontrarCoctailPorLetra (String letra)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(CoctailAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        CoctailAPI coctailapi = retrofit.create(CoctailAPI.class);

        Call<Drinks> call = coctailapi.getDrinkByName(letra);

        call.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                try
                {
                    if (response.isSuccessful() && response.body() != null)
                    {
                        Drinks drinks = response.body();

                        for (Coctail cocteles : drinks.getDrinks())
                        {
                            lista_coctails.add(cocteles);
                        }

                        //Pequeño método para mostrar la lista de cócteles en la listview
                        //SOLO PARA TESTING
                        /*
                        for (Coctail coctail : lista_coctails) {
                            Log.i(TAG, "Cócteles en el ArrayList: " + coctail.getstrDrink());
                        }
                        */

                        //Creamos instancia del adaptador y lo aplicamos
                        adaptadorCoctel adaptador = new adaptadorCoctel (getApplicationContext(), R.layout.list_item, lista_coctails);

                        listaCocteles.setAdapter(adaptador);
                    }

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Lo siento, no puedo acceder a la URL", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error, no se pueden obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}