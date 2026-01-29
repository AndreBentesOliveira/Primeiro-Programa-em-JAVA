import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {
    JButton convertButton = new JButton("Converter");
    JButton swithCurrencyButton = new JButton("Trocar");
    JTextField textBox = new JTextField(10);
    JLabel resultLabel = new JLabel("Resultado: ");
    String[] choice1 = ReadFile.getFileContent();
    String[] choice2 = ReadFile.getFileContent();

    final JComboBox<String> cb1 = new JComboBox<>(choice1);
    final JComboBox<String> cb2 = new JComboBox<>(choice2);

    public Menu() {
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

            ApiConnect.urlBuild("pair/" + cb1.getSelectedItem()
                    + "/" + cb1.getSelectedItem() + "/" + fielValue);

            ApiConnect.ApiConnect();
            System.out.println(ApiConnect.apiResponse);
            resultLabel.setText("Resultado: " + ApiConnect.apiResponse);

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
}


void main() {
    javax.swing.SwingUtilities.invokeLater(() -> new Menu());
}