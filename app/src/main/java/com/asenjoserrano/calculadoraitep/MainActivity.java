package com.asenjoserrano.calculadoraitep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String displayNumberString = "";

    TextView displayNumber;
    Button oneBtn;

    // números separados para guardarse entre operaciones
    boolean procesoOperacion = false;
    String tipoOperacion = "";

    double num1, num2, resultadoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oneBtn = (Button)findViewById(R.id.btn1);

        displayNumber = (TextView)findViewById(R.id.displayNumber);
    }

    public void onClick (View view) {
        if (view != null) {
            if (!displayNumber.getText().equals("")) {
                Button clicked = (Button) view;
                displayNumberString += clicked.getText().toString();
            } else {
                resultadoActual = 0;
                Button clicked = (Button) view;
                displayNumberString += clicked.getText().toString();
            }
            displayNumber.setText(displayNumberString);
        }
    }

    public void resetDisplay (View view) {
        // reset
        displayNumberString = "";
        procesoOperacion = false;
        tipoOperacion = "";
        num1 = 0;
        num2 = 0;
    }

    public void resetAll (View view) {
        resetDisplay(view);
        displayNumber.setText("0");
        resultadoActual = 0;
    }

    // métodos operacionales
    public void suma (View view) {
        if (procesoOperacion == false && displayNumberString != ""){
            tipoOperacion = "sumando"; // aplicable al pulsar "="
            displayNumber.setText(displayNumberString + " +");

            num1 = Double.parseDouble(displayNumberString);
            displayNumberString = ""; // reset para segundo número
            procesoOperacion = true;
        } else {
            tipoOperacion = "sumando";
            displayNumber.setText((int) resultadoActual + " +");

            num1 = resultadoActual;
            displayNumberString = ""; // reset para segundo número
            procesoOperacion = true;
        }
    }
    public void resta (View view) {
        if (procesoOperacion == false && displayNumberString != ""){
            tipoOperacion = "restando"; // aplicable al pulsar "="
            displayNumber.setText(displayNumberString + " -");

            num1 = Double.parseDouble(displayNumberString);
            displayNumberString = ""; // reset para segundo número
            procesoOperacion = true;
        } else {
            tipoOperacion = "restando";
            displayNumber.setText((int) resultadoActual + " -");

            num1 = resultadoActual;
            displayNumberString = ""; // reset para segundo número
            procesoOperacion = true;
        }
    }
    public void multi (View view) {
        if (procesoOperacion == false && displayNumberString != ""){
            tipoOperacion = "multiplicando"; // aplicable al pulsar "="
            displayNumber.setText(displayNumberString + " x");

            num1 = Double.parseDouble(displayNumberString);
            displayNumberString = ""; // reset para segundo número
            procesoOperacion = true;
        } else {
            tipoOperacion = "multiplicando";
            displayNumber.setText((int) resultadoActual + " x");

            num1 = resultadoActual;
            displayNumberString = ""; // reset para segundo número
            procesoOperacion = true;
        }
    }
    public void divi (View view) {
        try {
            if (procesoOperacion == false && displayNumberString != ""){
                tipoOperacion = "dividiendo"; // aplicable al pulsar "="
                displayNumber.setText(displayNumberString + " ÷");

                num1 = Double.parseDouble(displayNumberString);
                displayNumberString = ""; // reset para segundo número
                procesoOperacion = true;
            } else {
                tipoOperacion = "dividiendo";
                displayNumber.setText((int) resultadoActual + " ÷");

                num1 = resultadoActual;
                displayNumberString = ""; // reset para segundo número
                procesoOperacion = true;
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
        }
    }

    public void operarResultado (View view) {
        if (displayNumberString != null && !displayNumberString.equals("")) {
            if (tipoOperacion.equals("sumando")) {
                num2 = Double.parseDouble(displayNumberString);
                double resultado = num1+num2;
                resultadoActual = resultado; // guarda para posterior uso si se continúan las operaciones
                displayNumberString = String.valueOf(resultado);
                if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
                displayNumber.setText(displayNumberString);

                resetDisplay(view);
            }
            else if (tipoOperacion.equals("restando")) {
                num2 = Double.parseDouble(displayNumberString);
                double resultado = num1-num2;
                resultadoActual = resultado; // guarda para posterior uso si se continúan las operaciones
                displayNumberString = String.valueOf(resultado);
                if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
                displayNumber.setText(displayNumberString);

                resetDisplay(view);
            }
            else if (tipoOperacion.equals("multiplicando")) {
                num2 = Double.parseDouble(displayNumberString);
                double resultado = num1*num2;
                resultadoActual = resultado; // guarda para posterior uso si se continúan las operaciones
                displayNumberString = String.valueOf(resultado);
                if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
                displayNumber.setText(displayNumberString);

                resetDisplay(view);
            }
            else if (tipoOperacion.equals("dividiendo")) {
                try {
                    num2 = Double.parseDouble(displayNumberString);
                    double resultado = num1/num2;
                    resultadoActual = resultado; // guarda para posterior uso si se continúan las operaciones
                    displayNumberString = String.valueOf(resultado);
                    if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
                    displayNumber.setText(displayNumberString);

                    resetDisplay(view);
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                    displayNumberString = "";
                    displayNumber.setText("0");

                    resetDisplay(view);
                }
            }
        }
    }

    public void addDecimal (View view) {
        displayNumberString += ".";
        displayNumber.setText(displayNumberString);
    }

    public void raizCuadrada (View view) {
        if (procesoOperacion == false && displayNumberString != "") {
            num1 = Double.parseDouble(displayNumberString);
            double resultado = Math.sqrt(num1);
            resultadoActual = (float) resultado;
            displayNumberString = String.valueOf(resultado);
            if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
            displayNumber.setText(displayNumberString);

            resetDisplay(view);
        } else {
            displayNumberString = String.valueOf(resultadoActual);
            raizCuadrada(view);
        }
    }

    public void seno (View view) {
        if (procesoOperacion == false && displayNumberString != "") {
            num1 = Double.parseDouble(displayNumberString);
            double resultado = Math.sin(num1);
            resultadoActual = (float) resultado;
            displayNumberString = String.valueOf(resultado);
            if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
            displayNumber.setText(displayNumberString + " rad");

            resetDisplay(view);
        } else {
            displayNumberString = String.valueOf(resultadoActual);
            raizCuadrada(view);
        }
    }

    public void coseno (View view) {
        if (procesoOperacion == false && displayNumberString != "") {
            num1 = Double.parseDouble(displayNumberString);
            double resultado = Math.cos(num1);
            resultadoActual = (float) resultado;
            displayNumberString = String.valueOf(resultado);
            if (displayNumberString.endsWith(".0")) {displayNumberString = String.valueOf((int)(resultado));};
            displayNumber.setText(displayNumberString + " rad");

            resetDisplay(view);
        } else {
            displayNumberString = String.valueOf(resultadoActual);
            raizCuadrada(view);
        }
    }
}