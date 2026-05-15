import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class HotelReservation_Domingo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            HotelReservationFrame_Domingo frame_domingo = new HotelReservationFrame_Domingo();
            frame_domingo.setVisible(true);
        });

    }
}

class HotelReservationFrame_Domingo extends JFrame {

    // Components
    public JTextField guestNameField_domingo = new JTextField(18);
    public JComboBox<String> roomTypeCombo_domingo =
            new JComboBox<>(new String[]{"-- Select Room --", "Standard", "Deluxe", "Suite"});
    public JTextField nightsField_domingo = new JTextField(4);
    public JTextField totalCostField_domingo = new JTextField(10);
    public JTextField paymentField_domingo = new JTextField(10);

    public JTextArea receiptArea_domingo = new JTextArea(12, 28);
    public JLabel statusLabel_domingo = new JLabel(" ");

    public Map<String, Integer> roomRates_domingo = new LinkedHashMap<>();


    // Frame
    public HotelReservationFrame_Domingo() {

        setTitle("Hotel Reservation System");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        getContentPane().setBackground(new Color(230,230,230));

        initRates_domingo();

        receiptArea_domingo.setEditable(false);
        receiptArea_domingo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        receiptArea_domingo.setBackground(new Color(245,245,245));

        totalCostField_domingo.setEditable(false);

        add(buildHeaderPanel_domingo(), BorderLayout.NORTH);
        add(buildMainPanel_domingo(), BorderLayout.CENTER);
        add(buildBottomPanel_domingo(), BorderLayout.SOUTH);

        roomTypeCombo_domingo.addActionListener(e -> clearTotals_domingo());
    }


    // Room Rates
    public void initRates_domingo() {

        roomRates_domingo.put("Standard", 150);
        roomRates_domingo.put("Deluxe", 220);
        roomRates_domingo.put("Suite", 325);

    }


    // Header Panel
    public JPanel buildHeaderPanel_domingo() {

        JPanel panel_domingo = new JPanel();
        panel_domingo.setBackground(new Color(30, 60, 120));

        JLabel title_domingo = new JLabel("JAVA CCS HOTEL");
        title_domingo.setForeground(Color.WHITE);
        title_domingo.setFont(new Font("Arial", Font.BOLD, 22));

        panel_domingo.add(title_domingo);

        return panel_domingo;
    }


    // Main Panel
    public JPanel buildMainPanel_domingo() {

        JPanel main_domingo = new JPanel(new BorderLayout(10, 10));
        main_domingo.setOpaque(false);

        main_domingo.add(buildFormPanel_domingo(), BorderLayout.WEST);
        main_domingo.add(buildReceiptPanel_domingo(), BorderLayout.CENTER);

        return main_domingo;
    }


    // Form Panel
    public JPanel buildFormPanel_domingo() {

        JPanel form_domingo = new JPanel(new GridBagLayout());
        form_domingo.setBorder(BorderFactory.createTitledBorder("Reservation Details"));
        form_domingo.setBackground(Color.WHITE);

        GridBagConstraints c_domingo = new GridBagConstraints();
        c_domingo.insets = new Insets(5,5,5,5);
        c_domingo.anchor = GridBagConstraints.WEST;

        c_domingo.gridx = 0; c_domingo.gridy = 0;
        form_domingo.add(new JLabel("Guest Name:"), c_domingo);

        c_domingo.gridx = 1;
        form_domingo.add(guestNameField_domingo, c_domingo);

        c_domingo.gridx = 0; c_domingo.gridy = 1;
        form_domingo.add(new JLabel("Room Type:"), c_domingo);

        c_domingo.gridx = 1;
        form_domingo.add(roomTypeCombo_domingo, c_domingo);

        c_domingo.gridx = 0; c_domingo.gridy = 2;
        form_domingo.add(new JLabel("Number of Nights:"), c_domingo);

        c_domingo.gridx = 1;
        form_domingo.add(nightsField_domingo, c_domingo);

        c_domingo.gridx = 0; c_domingo.gridy = 3;
        form_domingo.add(new JLabel("Total Cost:"), c_domingo);

        c_domingo.gridx = 1;
        form_domingo.add(totalCostField_domingo, c_domingo);

        c_domingo.gridx = 0; c_domingo.gridy = 4;
        form_domingo.add(new JLabel("Payment:"), c_domingo);

        c_domingo.gridx = 1;
        form_domingo.add(paymentField_domingo, c_domingo);

        return form_domingo;
    }


    // Receipt Panel
    public JPanel buildReceiptPanel_domingo() {

        JPanel panel_domingo = new JPanel(new BorderLayout());
        panel_domingo.setBorder(BorderFactory.createTitledBorder("Receipt"));
        panel_domingo.setBackground(Color.WHITE);

        panel_domingo.add(new JScrollPane(receiptArea_domingo), BorderLayout.CENTER);

        return panel_domingo;
    }


    // Bottom Panel
    public JPanel buildBottomPanel_domingo() {

        JPanel bottom_domingo = new JPanel(new BorderLayout());
        bottom_domingo.setBackground(new Color(220,220,220));

        bottom_domingo.add(buildButtonPanel_domingo(), BorderLayout.CENTER);

        statusLabel_domingo.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        bottom_domingo.add(statusLabel_domingo, BorderLayout.SOUTH);

        return bottom_domingo;
    }


 // Button Panel
    public JPanel buildButtonPanel_domingo() {

        JPanel panel_domingo = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        panel_domingo.setBackground(new Color(220,220,220));

        JButton calculateButton_domingo = new JButton("Calculate Total");
        JButton payButton_domingo = new JButton("Pay");
        JButton clearButton_domingo = new JButton("Clear");
        JButton exitButton_domingo = new JButton("Exit");

        // Button Colors
        calculateButton_domingo.setBackground(new Color(52,152,219));
        calculateButton_domingo.setForeground(Color.WHITE);

        payButton_domingo.setBackground(new Color(46,204,113));
        payButton_domingo.setForeground(Color.WHITE);

        clearButton_domingo.setBackground(new Color(241,196,15));

        exitButton_domingo.setBackground(new Color(231,76,60));
        exitButton_domingo.setForeground(Color.WHITE);

        // Button Actions
        calculateButton_domingo.addActionListener(e -> calculateTotal_domingo());
        payButton_domingo.addActionListener(e -> pay_domingo());
        clearButton_domingo.addActionListener(e -> clearForm_domingo());
        exitButton_domingo.addActionListener(e -> System.exit(0));

        panel_domingo.add(calculateButton_domingo);
        panel_domingo.add(payButton_domingo);
        panel_domingo.add(clearButton_domingo);
        panel_domingo.add(exitButton_domingo);

        return panel_domingo;
    }


    // Calculate Total
    public void calculateTotal_domingo() {

        try {

            String room_domingo = (String) roomTypeCombo_domingo.getSelectedItem();
            int nights_domingo = Integer.parseInt(nightsField_domingo.getText());

            int rate_domingo = roomRates_domingo.get(room_domingo);

            double total_domingo = rate_domingo * nights_domingo;

            totalCostField_domingo.setText(String.format("%.2f", total_domingo));

            setStatus_domingo("Total calculated successfully.", new Color(0,128,0));

        } catch(Exception e){

            setStatus_domingo("Invalid input.", Color.RED);
        }
    }


    // Pay
    public void pay_domingo() {

        try{

            double total_domingo = Double.parseDouble(totalCostField_domingo.getText());
            double payment_domingo = Double.parseDouble(paymentField_domingo.getText());

            if(payment_domingo < total_domingo){
                setStatus_domingo("Payment not enough.", Color.RED);
                return;
            }

            double change_domingo = payment_domingo - total_domingo;

            receiptArea_domingo.setText(
                    "Java CCS Hotel\n"+
                    "Guest: "+guestNameField_domingo.getText()+"\n"+
                    "Room: "+roomTypeCombo_domingo.getSelectedItem()+"\n"+
                    "Nights: "+nightsField_domingo.getText()+"\n"+
                    "Total: "+total_domingo+"\n"+
                    "Payment: "+payment_domingo+"\n"+
                    "Change: "+change_domingo+"\n"+
                    "Thank you for booking!"
            );

            setStatus_domingo("Payment accepted.", new Color(0,128,0));

        }catch(Exception e){

            setStatus_domingo("Invalid payment.", Color.RED);
        }
    }


    // Clear Totals
    public void clearTotals_domingo(){

        totalCostField_domingo.setText("");
        paymentField_domingo.setText("");
        receiptArea_domingo.setText("");
    }


    // Clear Form
    public void clearForm_domingo(){

        guestNameField_domingo.setText("");
        nightsField_domingo.setText("");
        paymentField_domingo.setText("");
        totalCostField_domingo.setText("");
        receiptArea_domingo.setText("");

        roomTypeCombo_domingo.setSelectedIndex(0);
    }


    // Status Message
    public void setStatus_domingo(String msg_domingo, Color color_domingo){

        statusLabel_domingo.setText(msg_domingo);
        statusLabel_domingo.setForeground(color_domingo);
    }
}