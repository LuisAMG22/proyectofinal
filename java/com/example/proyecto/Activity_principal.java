 package com.example.proyecto;

 import android.content.DialogInterface;
 import android.os.Bundle;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AlertDialog;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.fragment.app.Fragment;
 import androidx.fragment.app.FragmentTransaction;

 import com.google.android.material.bottomnavigation.BottomNavigationView;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;


 public class Activity_principal extends AppCompatActivity {

     FirstFragment firstFragment = new FirstFragment();
     SecondFragment secondFragment = new SecondFragment();
     ThirdFragment thirdFragment = new ThirdFragment();
     FourthFragment fourthFragment = new FourthFragment();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_principal);

         BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
         navigation.setBackground(null);

         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectListener);
         loadFragment(firstFragment);
         FloatingActionButton fab = findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //AQUI METE MIKEE TOWERS SU VAINA DE AÑADIR EJERCICIO NUEVO
                 // Acción a realizar cuando se pulsa el botón fab
                 // Por ejemplo, mostrar un mensaje o iniciar una nueva actividad
                 Toast.makeText(getApplicationContext(), "Botón FAB pulsado", Toast.LENGTH_SHORT).show();
                 // Aquí puedes agregar tu lógica personalizada
             }
         });
     }

     private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             Fragment selectedFragment = null;

             switch (item.getItemId()) {
                 case R.id.firstFragment:
                     selectedFragment = firstFragment;
                     break;
                 case R.id.secondFragment:
                     selectedFragment = secondFragment;
                     break;
                 case R.id.thirdFragment:
                     selectedFragment = thirdFragment;
                     break;
                 case R.id.fourthFragment:
                     selectedFragment = fourthFragment;
                     break;
             }

             if (getCurrentFragment() instanceof FirstFragment && selectedFragment != getCurrentFragment()) {
                 showConfirmationDialog(selectedFragment);
                 return false; // Evitar la selección del elemento de menú hasta que se confirme el cambio
             } else {
                 loadFragment(selectedFragment);
                 return true;
             }
         }
     };

     public void loadFragment(Fragment fragment){
         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
         transaction.replace(R.id.frame_container,fragment);
         transaction.commit();
     }

     private Fragment getCurrentFragment() {
         return getSupportFragmentManager().findFragmentById(R.id.frame_container);
     }

     private void showConfirmationDialog(final Fragment fragment) {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Salir del ejercicio");
         builder.setMessage("Si sales sin guardar, perderás tu datos. ¿Estás seguro?");

         builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 loadFragment(fragment);
             }
         });

         builder.setNegativeButton("Cancelar", null);

         AlertDialog dialog = builder.create();
         dialog.setOnShowListener(new DialogInterface.OnShowListener() {
             @Override
             public void onShow(DialogInterface dialogInterface) {
                 dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                 dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
             }
         });
         dialog.show();
     }

     @Override
     public void onBackPressed() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Cerrar aplicación");
         builder.setMessage("¿Estás seguro de que quieres cerrar la aplicación?");
         builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 // Cierra todas las actividades y sale completamente de la aplicación
                 finishAffinity();
             }
         });

         builder.setNegativeButton("Cancelar", null);

         AlertDialog dialog = builder.create();
         dialog.setOnShowListener(new DialogInterface.OnShowListener() {
             @Override
             public void onShow(DialogInterface dialogInterface) {
                 dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                 dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
             }
         });
         dialog.show();
     }
 }
