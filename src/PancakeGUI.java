/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Ivan
 */
public class PancakeGUI extends JPanel implements ActionListener {

    private PancakeStack stack;
    private JButton resetButton;
    private DrawPanel drawPanel;
    private JPanel buttons;
    private JButton resolveButton;
    Timer timer;

    public PancakeGUI() {
        super(new BorderLayout());
        stack = new PancakeStack();
        resetButton = new JButton("Reset");
        resolveButton = new JButton("Resolve");
        drawPanel = new DrawPanel();
        buttons = new JPanel();
        
        

        buttons.add(resetButton);
        buttons.add(resolveButton);
        add(buttons, BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER);

        resetButton.addActionListener(this);
        resolveButton.addActionListener(this);
        
                

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == resetButton) {
            System.out.println("reset");
            stack = new PancakeStack();

        }
        if (source == resolveButton)
        {
//            System.out.println("Resolving");
            stack.pancakeSort();
        }
        drawPanel.repaint();
    }

   

    private class DrawPanel extends JPanel implements MouseListener {

        public DrawPanel() {
            setPreferredSize(new Dimension(600, 400));
            setBackground(Color.WHITE);
            addMouseListener(this);
            
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Pancake p;
            int height =getHeight()/stack.size();      
            int y = getHeight() - height;

            int width = 0;
            int x = getWidth() / 2; //centre of panel

            if (stack.size() != 0) {

                for (int i = 0; i < stack.size(); i++) {

                    p = stack.getPancake(i);
                    
                    

                    width = (p.getSize() * getWidth()) / stack.size();
//                    
                    p.draw(g, x - width / 2, y, width, height);

                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(p.getSize()), (x -width / 2), (height+y));

                    width -= width;
                    y -= height;
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int height = getHeight()/stack.size();
            
         int ycord = getHeight()-e.getY(); // "inverts" origin - 0 is now bottom of frame
            int index = ((ycord/stack.size() * (height))/height); // calcs index based on y click coord
            
//            System.out.println("Index" + index);
            stack.getPancake(index).highlight(true); 
            stack.flip(index);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PANCAKEGUI");
        frame.setResizable(false);
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PancakeGUI());
        frame.pack(); // resizes
        // position the frame in the middle of the screen

        Toolkit tk = Toolkit.getDefaultToolkit(); // gets toolkit for this OS
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);

        frame.setVisible(true);

    }
}
