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
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        //setContentPane(new BackGround());

        JLabel Label1 = new JLabel("Converter de ");
        JLabel Label2 = new JLabel(" Para ");

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        //panel.setSize(300, 300);
        panel.setBackground(Color.red);
        panel.setBounds(0, 0, 500, 500);

        //panel2.setSize(300, 300);
        /*panel2.setBackground(Color.blue);
        panel2.setBounds(250, 0, 250, 250);

        panel3.setBackground(Color.green);
        panel3.setBounds(0, 250, 500, 250);*/

        add(panel);
       /* add(panel2);
        add(panel3);*/

        panel.add(Label1);
        panel.add(cb1);
        panel.add(Label2);
        panel.add(cb2);
        panel.add(textBox);
        panel.add(convertButton);
        panel.add(swithCurrencyButton);
        panel.add(resultLabel);
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
                    + "/" + cb2.getSelectedItem() + "/" + fielValue);

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


public class BackGround extends JPanel{
    public void paintComponent(Graphics g) {
        ImageIcon image = new ImageIcon("C:\\Users\\andre.oliveira\\IdeaProjects\\conversor_de_moedas\\src\\main\\resources\\background.png");
        g.drawImage(image.getImage(), 0,0, 500, 500, null);

    }
}

void main() {
    javax.swing.SwingUtilities.invokeLater(() -> new Menu());
}