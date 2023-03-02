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
import com.example.ejercicioapirest.modelos.Drinks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoctailMain extends AppCompatActivity
{
    Spinner sp_coctail;
    ImageButton btn_random, btn_filtrar;
    ArrayAdapter<Drinks> adaptador;     //Adaptador para mostrar la información en el spinner

    //Estas variables servirán para mostrar el cóctel devuelto en la vista detalle
    public String nombreCoctel, descCoctel, urlImagenCoctel;
    public String ingre1, ingre2, ingre3, ingre4, ingre5, ingre6, ingre7, ingre8, ingre9, ingre10, ingre11, ingre12, ingre13, ingre14, ingre15;
    public String medi1, medi2, medi3, medi4, medi5, medi6, medi7, medi8, medi9, medi10, medi11, medi12, medi13, medi14, medi15;

    public int codigoCoctel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coctail_main);

        //Asiganmos los botones a la vista
        btn_random = (ImageButton) findViewById(R.id.btn_randomCoctail);
        btn_filtrar = (ImageButton) findViewById(R.id.btn_lista);
        sp_coctail = (Spinner) findViewById(R.id.sp_letra);

        //Rellenamos el Spìnner con todas las iniciales (excepto la X y la U que no devuelven resultados) con un adaptador
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.letras_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_coctail.setAdapter(adapter);

        /////////////////////////BOTONES/////////////////////////////
        //Generamos los listener de los dos botones
        btn_random.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Invocamos al método que nos generará el cóctel aleatorio
                mostrarCoctelRandom();

                //colocamos en un Bundle toda la info del cóctel a cargar para pasarlo a la activity detalle
                Intent intent = new Intent(getApplicationContext(), DetalleCoctail.class);
                intent.putExtra("coctail", nombreCoctel);
                intent.putExtra("id", codigoCoctel);
                intent.putExtra("descripcion", descCoctel);
                intent.putExtra("imagen", urlImagenCoctel);
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

                Intent intent = new Intent(getApplicationContext(), ListaCoctel.class);
                intent.putExtra("letra", sp_coctail.getSelectedItem().toString());

                startActivity(intent);
            }
        });
    }



    //Generamos un cóctel aleatorio proporcionado por la API
    private void mostrarCoctelRandom ()
    {
        //Creamos una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(CoctailAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //Creamos una instancia de CoctailAPI (la API que hemos creado para comunicarnos)
        CoctailAPI coctailapi = retrofit.create(CoctailAPI.class);

        //Creamos una llamada a la API
        Call<Drinks> call = coctailapi.getRandomDrink();

        //Ejecutamos la llamada
        call.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                try
                {
                    //Obtenemos la respuesta y como solamente nos va a devolver un solo Cóctel, almacenamos
                    //lo que necesitamos del mismo para mostrarlo en una activity detalle
                    if (response.isSuccessful() && response.body() != null)
                    {
                        Drinks drinks = response.body();

                        nombreCoctel = drinks.getDrinks().get(0).getstrDrink();
                        codigoCoctel = drinks.getDrinks().get(0).getIdDrink();
                        descCoctel = drinks.getDrinks().get(0).getstrInstructions();
                        urlImagenCoctel = drinks.getDrinks().get(0).getstrDrinkThumb();

                        //Ingredientes
                        ingre1 = drinks.getDrinks().get(0).getStrIngredient1();
                        ingre2 = drinks.getDrinks().get(0).getStrIngredient2();
                        ingre3 = drinks.getDrinks().get(0).getStrIngredient3();
                        ingre4 = drinks.getDrinks().get(0).getStrIngredient4();
                        ingre5 = drinks.getDrinks().get(0).getStrIngredient5();
                        ingre6 = drinks.getDrinks().get(0).getStrIngredient6();
                        ingre7 = drinks.getDrinks().get(0).getStrIngredient7();
                        ingre8 = drinks.getDrinks().get(0).getStrIngredient8();
                        ingre9 = drinks.getDrinks().get(0).getStrIngredient9();
                        ingre10 = drinks.getDrinks().get(0).getStrIngredient10();
                        ingre11 = drinks.getDrinks().get(0).getStrIngredient11();
                        ingre12 = drinks.getDrinks().get(0).getStrIngredient12();
                        ingre13 = drinks.getDrinks().get(0).getStrIngredient13();
                        ingre14 = drinks.getDrinks().get(0).getStrIngredient14();
                        ingre15 = drinks.getDrinks().get(0).getStrIngredient15();

                        //Medidas
                        medi1 = drinks.getDrinks().get(0).getStrMeasure1();
                        medi2 = drinks.getDrinks().get(0).getStrMeasure2();
                        medi3 = drinks.getDrinks().get(0).getStrMeasure3();
                        medi4 = drinks.getDrinks().get(0).getStrMeasure4();
                        medi5 = drinks.getDrinks().get(0).getStrMeasure5();
                        medi6 = drinks.getDrinks().get(0).getStrMeasure6();
                        medi7 = drinks.getDrinks().get(0).getStrMeasure7();
                        medi8 = drinks.getDrinks().get(0).getStrMeasure8();
                        medi9 = drinks.getDrinks().get(0).getStrMeasure9();
                        medi10 = drinks.getDrinks().get(0).getStrMeasure10();
                        medi11 = drinks.getDrinks().get(0).getStrMeasure11();
                        medi12 = drinks.getDrinks().get(0).getStrMeasure12();
                        medi13 = drinks.getDrinks().get(0).getStrMeasure13();
                        medi14 = drinks.getDrinks().get(0).getStrMeasure14();
                        medi15 = drinks.getDrinks().get(0).getStrMeasure15();

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