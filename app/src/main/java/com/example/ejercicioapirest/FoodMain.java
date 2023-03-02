package com.example.ejercicioapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejercicioapirest.interfaces.CoctailAPI;
import com.example.ejercicioapirest.interfaces.FoodAPI;
import com.example.ejercicioapirest.modelos.Drinks;
import com.example.ejercicioapirest.modelos.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodMain extends AppCompatActivity
{

    Spinner sp_meal;
    ImageButton btn_random, btn_filtrar;

    //Estas variables servirán para mostrar el plato devuelto en la vista detalle
    public String nombrePlato, descPlato, urlImagenPlato;

    public String ingre1, ingre2, ingre3, ingre4, ingre5, ingre6, ingre7, ingre8, ingre9, ingre10, ingre11, ingre12, ingre13, ingre14, ingre15, ingre16, ingre17, ingre18, ingre19, ingre20;
    public String medi1, medi2, medi3, medi4, medi5, medi6, medi7, medi8, medi9, medi10, medi11, medi12, medi13, medi14, medi15, medi16, medi17, medi18, medi19, medi20;
    public int codigoPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);

        //Asiganmos los botones a la vista
        btn_random = (ImageButton) findViewById(R.id.btn_randomFood);
        btn_filtrar = (ImageButton) findViewById(R.id.btn_lista);
        sp_meal = (Spinner) findViewById(R.id.sp_letra);

        //Rellenamos el Spìnner con todas las iniciales con un adaptador
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comida, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_meal.setAdapter(adapter);

        /////////////////////////BOTONES/////////////////////////////
        //Generamos los listener de los dos botones
        btn_random.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Invocamos al método que nos generará el cóctel aleatorio
                mostrarComidaRandom();

                //colocamos en un Bundle toda la info del cóctel a cargar para pasarlo a la activity detalle
                Intent intent = new Intent(getApplicationContext(), DetallePlato.class);
                intent.putExtra("plato", nombrePlato);
                intent.putExtra("id", codigoPlato);
                intent.putExtra("descripcion", descPlato);
                intent.putExtra("imagen", urlImagenPlato);
                intent.putExtra("ingre1", ingre1);
                intent.putExtra("ingre2", ingre2);
                intent.putExtra("ingre3", ingre3);
                intent.putExtra("ingre4", ingre4);
                intent.putExtra("ingre5", ingre5);
                intent.putExtra("ingre6", ingre6);
                intent.putExtra("ingre7", ingre7);
                intent.putExtra("ingre8", ingre8);
                intent.putExtra("ingre9", ingre9);
                intent.putExtra("ingre10", ingre10);
                intent.putExtra("ingre11", ingre11);
                intent.putExtra("ingre12", ingre12);
                intent.putExtra("ingre13", ingre13);
                intent.putExtra("ingre14", ingre14);
                intent.putExtra("ingre15", ingre15);
                intent.putExtra("ingre16", ingre16);
                intent.putExtra("ingre17", ingre17);
                intent.putExtra("ingre18", ingre18);
                intent.putExtra("ingre19", ingre19);
                intent.putExtra("ingre20", ingre20);
                intent.putExtra("medida1", medi1);
                intent.putExtra("medida2", medi2);
                intent.putExtra("medida3", medi3);
                intent.putExtra("medida4", medi4);
                intent.putExtra("medida5", medi5);
                intent.putExtra("medida6", medi6);
                intent.putExtra("medida7", medi7);
                intent.putExtra("medida8", medi8);
                intent.putExtra("medida9", medi9);
                intent.putExtra("medida10", medi10);
                intent.putExtra("medida11", medi11);
                intent.putExtra("medida12", medi12);
                intent.putExtra("medida13", medi13);
                intent.putExtra("medida14", medi14);
                intent.putExtra("medida15", medi15);
                intent.putExtra("medida16", medi16);
                intent.putExtra("medida17", medi17);
                intent.putExtra("medida18", medi18);
                intent.putExtra("medida19", medi19);
                intent.putExtra("medida20", medi20);

                //Por último lanzamos la actividad
                startActivity(intent);
            }
        });

        //Botón para filtrar por letra
        btn_filtrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Lo único que pasamos esta vez, es la letra seleccionada en el Spinner para que la
                //vista de lista nos devuelva toda la lista de cócteles que comiencen por la letra
                //seleccionada en el spinner

                Intent intent = new Intent(getApplicationContext(), ListaPlato.class);
                intent.putExtra("letra", sp_meal.getSelectedItem().toString());

                startActivity(intent);
            }
        });
    }



    //Generamos un plato aleatorio proporcionado por la API
    private void mostrarComidaRandom ()
    {
        //Creamos una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FoodAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //Creamos una instancia de FoodAPI (la API que hemos creado para comunicarnos)
        FoodAPI foodapi = retrofit.create(FoodAPI.class);

        //Creamos una llamada a la API
        Call<Meals> call = foodapi.getRandomFood();

        //Ejecutamos la llamada
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                try
                {
                    //Obtenemos la respuesta y como solamente nos va a devolver un solo plato, almacenamos
                    //lo que necesitamos del mismo para mostrarlo en una activity detalle
                    if (response.isSuccessful() && response.body() != null)
                    {
                        Meals meals = response.body();

                        nombrePlato = meals.getMeals().get(0).getStrMeal();
                        codigoPlato = meals.getMeals().get(0).getIdMeal();
                        descPlato = meals.getMeals().get(0).getStrInstructions();
                        urlImagenPlato = meals.getMeals().get(0).getStrMealThumb();

                        //Este tocino obtiene los ingredientes de cada plato
                        ingre1 = meals.getMeals().get(0).getStrIngredient1();
                        ingre2 = meals.getMeals().get(0).getStrIngredient2();
                        ingre3 = meals.getMeals().get(0).getStrIngredient3();
                        ingre4 = meals.getMeals().get(0).getStrIngredient4();
                        ingre5 = meals.getMeals().get(0).getStrIngredient5();
                        ingre6 = meals.getMeals().get(0).getStrIngredient6();
                        ingre7 = meals.getMeals().get(0).getStrIngredient7();
                        ingre8 = meals.getMeals().get(0).getStrIngredient8();
                        ingre9 = meals.getMeals().get(0).getStrIngredient9();
                        ingre10 = meals.getMeals().get(0).getStrIngredient10();
                        ingre11 = meals.getMeals().get(0).getStrIngredient11();
                        ingre12 = meals.getMeals().get(0).getStrIngredient12();
                        ingre13 = meals.getMeals().get(0).getStrIngredient13();
                        ingre14 = meals.getMeals().get(0).getStrIngredient14();
                        ingre15 = meals.getMeals().get(0).getStrIngredient15();
                        ingre16 = meals.getMeals().get(0).getStrIngredient16();
                        ingre17 = meals.getMeals().get(0).getStrIngredient17();
                        ingre18 = meals.getMeals().get(0).getStrIngredient18();
                        ingre19 = meals.getMeals().get(0).getStrIngredient19();
                        ingre20 = meals.getMeals().get(0).getStrIngredient20();

                        //Y esta mortadela la medida de cada ingrediente
                        medi1 = meals.getMeals().get(0).getStrMeasure1();
                        medi2 = meals.getMeals().get(0).getStrMeasure2();
                        medi3 = meals.getMeals().get(0).getStrMeasure3();
                        medi4 = meals.getMeals().get(0).getStrMeasure4();
                        medi5 = meals.getMeals().get(0).getStrMeasure5();
                        medi6 = meals.getMeals().get(0).getStrMeasure6();
                        medi7 = meals.getMeals().get(0).getStrMeasure7();
                        medi8 = meals.getMeals().get(0).getStrMeasure8();
                        medi9 = meals.getMeals().get(0).getStrMeasure9();
                        medi10 = meals.getMeals().get(0).getStrMeasure10();
                        medi11 = meals.getMeals().get(0).getStrMeasure11();
                        medi12 = meals.getMeals().get(0).getStrMeasure12();
                        medi13 = meals.getMeals().get(0).getStrMeasure13();
                        medi14 = meals.getMeals().get(0).getStrMeasure14();
                        medi15 = meals.getMeals().get(0).getStrMeasure15();
                        medi16 = meals.getMeals().get(0).getStrMeasure16();
                        medi17 = meals.getMeals().get(0).getStrMeasure17();
                        medi18 = meals.getMeals().get(0).getStrMeasure18();
                        medi19 = meals.getMeals().get(0).getStrMeasure19();
                        medi20 = meals.getMeals().get(0).getStrMeasure20();

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