import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {
    JButton convertButton = new JButton("Converter");
    JButton swithCurrencyButton = new JButton("Trocar");
    JTextField textBox = new JTextField(10);
    JLabel resultLabel = new JLabel("Resultado: ");
    String[] choice1 = {"Option 1", "Option 2", "Option 3", "Option 4"};
    String[] choice2 = {"Option 1", "Option 2", "Option 3", "Option 4"};

    final JComboBox<String> cb1 = new JComboBox<>(choice1);
    final JComboBox<String> cb2 = new JComboBox<>(choice2);

    public Menu() {
        ProgramFunctions.getCurrency();
        super("Conversor de Moeda");
        setSize(500, 300);
        setLocationRelativeTo(null);

        //BotoesComAcao actionButtons = new BotoesComAcao();

        JLabel Label1 = new JLabel("Converter de ");
        JLabel Label2 = new JLabel(" Para ");
        JLabel convertType = new JLabel("Real -> Dolar");
        JLabel empity = new JLabel("_-_-_-_-_-_-_");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        panel.setSize(300, 300);

        panel.add(Label1);
        panel.add(cb1);
        panel.add(Label2);
        panel.add(cb2, gbc);
        panel.add(textBox, gbc);
        panel.add(convertButton, gbc);
        panel.add(swithCurrencyButton, gbc);
        panel.add(resultLabel, gbc);

        add(panel);
        setVisible(true);
        convertButton.addActionListener(this);
        swithCurrencyButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if (e.getSource() == convertButton) {
            String fielValue = textBox.getText();
            ProgramFunctions pFunction = new ProgramFunctions();
            //fielValue = fielValue.replaceAll("[a-z]", "");
            //fielValue = fielValue.replaceAll("[A-Z]", "");

            if (fielValue.trim().isEmpty()){
                resultLabel.setText("Campo Vazio");
                return;
            }

            if (!fielValue.matches("[0-9.,]+")){
                resultLabel.setText("Digite Apenas Numeros");
                textBox.setText("");
                return;
            }

            if (!fielValue.matches("[,]+")){
                fielValue = fielValue.replaceAll(",", ".");
            }

            String finalValue = String.valueOf(pFunction.convert(Double.parseDouble(fielValue)));
            resultLabel.setText("Resultado: " + finalValue);

        } else if (e.getSource() == swithCurrencyButton) {
            System.out.println("swithCurrencyButton");
        }
    }
}

public class ProgramFunctions{

    public double convert(double value){
        double valor_dolar = 5.19;
        return value * valor_dolar;
    }

    public static void getCurrency(){
        System.out.println(ApiConnect.apiResponse.contains(""));
    }
}


void main() {
    ApiConnect.urlBuild("latest/USD");
    ApiConnect.ApiConnect();
    javax.swing.SwingUtilities.invokeLater(() -> new Menu());
}