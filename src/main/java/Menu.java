import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton convertButton = new JButton("Converter");
    JButton swithCurrencyButton = new JButton("Trocar");
    JTextField textBox = new JTextField();
    JLabel resultLabel = new JLabel("Resultado: ");
    JLabel lastUpdate = new JLabel("", SwingConstants.CENTER);
    String[] choice1 = ReadFile.getFileContent();
    String[] choice2 = ReadFile.getFileContent();

    final JComboBox<String> cb1 = new JComboBox<>(choice1);
    final JComboBox<String> cb2 = new JComboBox<>(choice2);

    public Menu() {
        //--------- FRAME CONFIG------------
        super("Conversor de Moeda");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        //----------------------------------

        JLabel titleLabel = new JLabel("Conversor de Moedas", SwingConstants.CENTER);
        JLabel valueLabel = new JLabel("Valor: ");
        Font boldFont = new Font("Arial", Font.BOLD, 20);
    

        titleLabel.setFont(boldFont);
        resultLabel.setFont(boldFont);
        lastUpdate.setForeground(Color.gray);

        JLabel Label1 = new JLabel("Converter de ");
        JLabel Label2 = new JLabel(" Para ");

        JPanel titlePanel = new JPanel();
        JPanel contentPanel = new JPanel();
        JPanel contryPanel = new JPanel();
        JPanel valuePanel = new JPanel();
        JPanel resultPanel = new JPanel();

        JPanel marginLeft = new JPanel();
        JPanel marginRight = new JPanel();

        marginLeft.setPreferredSize(new Dimension(100, 100));
        marginRight.setPreferredSize(new Dimension(100, 100));



        //titlePanel.setBackground(Color.gray);
        titlePanel.setPreferredSize(new Dimension(100, 50));
        titlePanel.setLayout(new BorderLayout());

        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        //contryPanel.setBackground(Color.red);
        //valuePanel.setBackground(Color.blue);
        //resultPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        //contentPanel.setPreferredSize(new Dimension(100, 100));
        textBox.setPreferredSize(new Dimension(100, 25));

        add(marginLeft, BorderLayout.EAST);
        add(marginRight, BorderLayout.WEST);

        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        contentPanel.add(contryPanel);
        contentPanel.add(valuePanel);
        contentPanel.add(resultPanel);

        contryPanel.add(Label1);
        contryPanel.add(cb1);
        contryPanel.add(Label2);
        contryPanel.add(cb2);

        valuePanel.add(valueLabel);
        valuePanel.add(textBox);
        valuePanel.add(convertButton);
        //contentPanel.add(swithCurrencyButton);

        resultPanel.add(resultLabel);
        resultPanel.add(lastUpdate);

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
            lastUpdate.setText("Ultima atualização: " + ApiConnect.apiLastUpdate);

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