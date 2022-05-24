import java.awt.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;

public class Main extends JFrame {
    static final int m = 12;
    Thread thread;
    static float[][] mass = new float[2][m-1];
    static float[][] new_mass = new float[2][m-1];
    static float M;
    static float[] udel;
    static float sumCost = 0;
    static float sumWeight = 0;
    static int tec_kol_pred = 0;
    static int step = 0;
    static JLabel itemWeight_Ud[] = new JLabel[m];
    static JLabel itemCost_Ud[] = new JLabel[m];
    static JLabel itemUdCost_Ud[] = new JLabel[m];
    static JLabel itemProgress = new JLabel();
    static JLabel itemProgress_Ud = new JLabel();
    static JFrame frame = new JFrame("Задача о рюкзаке");
    static JLabel ruc1;
    static JLabel ruc2;
    static PrintBag printBag;
    static ProgressCost progCost;
    static ProgressItems progItems;
    AboutDialog dialog;
    static int y1;
    static int y2;
    static int prev_y1 = 400;
    static int prev_y2 = 330;
    static int state_bag = 0;
    static Color[] color_bag = new Color[m];
    static int[][] state_bag_mass = new int[m][5];
    static int[][] state_bag_mass_1 = new int[m][2];
    static int progressCountItems = -1;
    static int xProgressItems;
    static int progressCountCost = -1;
    static int xProgressCost;
    

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 1000));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setState(JFrame.NORMAL);
        Dimension labelSize = new Dimension(50, 20);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        Container allContainer = frame.getContentPane();
        allContainer.setLayout(new GridBagLayout());
        allContainer.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        Container mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(2, 2));
        mainContainer.setBackground(Color.WHITE);


        JPanel itemsPanel = new JPanel();
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Предметы"));
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setLayout(new GridLayout(3, 1));


            JLabel itemWeight[] = new JLabel[m];
            itemWeight[0] = new JLabel("Вес предмета");
            itemWeight[0].setBackground(Color.WHITE);
            itemWeight[0].setPreferredSize(new Dimension(150, 20));
            itemWeight[0].setBorder(solidBorder);
            for (int i = 1; i < m; i++) {
                itemWeight[i] = new JLabel();
                itemWeight[i].setPreferredSize(labelSize);
                itemWeight[i].setBackground(Color.WHITE);
                itemWeight[i].setBorder(solidBorder);
                itemWeight[i].setVerticalAlignment(JLabel.CENTER);
                itemWeight[i].setHorizontalAlignment(JLabel.CENTER);
            }

            JLabel itemCost[] = new JLabel[m];
            itemCost[0] = new JLabel("Стоимость предмета");
            itemCost[0].setPreferredSize(new Dimension(150, 20));
            itemCost[0].setBackground(Color.WHITE);
            itemCost[0].setBorder(solidBorder);
            for (int i = 1; i < m; i++) {
                itemCost[i] = new JLabel();
                itemCost[i].setPreferredSize(labelSize);
                itemCost[i].setBackground(Color.WHITE);
                itemCost[i].setBorder(solidBorder);
                itemCost[i].setVerticalAlignment(JLabel.CENTER);
                itemCost[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemProgress = new JLabel("Прогресс");
            itemProgress.setPreferredSize(new Dimension(150, 20));
            itemProgress.setBackground(Color.WHITE);
            progItems = new ProgressItems();
            progItems.setBackground(Color.WHITE);
            progItems.setPreferredSize(new Dimension(600, 40));

            JPanel preItemsPanel_1 = new JPanel();
            preItemsPanel_1.setLayout(new FlowLayout());
            preItemsPanel_1.setBackground(Color.WHITE);
            for (int i = 0; i < m; i++) {
                preItemsPanel_1.add(itemWeight[i]);
            }

            JPanel preItemsPanel_2 = new JPanel();
            preItemsPanel_2.setLayout(new FlowLayout());
            preItemsPanel_2.setBackground(Color.WHITE);
            for (int i = 0; i < m; i++) {
                preItemsPanel_2.add(itemCost[i]);
            }

            JPanel preItemsPanel_3 = new JPanel();
            preItemsPanel_3.setLayout(new FlowLayout());
            preItemsPanel_3.setBackground(Color.WHITE);
            preItemsPanel_3.add(itemProgress);
            preItemsPanel_3.add(progItems);

            itemsPanel.add(preItemsPanel_1);
            itemsPanel.add(preItemsPanel_2);
            itemsPanel.add(preItemsPanel_3);

        


        JPanel costPanel = new JPanel();
        costPanel.setBorder(BorderFactory.createTitledBorder("Удельная стоимость"));
        costPanel.setLayout(new GridLayout(4, 1));
        costPanel.setBackground(Color.WHITE);

            
            itemWeight_Ud[0] = new JLabel("Вес предмета");
            itemWeight_Ud[0].setPreferredSize(new Dimension(150, 20));
            itemWeight_Ud[0].setBackground(Color.WHITE);
            itemWeight_Ud[0].setBorder(solidBorder);
            for (int i = 1; i < m; i++) {
                itemWeight_Ud[i] = new JLabel();
                itemWeight_Ud[i].setPreferredSize(labelSize);
                itemWeight_Ud[i].setBackground(Color.WHITE);
                itemWeight_Ud[i].setBorder(solidBorder);
                itemWeight_Ud[i].setVerticalAlignment(JLabel.CENTER);
                itemWeight_Ud[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemCost_Ud[0] = new JLabel("Стоимость предмета");
            itemCost_Ud[0].setPreferredSize(new Dimension(150, 20));
            itemCost_Ud[0].setBackground(Color.WHITE);
            itemCost_Ud[0].setBorder(solidBorder);
            for (int i = 1; i < m; i++) {
                itemCost_Ud[i] = new JLabel();
                itemCost_Ud[i].setPreferredSize(labelSize);
                itemCost_Ud[i].setBackground(Color.WHITE);
                itemCost_Ud[i].setBorder(solidBorder);
                itemCost_Ud[i].setVerticalAlignment(JLabel.CENTER);
                itemCost_Ud[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemUdCost_Ud[0] = new JLabel("Удельная стоимость");
            itemUdCost_Ud[0].setPreferredSize(new Dimension(150, 20));
            itemUdCost_Ud[0].setBackground(Color.WHITE);
            itemUdCost_Ud[0].setBorder(solidBorder);
            for (int i = 1; i < m; i++) {
                itemUdCost_Ud[i] = new JLabel();
                itemUdCost_Ud[i].setPreferredSize(labelSize);
                itemUdCost_Ud[i].setBackground(Color.WHITE);
                itemUdCost_Ud[i].setBorder(solidBorder);
                itemUdCost_Ud[i].setVerticalAlignment(JLabel.CENTER);
                itemUdCost_Ud[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemProgress_Ud = new JLabel("Прогресс");
            itemProgress_Ud.setPreferredSize(new Dimension(150, 20));
            itemProgress_Ud.setBackground(Color.WHITE);
            progCost = new ProgressCost();
            progCost.setBackground(Color.WHITE);
            progCost.setBorder(solidBorder);
            progCost.setPreferredSize(new Dimension(600, 40));

            JPanel preCostPanel_1 = new JPanel();
            preCostPanel_1.setLayout(new FlowLayout());
            preCostPanel_1.setBackground(Color.WHITE);
            for (int i = 0; i < m; i++) {
                preCostPanel_1.add(itemWeight_Ud[i]);
            }

            JPanel preCostPanel_2 = new JPanel();
            preCostPanel_2.setLayout(new FlowLayout());
            preCostPanel_2.setBackground(Color.WHITE);
            for (int i = 0; i < m; i++) {
                preCostPanel_2.add(itemCost_Ud[i]);
            }

            JPanel preCostPanel_3 = new JPanel();
            preCostPanel_3.setLayout(new FlowLayout());
            preCostPanel_3.setBackground(Color.WHITE);
            for (int i = 0; i < m; i++) {
                preCostPanel_3.add(itemUdCost_Ud[i]);
            }

            JPanel preCostPanel_4 = new JPanel();
            preCostPanel_4.setLayout(new FlowLayout());
            preCostPanel_4.setBackground(Color.WHITE);
            preCostPanel_4.add(itemProgress_Ud);
            preCostPanel_4.add(progCost);

            costPanel.add(preCostPanel_1);
            costPanel.add(preCostPanel_2);
            costPanel.add(preCostPanel_3);
            costPanel.add(preCostPanel_4);



        JPanel bagPanel = new JPanel();
        bagPanel.setBorder(BorderFactory.createTitledBorder("Рюкзак"));
        bagPanel.setBackground(Color.WHITE);
        bagPanel.setLayout(new GridLayout(0, 2));
        bagPanel.setPreferredSize(new Dimension(850, 460));
            printBag = new PrintBag();
            printBag.setBackground(Color.WHITE);
            printBag.setPreferredSize(new Dimension(410, 410));
            JPanel bagPanel_1 = new JPanel();
            bagPanel_1.setLayout(new FlowLayout());
            bagPanel_1.setBackground(Color.WHITE);
                Dimension labelSize_bag = new Dimension(260, 20);
                JLabel bagLabel = new JLabel("Грузоподъемность рюкзака:");
                bagLabel.setPreferredSize(labelSize_bag);
                bagLabel.setBackground(Color.WHITE);

                JLabel ruc = new JLabel();
                ruc.setPreferredSize(labelSize);
                ruc.setBackground(Color.WHITE);

                JLabel tec_weight_bag = new JLabel("Текущая заполненность рюкзака: ");
                tec_weight_bag.setPreferredSize(labelSize_bag);
                tec_weight_bag.setBackground(Color.WHITE);

                ruc1 = new JLabel("0");
                ruc1.setPreferredSize(labelSize);
                ruc1.setBackground(Color.WHITE);

                JLabel tec_weight_bag_perc = new JLabel("Текущая заполненность рюкзака (в %): ");
                tec_weight_bag_perc.setPreferredSize(labelSize_bag);
                tec_weight_bag_perc.setBackground(Color.WHITE);

                ruc2 = new JLabel("0%");
                ruc2.setPreferredSize(labelSize);
                ruc2.setBackground(Color.WHITE);

            bagPanel_1.add(bagLabel);
            bagPanel_1.add(ruc);
            bagPanel_1.add(tec_weight_bag);
            bagPanel_1.add(ruc1);
            bagPanel_1.add(tec_weight_bag_perc);
            bagPanel_1.add(ruc2);
        bagPanel.add(bagPanel_1);
        bagPanel.add(printBag);
        

        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("Ввод"));
        addPanel.setLayout(new GridLayout(3, 0));
        addPanel.setBackground(Color.WHITE);

            JPanel addPanel_1 = new JPanel();
            addPanel_1.setLayout(new FlowLayout());
            addPanel_1.setBackground(Color.WHITE);
                JLabel addLabel_1 = new JLabel("Грузоподъемность");
                addLabel_1.setPreferredSize(new Dimension(150, 20));
                addLabel_1.setBackground(Color.WHITE);
                JTextField grus = new JTextField();
                grus.setPreferredSize(new Dimension(150, 20));
                grus.setBackground(Color.WHITE);
                JButton ok = new JButton("OK");
            addPanel_1.add(addLabel_1);
            addPanel_1.add(grus);
            addPanel_1.add(ok);

            JPanel addPanel_2 = new JPanel();
            addPanel_2.setLayout(new FlowLayout());
            addPanel_2.setBackground(Color.WHITE);
                JPanel addPanel_2_1 = new JPanel();
                addPanel_2_1.setLayout(new GridLayout(2, 2));
                addPanel_2_1.setBackground(Color.WHITE);
                    JLabel addLabel_2_1 = new JLabel("Вес");
                    addLabel_2_1.setPreferredSize(new Dimension(150, 20));
                    addLabel_2_1.setBackground(Color.WHITE);
                    JLabel addLabel_2_2 = new JLabel("Стоимость");
                    addLabel_2_2.setPreferredSize(new Dimension(150, 20));
                    addLabel_2_2.setBackground(Color.WHITE);
                    JTextField weight = new JTextField();
                    weight.setPreferredSize(new Dimension(20, 20));
                    weight.setBackground(Color.WHITE);
                    JTextField cost = new JTextField();
                    cost.setPreferredSize(new Dimension(20, 20));
                    cost.setBackground(Color.WHITE);
                addPanel_2_1.add(addLabel_2_1);
                addPanel_2_1.add(weight);
                addPanel_2_1.add(addLabel_2_2);
                addPanel_2_1.add(cost);

                JButton addButton = new JButton("Добавить");
                
            addPanel_2.add(addPanel_2_1);
            addPanel_2.add(addButton);

            JButton exitAddButton = new JButton("Завершить добавление");
            exitAddButton.setSize(new Dimension(150, 20));
        addPanel.add(addPanel_1);
        addPanel.add(addPanel_2);
        addPanel.add(exitAddButton);


        mainContainer.add(bagPanel);
        mainContainer.add(itemsPanel);
        mainContainer.add(addPanel);
        mainContainer.add(costPanel);

        JButton next_stepButton = new JButton("Следующий шаг");

        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        allContainer.add(mainContainer, c);


        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 1;
        allContainer.add(next_stepButton, c);

        ok.addActionListener(e -> {
            M = Float.parseFloat(grus.getText());
            ruc.setText(String.valueOf(M));
        });

        addButton.addActionListener(e ->{
            mass[0][tec_kol_pred] = Float.parseFloat(weight.getText());
            mass[1][tec_kol_pred] = Float.parseFloat(cost.getText());
            itemWeight[tec_kol_pred+1].setText(String.valueOf(mass[0][tec_kol_pred]));
            itemCost[tec_kol_pred+1].setText(String.valueOf(mass[1][tec_kol_pred]));
            weight.setText("");
            cost.setText("");
            tec_kol_pred++;
        });

        exitAddButton.addActionListener(e -> {
            thread = new AlgorithmThread(this);
            thread.start();
        });

        next_stepButton.addActionListener(e -> {
            step++;
        });

        frame.pack();
        frame.setVisible(true);
    }

    
    class AlgorithmThread extends Thread {
        Main main;

        public AlgorithmThread(Main main) {
            super("AlgorithmThread");
            this.main = main;
        }

        static void swap(float mass[], int ind1, int ind2) {
            float temp = mass[ind1];
            mass[ind1] = mass[ind2];
            mass[ind2] = temp;
    
        }
        
        static void bubble() {
            progressCountItems = -1;
            frame.repaint();

            for (int i = 0; i < mass.length; i++) {
                for (int j = 0; j < mass[0].length; j++) {
                    new_mass[i][j] = mass[i][j];
                }
            }
            Boolean need = true;
            while (need) {
                need = false;
                for (int i = 0; i < udel.length-1; i++) {
                    if (udel[i] < udel[i + 1]) {
                        swap(udel, i, i + 1);
                        itemUdCost_Ud[i+1].setText(String.valueOf(udel[i]));
                        itemUdCost_Ud[i+2].setText(String.valueOf(udel[i+1]));

                        swap(new_mass[0], i, i + 1);
                        itemWeight_Ud[i+1].setText(String.valueOf(new_mass[0][i]));
                        itemWeight_Ud[i+2].setText(String.valueOf(new_mass[0][i+1]));

                        swap(new_mass[1], i, i + 1);
                        itemCost_Ud[i+1].setText(String.valueOf(new_mass[1][i]));
                        itemCost_Ud[i+2].setText(String.valueOf(new_mass[1][i+1]));
                        
                        need = true;
                    }
                }
            }            
        }
    
        static float[] udel() {
            udel = new float[mass[0].length];
            int i = 0;
            while (true) {
                if (step == i) {
                    if (tec_kol_pred > i) {
                        progressCountItems++;
                            if (progressCountItems != -1) {
                                for (xProgressItems = 55 * (progressCountItems-1); xProgressItems < 55 * progressCountItems; xProgressItems++) {
                                    progItems.repaint();
                                    try {
                                        Thread.sleep(5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        if (mass[0][i] != 0) {
                            udel[i] = mass[1][i] / mass[0][i];         
                        } else {
                            udel[i] = 0;
                        }
                        itemUdCost_Ud[i+1].setText(String.valueOf(udel[i]));
                        itemCost_Ud[i+1].setText(String.valueOf(mass[1][i]));
                        itemWeight_Ud[i+1].setText(String.valueOf(mass[0][i]));
                    } else {
                        break;
                    }
                    i++;
                } else {
                    continue;
                }
            }
            return udel;
        }
    
        static float put_in_bag() {
            int i = 0;
            int l = step+1;
            int k = 0;
            while (true) {
                if (step == l) {
                    if (tec_kol_pred > k) {
                        if (new_mass[0][i] + sumWeight <= M) {
                            progressCountCost++;
                            if (progressCountCost != -1) {
                                for (xProgressCost = 55 * (progressCountCost-1); xProgressCost < 55 * progressCountCost; xProgressCost++) {
                                    progCost.repaint();
                                    try {
                                        Thread.sleep(4);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            sumWeight += new_mass[0][i];
                            sumCost += new_mass[1][i];
                            printBag.repaint();            
                            k++;
                            i++;
                            try {
                                sleep(150);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            state_bag++;
                        } else {
                            break;
                        }
                    } else { 
                        break;
                    }
                    l++;
                } else {
                    continue;
                }
            }
            return sumCost;
        } 
    
        public void run() {
            
            udel();
            
            bubble();
    
            put_in_bag();

            progressCountCost = -1;
            progCost.repaint();
            dialog = new AboutDialog(sumCost);
            dialog.setVisible(true);
        }
    }

    class PrintBag extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
    
            Graphics2D g2d = (Graphics2D)g;
            Random rand = new Random();
            
            float perc = sumWeight / M;
            if (state_bag != 0) {
                prev_y1 = y1;
                prev_y2 = y2;
            }
            y1 = (int)(400 - (100 * 3.3 * perc));
            y2 = (int)(330 - (100 * 3.3 * perc));

            color_bag[state_bag] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            state_bag_mass[state_bag][0] = prev_y1;
            state_bag_mass[state_bag][1] = y1;
            state_bag_mass[state_bag][2] = y2;
            state_bag_mass[state_bag][3] = prev_y2;
            state_bag_mass[state_bag][4] = prev_y1;

            state_bag_mass_1[state_bag][0] = y1;
            state_bag_mass_1[state_bag][1] = prev_y1 - y1;

            for (int i = 0; i < m-1; i++) {
                if (y1 != 0) {
                    g2d.setColor(color_bag[i]);
                    g2d.fillRect(0, state_bag_mass_1[i][0], 330, state_bag_mass_1[i][1]);
                    int[] xPointsFill = {330, 330, 400, 400, 330};
                    g2d.fillPolygon(xPointsFill, state_bag_mass[i], 5);
                }
            }
            
            g2d.setColor(Color.BLACK);
            int[] xPoints = {330, 330, 0, 0, 330, 170, 0, 70, 400, 400, 330, 330, 400};
            int[] yPoints = {70, 400, 400, 70, 70, 150, 70, 0, 0, 330, 400, 70, 0};
            g2d.drawPolygon(xPoints, yPoints, 13);

            ruc1.setText(String.valueOf(sumWeight));
            if (M != 0) {
                float res = sumWeight/M*100;
                String result = String.format("%.2f", res);
                ruc2.setText(result + "%");
            } else {
                ruc2.setText("0%");
            }
            
        }
    }

    class ProgressItems extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 600, 40);
            if (progressCountItems != -1) {                    
                g2d.setColor(Color.GREEN);
                g2d.fillOval(15 + xProgressItems, 10, 20, 20);
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    class ProgressCost extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 600, 40);
            if (progressCountCost != -1) {                    
                g2d.setColor(Color.GREEN);
                g2d.fillOval(15 + xProgressCost, 10, 20, 20);
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }

    class AboutDialog extends JDialog {
        public AboutDialog (float sumCost) {

            JLabel finalText = new JLabel("Итоговая стоимость вещей в рюкзаке: " + String.valueOf(sumCost));
            finalText.setVerticalAlignment(JLabel.CENTER);
            finalText.setHorizontalAlignment(JLabel.CENTER);

            add(finalText);
    
            JButton okButton = new JButton("ok");
            okButton.addActionListener(e -> {
                setVisible(false);
                System.exit(0);
            });
        
            JPanel panel = new JPanel();
            panel.add(okButton);
            add(panel, BorderLayout.SOUTH);
            setSize(350, 160);
        }
    }
}