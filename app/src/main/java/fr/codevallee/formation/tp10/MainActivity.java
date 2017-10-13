package fr.codevallee.formation.tp10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons;
    private TextView[] stackView;
    private Stack<Integer> stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        //Récupération des éléments de l'interface et des boutons:
        GridLayout gridLayoutNumbers = (GridLayout) findViewById(R.id.gl_buttons);
        final TextView textViewInput = (TextView) findViewById(R.id.tv_input);

        final Button buttonMult = (Button) findViewById(R.id.button_mult);
        final Button buttonAdd = (Button) findViewById(R.id.button_add);
        final Button buttonSub = (Button) findViewById(R.id.button_sub);
        final Button buttonDiv = (Button) findViewById(R.id.button_div);

        final Button buttonClear = (Button) findViewById(R.id.button_clear);
        final Button buttonPop = (Button) findViewById(R.id.button_pop);
        final Button buttonSwap = (Button) findViewById(R.id.button_swap);
        final Button buttonEnter = (Button) findViewById(R.id.button_enter);

        //Récupération des vues pour la pile:
        this.stackView = new TextView[4];
        /*this.stackView[0] = (TextView) findViewById(R.id.tv_stack1);
        this.stackView[1] = (TextView) findViewById(R.id.tv_stack2);
        this.stackView[2] = (TextView) findViewById(R.id.tv_stack3);
        this.stackView[3] = (TextView) findViewById(R.id.tv_stack4);*/

        this.stackView[3] = (TextView) findViewById(R.id.tv_stack1);
        this.stackView[2] = (TextView) findViewById(R.id.tv_stack2);
        this.stackView[1] = (TextView) findViewById(R.id.tv_stack3);
        this.stackView[0] = (TextView) findViewById(R.id.tv_stack4);

        //Récuperation des boutons des nombres:
        this.buttons = new Button[10];
        for(int i=0 ; i<9 ; i++) {
            this.buttons[i] = (Button) gridLayoutNumbers.getChildAt(i);
            this.buttons[i].setText(""+(i+1));
        }
        this.buttons[9] = (Button) gridLayoutNumbers.getChildAt(9);
        this.buttons[9].setText("0");

        //Initialisation de la pile:
        this.stack = new Stack<Integer>();

        //Assignation des listeners aux boutons des numéros:
        for(int i=0 ; i<10 ; i++) {
            final Button actualButton = buttons[i];
            final int position = i;
            actualButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textViewInput.setText(textViewInput.getText() + "" + (position+1));
                }
            });
        }

        //Assignation des listeners aux boutons des fonctions:
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textViewInput.getText()!="") {
                    String numberEntered = "" + textViewInput.getText();
                    textViewInput.setText("");
                    stack.push(Integer.parseInt(numberEntered));
                    refreshStackView();
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*for(int i=0 ; i<stack.size() ; i++) {
                    stack.pop();
                }*/
                stack.clear();
                textViewInput.setText("");
                refreshStackView();
            }
        });
    }

    public void refreshStackView() {
        for(int i=0 ; i<this.stackView.length ; i++) {
            if(i<stack.size()) {
                this.stackView[i].setText("" + this.stack.get(i));
            } else {
                this.stackView[i].setText("");
            }
        }
    }
}