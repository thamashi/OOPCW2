import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Reel extends JFrame {

    private int initialCredit = 10;
    static boolean Reel1Clicked = false;
    static boolean Reel2Clicked = false;
    static boolean Reel3Clicked = false;
    private int reel1Value,reel2Value,reel3Value;
    private int i,i1,i2;
    private int wins = 0;
    private int loses = 0;
    private double average = 0;
    private int totalScore = 0;
    private int spinclick = 0;
    private JLabel lblCredit, lblBetArea;
    private JButton btnAdd, btnReset, btnStat, btnBet1, btnBetMax, btnReel1, btnReel2, btnReel3, btnSpin;
    
    ReelThread reel1 = new ReelThread();
        ReelThread reel2 = new ReelThread();
        ReelThread reel3 = new ReelThread();

    public Reel() {
        
        
        this.setTitle("Slot machine application");
        setSize(new Dimension(900, 600));

        GridBagLayout g = new GridBagLayout();
        this.setLayout(g);

        GridBagConstraints gb = new GridBagConstraints();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        gb.fill = GridBagConstraints.NORTHWEST;
        gb.insets = new Insets(3, 3, 3, 3);
        gb.gridx = 0;
        gb.gridy = 0;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        lblCredit = new JLabel("Credit area");
        lblCredit.setBorder(border);
        lblCredit.setFont(new Font("Calibri", Font.ITALIC, 20));
        this.add(lblCredit, gb);
        lblCredit.setText(null);
        lblCredit.setText(initialCredit + "");

        gb.fill = GridBagConstraints.NORTH;
        gb.gridx = 4;
        gb.gridy = 0;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        lblBetArea = new JLabel("Bet area");
        lblBetArea.setBorder(border);
        lblBetArea.setFont(new Font("Calibri", Font.ITALIC, 20));
        this.add(lblBetArea, gb);
        lblBetArea.setText("0");

        gb.fill = GridBagConstraints.VERTICAL;
        gb.gridx = 0;
        gb.gridy = 1;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnAdd = new JButton("Add coin");
        btnAdd.setBackground(Color.cyan);
        btnAdd.setFont(new Font("Calibri", Font.ITALIC, 16));
        this.add(btnAdd, gb);

        gb.gridx = 0;
        gb.gridy = 2;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnReset = new JButton("Reset");
        btnReset.setBackground(Color.cyan);
        btnReset.setFont(new Font("Calibri", Font.ITALIC, 16));
        this.add(btnReset, gb);

        gb.gridx = 0;
        gb.gridy = 3;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnStat = new JButton("Statistics");
        btnStat.setBackground(Color.cyan);
        btnStat.setFont(new Font("Calibri", Font.ITALIC, 16));
        this.add(btnStat, gb);

        gb.gridx = 1;
        gb.gridy = 1;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnReel1 = new JButton("");
        btnReel1.setIcon(new ImageIcon("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/lemon.png"));
        btnReel1.setBackground(Color.lightGray);
        this.add(btnReel1, gb);

        gb.gridx = 2;
        gb.gridy = 1;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnReel2 = new JButton("");
        btnReel2.setIcon(new ImageIcon("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/cherry.png"));
        btnReel2.setBackground(Color.lightGray);
        this.add(btnReel2, gb);

        gb.gridx = 3;
        gb.gridy = 1;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnReel3 = new JButton("");
        btnReel3.setIcon(new ImageIcon("C:/Users/Thamashi/Documents/NetBeansProjects/SlotMachineApplication/images/plum.png"));
        btnReel3.setBackground(Color.lightGray);
        this.add(btnReel3, gb);

        gb.gridx = 3;
        gb.gridy = 2;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnSpin = new JButton("Spin");
        btnSpin.setBackground(Color.GREEN);
        btnSpin.setFont(new Font("Calibri", Font.ITALIC, 22));
        this.add(btnSpin, gb);

        gb.gridx = 4;
        gb.gridy = 1;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnBet1 = new JButton("Bet one");
        btnBet1.setBackground(Color.cyan);
        btnBet1.setFont(new Font("Calibri", Font.ITALIC, 16));
        this.add(btnBet1, gb);

        gb.gridx = 4;
        gb.gridy = 2;
        gb.gridwidth = 1;
        gb.gridheight = 1;
        btnBetMax = new JButton("Bet max");
        btnBetMax.setBackground(Color.cyan);
        btnBetMax.setFont(new Font("Calibri", Font.ITALIC, 16));
        this.add(btnBetMax, gb);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setCreditValue(1);
                btnSpin.setEnabled(false);
            }
        });
        
        btnSpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentCredit = lblCredit.getText();
                int c2 = Integer.parseInt(currentCredit);
                btnSpin.setEnabled(false);

                if (c2 > 0) {
                    reel1.Spin(reel1.symbol);
                    reel2.Spin(reel2.symbol);
                    reel3.Spin(reel3.symbol);
                    
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Reel1Clicked = true;

                            while (Reel1Clicked) {

                                if (!btnReel1.getModel().isPressed()) {
                                    i = (int) Math.floor(Math.random() * 5);

                                    btnReel1.setIcon(new ImageIcon(reel1.symbol.get(i).getImage()));
                                    reel1Value = reel1.symbol.get(i).getValue();
                                }else{
                                    Reel1Clicked = false;
                                }
                            }
                        }
                    });
                    t.start();

                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Reel2Clicked = true;

                            while (Reel2Clicked) {
                                if (!btnReel2.getModel().isPressed()) {
                                    i1 = (int) Math.floor(Math.random() * 5);

                                    btnReel2.setIcon(new ImageIcon(reel2.symbol.get(i1).getImage()));
                                    
                                  reel2Value = reel2.symbol.get(i1).getValue();
                                }else{
                                    Reel1Clicked = false;
                                }
                            }
                        }
                    });
                    t1.start();

                    Thread t3 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Reel3Clicked = true;

                            while (Reel3Clicked) {
                                if (!btnReel3.getModel().isPressed()) {
                                    i2 = (int) Math.floor(Math.random() * 5);

                                    btnReel3.setIcon(new ImageIcon(reel3.symbol.get(i2).getImage()));
                                    reel3Value = reel3.symbol.get(i2).getValue();
                                } else {
                                    Reel1Clicked = false;
                                }
                            }
                        }
                    });
                    t3.start();

                    btnBet1.setEnabled(false);
                    btnBetMax.setEnabled(false);
                    btnAdd.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Game Over!!");
                }

            }
        });

        btnReel1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Reel1Clicked = false;
                    Reel2Clicked = false;
                    Reel3Clicked = false;
                    
                    Thread.sleep(100);
                    //matchImages();
                    btnSpin.setEnabled(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        });

        btnReel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Reel1Clicked = false;
                    Reel2Clicked = false;
                    Reel3Clicked = false;
                    Thread.sleep(100);
                    //matchImages();
                    btnSpin.setEnabled(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnReel3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Reel1Clicked = false;
                    Reel2Clicked = false;
                    Reel3Clicked = false;
                    Thread.sleep(100);
                    //matchImages();
                    reel3.symbol.get(i).getImage();
                    btnSpin.setEnabled(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnBet1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String currentCredit = lblCredit.getText();
                int c1 = Integer.parseInt(currentCredit);
                btnSpin.setEnabled(true);

                if (c1 >= 1) {
                    setBetValue(1);
                } else {
                    JOptionPane.showMessageDialog(null, "credits are over");
                }

            }
        });

        btnBetMax.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String currentCredit = lblCredit.getText();
                int c1 = Integer.parseInt(currentCredit);
                btnSpin.setEnabled(true);
                if (c1 >= 3) {
                    setBetValue(3);
                } else {
                    JOptionPane.showMessageDialog(null, "credits are over");
                }

            }
        });

        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                btnAdd.setEnabled(true);
                btnBet1.setEnabled(true);
                btnBetMax.setEnabled(true);
                btnSpin.setEnabled(false);

            }
        });

        btnStat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                average();
                Vector v = new Vector();
                v.add(wins);
                v.add(loses);
                v.add(average);

                StatisticTable te = new StatisticTable(v);
                te.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        Reel r = new Reel();
        r.setVisible(true);
    }
    
    	public boolean matchImages() {
        boolean matched = false;
        
        //String path = this.getClass().getResource("/images/").getPath();
        String reel1image = reel1.symbol.get(reel1Value).getImage();
        String reel2image = reel2.symbol.get(reel2Value).getImage();
        String reel3image = reel3.symbol.get(reel3Value).getImage();

        if (reel1image.equalsIgnoreCase(reel2image)
                && (!reel1image.equalsIgnoreCase(reel3image) || !reel2image.equalsIgnoreCase(reel3image))) {
            JOptionPane.showMessageDialog(null, "You get another chance!");
            matched = true;
            spinclick++;
            btnBet1.setEnabled(false);
            btnBetMax.setEnabled(false);
            btnAdd.setEnabled(false);

        } else if (reel2image.equalsIgnoreCase(reel3image)
                && (!reel1image.equalsIgnoreCase(reel3image) || !reel1image.equalsIgnoreCase(reel2image))) {
            JOptionPane.showMessageDialog(null, "You get another chance!");
            matched = true;
            spinclick++;
            btnBet1.setEnabled(false);
            btnBetMax.setEnabled(false);
            btnAdd.setEnabled(false);

        } else if (reel1image.equalsIgnoreCase(reel3image)
                && (!reel2image.equalsIgnoreCase(reel3image) || !reel2image.equalsIgnoreCase(reel1image))) {
            JOptionPane.showMessageDialog(null, "You get another chance!");
            matched = true;
            spinclick++;
            btnBet1.setEnabled(false);
            btnBetMax.setEnabled(false);
            btnAdd.setEnabled(false);

        } else if (reel1image.equalsIgnoreCase(reel2image) && reel2image.equalsIgnoreCase(reel3image)) {
            JOptionPane.showMessageDialog(null, "YOU WIN!!");
            wins++;
            matched = true;
            spinclick++;
            btnAdd.setEnabled(false);
            score();
        } else {
            JOptionPane.showMessageDialog(null, "You Lost! \nPlace a bet in order to spin again");
            loses++;
            matched = false;
            spinclick++;
            lblBetArea.setText("0");
            btnBet1.setEnabled(true);
            btnBetMax.setEnabled(true);
            btnAdd.setEnabled(false);
        }
        return matched;
    }
    

    public void reset() {
        String bet = lblBetArea.getText();
        int b = Integer.parseInt(bet);
        String currentCredit = lblCredit.getText();
        int c = Integer.parseInt(currentCredit);

        c = c + b;
        lblCredit.setText(c + "");
        lblBetArea.setText("0");

    }

    public void setCreditValue(int value) {
        String currentCredit = lblCredit.getText();
        int c = Integer.parseInt(currentCredit);
        c++;
        lblCredit.setText(c + "");
    }

    public void setBetValue(int value) {

        String val = lblBetArea.getText();
        int betOne = Integer.parseInt(val);
        betOne = betOne + value;
        String crdt = lblCredit.getText();
        int crdt1 = Integer.parseInt(crdt);
        crdt1 = crdt1 - value;
        lblCredit.setText(crdt1 + "");
        lblBetArea.setText(betOne + "");
    }

    public void average() {
        String c3 = lblCredit.getText();
        int currentCredits = Integer.parseInt(c3);

        average = (currentCredits / spinclick);
    }
    
    public void score() {
        int mark = 0;
        String c3 = lblCredit.getText();
        int currentC = Integer.parseInt(c3);

        String b = lblBetArea.getText();
        int betAmount = Integer.parseInt(b);

       //String path = this.getClass().getResource("/images/").getPath();
        String reel1image = reel1.symbol.get(reel1Value).getImage();
        String reel2image = reel2.symbol.get(reel2Value).getImage();
        String reel3image = reel3.symbol.get(reel3Value).getImage();


        if (reel1image.equalsIgnoreCase(reel2image) && reel2image.equalsIgnoreCase(reel3image)) {
            mark = reel1.symbol.get(reel1Value).getValue();
            totalScore = currentC + (betAmount * mark);
            System.out.println(totalScore);
            lblCredit.setText(totalScore + "");
        }
    }

}
