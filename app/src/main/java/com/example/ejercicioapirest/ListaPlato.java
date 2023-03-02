package com.example.ejercicioapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioapirest.adaptadores.adaptadorCoctel;
import com.example.ejercicioapirest.adaptadores.adaptadorPlato;
import com.example.ejercicioapirest.interfaces.CoctailAPI;
import com.example.ejercicioapirest.interfaces.FoodAPI;
import com.example.ejercicioapirest.modelos.Coctail;
import com.example.ejercicioapirest.modelos.Drinks;
import com.example.ejercicioapirest.modelos.Food;
import com.example.ejercicioapirest.modelos.Meals;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaPlato extends AppCompatActivity
{
    TextView tvTitulo;
    ListView listaPlatos;

    //Este es el ArrayList que recogerá los platos devueltos por la API y los meterá en la listview
    ArrayList<Food> lista_platos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_plato);

        //Asiganmos las variables a la vista
        tvTitulo = (TextView) findViewById(R.id.tv_titulo);
        listaPlatos = (ListView) findViewById(R.id.lv_listaPlatos);

        tvTitulo.setText("Lista de platos disponibles con la letra "+getIntent().getStringExtra("letra"));

        //Llamamos al método que nos llenará la ListView con los resultados de la API pasando
        //como parámetro la letra seleccionada en el spinner de la activity anterior
        encontrarPlatoPorLetra(getIntent().getStringExtra("letra"));

        //Igual que con los cócteles, creamos un método para que muestre el detalle del plato para que al presionar sobre el mismo
        //muestre en detalle el plato
        listaPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(getApplicationContext(), "Seleccionaste el plato " + lista_platos.get(i).getStrMeal(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetallePlato.class);
                intent.putExtra("plato", lista_platos.get(i).getStrMeal());
                intent.putExtra("id", lista_platos.get(i).getIdMeal());
                intent.putExtra("descripcion", lista_platos.get(i).getStrInstructions());
                intent.putExtra("imagen", lista_platos.get(i).getStrMealThumb());
                intent.putExtra("ingre1", lista_platos.get(i).getStrIngredient1());
                intent.putExtra("ingre2", lista_platos.get(i).getStrIngredient2());
                intent.putExtra("ingre3", lista_platos.get(i).getStrIngredient3());
                intent.putExtra("ingre4", lista_platos.get(i).getStrIngredient4());
                intent.putExtra("ingre5", lista_platos.get(i).getStrIngredient5());
                intent.putExtra("ingre6", lista_platos.get(i).getStrIngredient6());
                intent.putExtra("ingre7", lista_platos.get(i).getStrIngredient7());
                intent.putExtra("ingre8", lista_platos.get(i).getStrIngredient8());
                intent.putExtra("ingre9", lista_platos.get(i).getStrIngredient9());
                intent.putExtra("ingre10", lista_platos.get(i).getStrIngredient10());
                intent.putExtra("ingre11", lista_platos.get(i).getStrIngredient11());
                intent.putExtra("ingre12", lista_platos.get(i).getStrIngredient12());
                intent.putExtra("ingre13", lista_platos.get(i).getStrIngredient13());
                intent.putExtra("ingre14", lista_platos.get(i).getStrIngredient14());
                intent.putExtra("ingre15", lista_platos.get(i).getStrIngredient15());
                intent.putExtra("ingre16", lista_platos.get(i).getStrIngredient16());
                intent.putExtra("ingre17", lista_platos.get(i).getStrIngredient17());
                intent.putExtra("ingre18", lista_platos.get(i).getStrIngredient18());
                intent.putExtra("ingre19", lista_platos.get(i).getStrIngredient19());
                intent.putExtra("ingre20", lista_platos.get(i).getStrIngredient20());
                intent.putExtra("medida1", lista_platos.get(i).getStrMeasure1());
                intent.putExtra("medida2", lista_platos.get(i).getStrMeasure2());
                intent.putExtra("medida3", lista_platos.get(i).getStrMeasure3());
                intent.putExtra("medida4", lista_platos.get(i).getStrMeasure4());
                intent.putExtra("medida5", lista_platos.get(i).getStrMeasure5());
                intent.putExtra("medida6", lista_platos.get(i).getStrMeasure6());
                intent.putExtra("medida7", lista_platos.get(i).getStrMeasure7());
                intent.putExtra("medida8", lista_platos.get(i).getStrMeasure8());
                intent.putExtra("medida9", lista_platos.get(i).getStrMeasure9());
                intent.putExtra("medida10", lista_platos.get(i).getStrMeasure10());
                intent.putExtra("medida11", lista_platos.get(i).getStrMeasure11());
                intent.putExtra("medida12", lista_platos.get(i).getStrMeasure12());
                intent.putExtra("medida13", lista_platos.get(i).getStrMeasure13());
                intent.putExtra("medida14", lista_platos.get(i).getStrMeasure14());
                intent.putExtra("medida15", lista_platos.get(i).getStrMeasure15());
                intent.putExtra("medida16", lista_platos.get(i).getStrMeasure16());
                intent.putExtra("medida17", lista_platos.get(i).getStrMeasure17());
                intent.putExtra("medida18", lista_platos.get(i).getStrMeasure18());
                intent.putExtra("medida19", lista_platos.get(i).getStrMeasure19());
                intent.putExtra("medida20", lista_platos.get(i).getStrMeasure20());

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }

    //////////Método que recupera el listado de platos de la API según la letra especificada
    // y rellena el arraylist y la lista con el adaptador.
    private void encontrarPlatoPorLetra (String letra)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FoodAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        FoodAPI foodapi = retrofit.create(FoodAPI.class);

        Call<Meals> call = foodapi.getFoodByName(letra);

        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                try
                {
                    if (response.isSuccessful() && response.body() != null)
                    {
                        Meals meals = response.body();

                        for (Food platillo : meals.getMeals())
                        {
                            lista_platos.add(platillo);
                        }

                        //Pequeño método para mostrar la lista de platos en la listview
                        //SOLO PARA TESTING
                        /*
                        for (Food food : lista_platos) {
                            Log.i(TAG, "Platos en el ArrayList: " + food.getstrDrink());
                        }
                        */

                        //Creamos instancia del adaptador y lo aplicamos
                        adaptadorPlato adaptador = new adaptadorPlato (getApplicationContext(), R.layout.list_item, lista_platos);

                        listaPlatos.setAdapter(adaptador);
                    }

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Lo siento, no puedo acceder a la URL", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error, no se pueden obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}