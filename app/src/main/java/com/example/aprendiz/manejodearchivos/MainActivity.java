package com.example.aprendiz.manejodearchivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button crear, leer, eliminar, limpia;
    EditText edit;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crear = (Button) findViewById(R.id.crear);
        leer = (Button) findViewById(R.id.leer);
        eliminar = (Button) findViewById(R.id.eliminar);
        texto = (TextView) findViewById(R.id.texto);
        edit = (EditText) findViewById(R.id.edit);
        limpia = (Button) findViewById(R.id.limpia);

        File f = getFilesDir();
        final String path = f.getAbsolutePath();

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edit.getText().toString() + "\n";
                edit.setText("");

                try {
                    FileOutputStream fo = openFileOutput("Archivo2.txt", MODE_APPEND);
                    try {
                        fo.write(text.getBytes());
                        fo.close();
                        texto.setText("Se ha creado el archivo txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fi = openFileInput("Archivo2.txt");
                    BufferedInputStream bi = new BufferedInputStream(fi);
                    StringBuffer buffer = new StringBuffer();

                    while (bi.available() != 0) {
                        char c = (char) bi.read();
                        buffer.append(c);
                    }
                    texto.setText(buffer.toString());
                    bi.close();
                    fi.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(path + File.separator + "Archivo2.txt");
                boolean deleted = file.delete();

                if (deleted) {
                    texto.setText("Eliminado");
                } else {
                    texto.setText("Error al eliminar");
                }
            }
        });

        limpia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                edit.setText("");

                try {
                    FileOutputStream fo = openFileOutput("Archivo2.txt", MODE_PRIVATE);
                    try {
                        fo.write(text.getBytes());
                        fo.close();
                        texto.setText("Se a borrado");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        texto.setText(path);
    }
}
