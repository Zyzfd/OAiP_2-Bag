import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.*;

public class Main extends JFrame {
    final int m = 20;
    final int timertime = 1500;
    Thread thread;
    float[][] mass = new float[2][m-1];
    float[][] new_mass = new float[2][m-1];
    float M;
    float[] udel;
    float sumCost = 0;
    float sumWeight = 0;
    int tec_kol_pred = 0;
    int bagCounter = 0;
    int step = 0;
    JLabel itemWeight[] = new JLabel[m];
    JLabel itemCost[] = new JLabel[m];
    JLabel itemUdCost[] = new JLabel[m];
    JLabel itemProgress = new JLabel();
    JFrame frame = new JFrame("Задача о рюкзаке");
    JLabel ruc1;
    JLabel ruc2;
    PrintBag printBag;
    Progress progress;
    AboutDialog dialog;
    int y1;
    int y2;
    int prev_y1 = 400;
    int prev_y2 = 330;
    int state_bag = 0;
    Color[] color_bag = new Color[m];
    int[][] state_bag_mass = new int[m][5];
    int[][] state_bag_mass_1 = new int[m][2];
    int progressCountItems = -1;
    int xProgress;
    int progressCountCost = -1;
    Font font = new Font("Sans-serif", Font.PLAIN, 20);
    Dimension nameDimension = new Dimension (210, 30);
    Dimension labelSize = new Dimension(70, 30);
    Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    Timer time;
    TimerTask timerTask;
    boolean exit = true;
    boolean progressOk = false;
    
    

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 1000));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setState(JFrame.NORMAL);

        Container allContainer = frame.getContentPane();
        allContainer.setLayout(new GridBagLayout());
        allContainer.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        Container mainContainer = new Container();
        mainContainer.setLayout(new GridBagLayout());
        mainContainer.setBackground(Color.WHITE);        


        JPanel itemsPanel = new JPanel();
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Предметы"));
        itemsPanel.setLayout(new GridLayout(4, 1));
        itemsPanel.setBackground(Color.WHITE);

            
            itemWeight[0] = new JLabel("Вес предмета");
            itemWeight[0].setPreferredSize(nameDimension);
            itemWeight[0].setBackground(Color.WHITE);
            itemWeight[0].setBorder(solidBorder);
            itemWeight[0].setFont(font);
            itemWeight[0].setVerticalAlignment(JLabel.CENTER);
            itemWeight[0].setHorizontalAlignment(JLabel.CENTER);
            for (int i = 1; i < m; i++) {
                itemWeight[i] = new JLabel();
                itemWeight[i].setPreferredSize(labelSize);
                itemWeight[i].setBackground(Color.WHITE);
                itemWeight[i].setBorder(solidBorder);
                itemWeight[i].setFont(font);
                itemWeight[i].setVerticalAlignment(JLabel.CENTER);
                itemWeight[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemCost[0] = new JLabel("Стоимость предмета");
            itemCost[0].setPreferredSize(nameDimension);
            itemCost[0].setBackground(Color.WHITE);
            itemCost[0].setBorder(solidBorder);
            itemCost[0].setFont(font);
            itemCost[0].setVerticalAlignment(JLabel.CENTER);
            itemCost[0].setHorizontalAlignment(JLabel.CENTER);
            for (int i = 1; i < m; i++) {
                itemCost[i] = new JLabel();
                itemCost[i].setPreferredSize(labelSize);
                itemCost[i].setBackground(Color.WHITE);
                itemCost[i].setBorder(solidBorder);
                itemCost[i].setFont(font);
                itemCost[i].setVerticalAlignment(JLabel.CENTER);
                itemCost[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemUdCost[0] = new JLabel("Удельная стоимость");
            itemUdCost[0].setPreferredSize(nameDimension);
            itemUdCost[0].setBackground(Color.WHITE);
            itemUdCost[0].setBorder(solidBorder);
            itemUdCost[0].setFont(font);
            itemUdCost[0].setVerticalAlignment(JLabel.CENTER);
            itemUdCost[0].setHorizontalAlignment(JLabel.CENTER);
            for (int i = 1; i < m; i++) {
                itemUdCost[i] = new JLabel();
                itemUdCost[i].setPreferredSize(labelSize);
                itemUdCost[i].setBackground(Color.WHITE);
                itemUdCost[i].setBorder(solidBorder);
                itemUdCost[i].setFont(font);
                itemUdCost[i].setVerticalAlignment(JLabel.CENTER);
                itemUdCost[i].setHorizontalAlignment(JLabel.CENTER);
            }

            
            itemProgress = new JLabel("Прогресс");
            itemProgress.setPreferredSize(nameDimension);
            itemProgress.setBackground(Color.WHITE);
            itemProgress.setFont(font);
            itemProgress.setVerticalAlignment(JLabel.CENTER);
            itemProgress.setHorizontalAlignment(JLabel.CENTER);
            progress = new Progress();
            progress.setBackground(Color.WHITE);
            progress.setPreferredSize(new Dimension(1420, 40));

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
            for (int i = 0; i < m; i++) {
                preItemsPanel_3.add(itemUdCost[i]);
            }

            JPanel preItemsPanel_4 = new JPanel();
            preItemsPanel_4.setLayout(new FlowLayout());
            preItemsPanel_4.setBackground(Color.WHITE);
            preItemsPanel_4.add(itemProgress);
            preItemsPanel_4.add(progress);

            itemsPanel.add(preItemsPanel_1);
            itemsPanel.add(preItemsPanel_2);
            itemsPanel.add(preItemsPanel_3);
            itemsPanel.add(preItemsPanel_4);



        JPanel bagPanel = new JPanel();
        bagPanel.setBorder(BorderFactory.createTitledBorder("Рюкзак"));
        bagPanel.setBackground(Color.WHITE);
        bagPanel.setLayout(new GridLayout(0, 2));
        bagPanel.setPreferredSize(new Dimension(920, 460));
            printBag = new PrintBag();
            printBag.setBackground(Color.WHITE);
            printBag.setPreferredSize(new Dimension(410, 410));
            JPanel bagPanel_1 = new JPanel();
            bagPanel_1.setLayout(new FlowLayout());
            bagPanel_1.setBackground(Color.WHITE);
                Dimension labelSize_bag = new Dimension(370, 30);
                JLabel bagLabel = new JLabel("Грузоподъемность рюкзака:");
                bagLabel.setFont(font);
                bagLabel.setPreferredSize(labelSize_bag);
                bagLabel.setBackground(Color.WHITE);

                JLabel ruc = new JLabel();
                ruc.setFont(font);
                ruc.setPreferredSize(labelSize);
                ruc.setBackground(Color.WHITE);

                JLabel tec_weight_bag = new JLabel("Текущая заполненность рюкзака: ");
                tec_weight_bag.setFont(font);
                tec_weight_bag.setPreferredSize(labelSize_bag);
                tec_weight_bag.setBackground(Color.WHITE);

                ruc1 = new JLabel("0");
                ruc1.setFont(font);
                ruc1.setPreferredSize(labelSize);
                ruc1.setBackground(Color.WHITE);

                JLabel tec_weight_bag_perc = new JLabel("Текущая заполненность рюкзака %:");
                tec_weight_bag_perc.setFont(font);
                tec_weight_bag_perc.setPreferredSize(labelSize_bag);
                tec_weight_bag_perc.setBackground(Color.WHITE);

                ruc2 = new JLabel("0%");
                ruc2.setFont(font);
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
                addLabel_1.setFont(font);
                addLabel_1.setPreferredSize(nameDimension);
                addLabel_1.setBackground(Color.WHITE);
                JTextField grus = new JTextField();
                grus.setFont(font);
                grus.setPreferredSize(nameDimension);
                grus.setBackground(Color.WHITE);
                JButton ok = new JButton("OK");
                ok.setFont(font);
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
                    addLabel_2_1.setFont(font);
                    addLabel_2_1.setPreferredSize(nameDimension);
                    addLabel_2_1.setBackground(Color.WHITE);
                    JLabel addLabel_2_2 = new JLabel("Стоимость");
                    addLabel_2_2.setFont(font);
                    addLabel_2_2.setPreferredSize(nameDimension);
                    addLabel_2_2.setBackground(Color.WHITE);
                    JTextField weight = new JTextField();
                    weight.setFont(font);
                    weight.setPreferredSize(new Dimension(20, 20));
                    weight.setBackground(Color.WHITE);
                    JTextField cost = new JTextField();
                    cost.setFont(font);
                    cost.setPreferredSize(new Dimension(20, 20));
                    cost.setBackground(Color.WHITE);
                addPanel_2_1.add(addLabel_2_1);
                addPanel_2_1.add(weight);
                addPanel_2_1.add(addLabel_2_2);
                addPanel_2_1.add(cost);

                JButton addButton = new JButton("Добавить");
                addButton.setFont(font);
                
            addPanel_2.add(addPanel_2_1);
            addPanel_2.add(addButton);

            JButton exitAddButton = new JButton("Завершить добавление");
            exitAddButton.setFont(font);
            exitAddButton.setSize(nameDimension);
        addPanel.add(addPanel_1);
        addPanel.add(addPanel_2);
        addPanel.add(exitAddButton);

        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        mainContainer.add(itemsPanel, c);

        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        mainContainer.add(bagPanel, c);

        c.gridwidth = GridBagConstraints.VERTICAL;
        c.fill = 1;
        c.gridx = 1;
        c.gridy = 1;
        mainContainer.add(addPanel, c);
        

        JButton next_stepButton = new JButton("Следующий шаг");
        next_stepButton.setFont(font);
        
        JButton stop_timerButton = new JButton("Остановить");
        stop_timerButton.setFont(font);
        
        JButton continue_timerButton = new JButton("Продолжить");
        continue_timerButton.setFont(font);


        c.gridx = 0;
        c.gridy = 0;
        allContainer.add(mainContainer, c);

        c.gridx = 0;
        c.gridy = 1;
        allContainer.add(next_stepButton, c);
        
        c.gridx = 0;
        c.gridy = 2;
        allContainer.add(stop_timerButton, c);
        
        c.gridx = 0;
        c.gridy = 3;
        allContainer.add(continue_timerButton, c);

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
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    nextStep();
                }
            };
            time = new Timer();
            time.scheduleAtFixedRate(timerTask, 0, timertime);
            dialog = new AboutDialog(sumCost);
            dialog.setVisible(false);
        });

        next_stepButton.addActionListener(e -> {
            nextStep();
        });

        stop_timerButton.addActionListener(e -> {
            time.cancel();
        });
        
        continue_timerButton.addActionListener(e -> {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    nextStep();
                }
            };
            time = new Timer();
            time.scheduleAtFixedRate(timerTask, 0, timertime);
        });

        frame.pack();
        frame.setVisible(true);
    }


    void nextStep() {
        thread = new AlgorithmThread(this);
        thread.start();
    }


    class AlgorithmThread extends Thread {
        Main main;

        public AlgorithmThread(Main main) {
            super("AlgorithmThread");
            this.main = main;
        }
        void swap(float mass[], int ind1, int ind2) {
            float temp = mass[ind1];
            mass[ind1] = mass[ind2];
            mass[ind2] = temp;

        }

        void bubble() {
            progressOk = false;
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
                        itemUdCost[i+1].setText(String.valueOf(udel[i]));
                        itemUdCost[i+2].setText(String.valueOf(udel[i+1]));

                        swap(new_mass[0], i, i + 1);
                        itemWeight[i+1].setText(String.valueOf(new_mass[0][i]));
                        itemWeight[i+2].setText(String.valueOf(new_mass[0][i+1]));

                        swap(new_mass[1], i, i + 1);
                        itemCost[i+1].setText(String.valueOf(new_mass[1][i]));
                        itemCost[i+2].setText(String.valueOf(new_mass[1][i+1]));
                        
                        need = true;
                    }
                }
            }      
        }

        void udel() {
            if (step == 0) {
                udel = new float[mass[0].length];
            }
            progressOk = true;
            progressCountItems++;
                if (progressCountItems != -1) {
                    for (xProgress = 75 * (progressCountItems-1); xProgress < 75 * progressCountItems; xProgress++) {
                        frame.repaint();
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            if (mass[0][step] != 0) {
                udel[step] = mass[1][step] / mass[0][step];         
            } else {
                udel[step] = 0;
            }
            itemUdCost[step+1].setText(String.valueOf(udel[step]));
        }

        int put_in_bag() {
            if (new_mass[0][bagCounter] + sumWeight <= M) {
                progressOk = true;
                progressCountCost++;
                if (progressCountCost != -1) {
                    for (xProgress = 75 * (progressCountCost-1); xProgress < 75 * progressCountCost; xProgress++) {
                        progress.repaint();
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                sumWeight += new_mass[0][bagCounter];
                sumCost += new_mass[1][bagCounter];
                printBag.repaint();
                bagCounter++;
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state_bag++;
            } else {
                return 0;
            }

            return 1;
        }

        void exit() {
            if (exit) {
                progressOk = false;
                progress.repaint();
                dialog = new AboutDialog(sumCost);
                dialog.setVisible(true);
                exit = false;
            }
        }

        public void run() {
            if (step < tec_kol_pred) {
                udel();
            } else if (step == tec_kol_pred){
                bubble();
            } else if (step <= tec_kol_pred * 2) {
                if (put_in_bag() == 0) {
                    exit();
                }
            } else if (step > tec_kol_pred * 2) {
                exit();
            }
            Thread.currentThread().interrupt();
            step++;
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

    class Progress extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 600, 40);
            if (progressOk) {                    
                g2d.setColor(Color.GREEN);
                g2d.fillOval(25 + xProgress, 10, 20, 20);
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }

    class AboutDialog extends JDialog {
        public AboutDialog (float sumCost) {
            JLabel finalText = new JLabel("Итоговая стоимость вещей в рюкзаке: " + String.valueOf(sumCost));
            finalText.setFont(font);
            finalText.setVerticalAlignment(JLabel.CENTER);
            finalText.setHorizontalAlignment(JLabel.CENTER);

            add(finalText);
    
            JButton okButton = new JButton("ok");
            okButton.setFont(font);
            okButton.addActionListener(e -> {
                setVisible(false);
                System.exit(0);
            });
        
            JPanel panel = new JPanel();
            panel.add(okButton);
            add(panel, BorderLayout.SOUTH);
            pack();
        }
    }
}