package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Sierpinski extends JPanel {
	private Triangle _trngl = new Triangle(0, 0, 500);
	private ArrayList<Triangle> _trngls = new ArrayList<Triangle>();
	
	public static void main(String[] args){
		
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(480, 480);
		frame.setVisible(true);
		
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.setName("Buttons");
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		frame.getContentPane().add(buttons);
		
		JButton startButton = new JButton("Draw Triangle");
		
		
		//startButton.setAlignmentX(RIGHT_ALIGNMENT);
		//startButton.setBounds(500, 20, 80, 20);
		//startButton.setMaximumSize(new Dimension(100, 20));
		//bgroup.add(new JButton("Some text"));
		final JSpinner recursionNumSelect = new JSpinner(	new SpinnerNumberModel(4,0,7,1));
		recursionNumSelect.setMaximumSize(new Dimension(40,30));
		buttons.add(startButton);
		buttons.setBorder(BorderFactory.createTitledBorder("Controls"));
		buttons.add(startButton);
		buttons.add(recursionNumSelect);
		
		
		
		final Sierpinski srp = new Sierpinski();
		
		frame.getContentPane().add(srp);
		srp.setBorder(BorderFactory.createTitledBorder("Sierpinski Triangle:"));
		startButton.addActionListener(new ActionListener() {
               
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                //System.out.println("You clicked the button");
            	srp.starter((Integer)recursionNumSelect.getValue());
            }
        }

		);
		
		
	}
	
	public Sierpinski() {
		setBackground(new Color(255,255,255));
	}

	public void starter(int recursions){
		Triangle trngl = new Triangle(30, 40, 400);
		_trngl = trngl;
		_trngls = splitTriangle(trngl, recursions);
	}
	
	private ArrayList<Triangle> splitTriangle(Triangle tr, int recursionNum) {
		/*if(recursionNum == 0){
			
		}*/
		
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		triangles.add(tr);
		
		double startX = tr.getX();
		double startY = tr.getY();
		double newSize = tr.getSideLength()/2;
		double newHeight = tr.getHeight()/2;
		
		System.out.println(tr.getHeight());
		tr.setTriangle(startX + newSize/2, startY, newSize);
		
		Triangle tr2 = new Triangle(startX, startY + newHeight, newSize);
		triangles.add(tr2);
		
		Triangle tr3 = new Triangle(startX + newSize, startY+newHeight, newSize);
		triangles.add(tr3);
		
		if(recursionNum > 0){
			triangles.addAll(splitTriangle(tr, recursionNum-1));
			triangles.addAll(splitTriangle(tr2, recursionNum-1));
			triangles.addAll(splitTriangle(tr3, recursionNum-1));
		}
		
		return triangles;
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.clearRect(0, 0, 800, 600);
		for(int i = 0; i < _trngls.size(); i++){
			//System.out.println(i);
			g.fillPolygon(_trngls.get(i).getPolygon());
		}
		repaint();
		g.dispose();
		//g.fillPolygon(_trngl.getPolygon());
	}
	
	private class Triangle{
		private double _x;
		private double _y;
		private double _sideLength;
		private Polygon _polygon;
		
		public Triangle(double x, double y, double sideLength) {
			setTriangle(x, y, sideLength);
		}
		
		public void setTriangle(double x, double y, double sideLength){
			_sideLength = sideLength;
			_x = x;
			_y = y;
			Point2D p1 = new Point2D.Double(x+sideLength, y+sideLength);//new Point(0, sideLength);
			Point2D p2 = new Point2D.Double(x, y+sideLength);//new Point(sideLength, sideLength);
			Point2D p3 = new Point2D.Double(x, y);
			double angleRad = Math.toRadians(30);
			
			AffineTransform at = AffineTransform.getRotateInstance(angleRad, p2.getX(), p2.getY());
			p3 = at.transform(p3, null);
			
			int[] xs = {(int)p1.getX(), (int)p2.getX(), (int)p3.getX()};
			int[] xy = {(int)p1.getY(), (int)p2.getY(), (int)p3.getY()};
			
			_polygon = new Polygon(xs, xy, 3);
			_polygon.translate(0, -(int)(p3.getY() - y));
		}

		public Polygon getPolygon() {
			return _polygon;
		}

		public void setPolygon(Polygon polygon) {
			_polygon = polygon;
		}

		public double getX() {
			return _x;
		}

		public void setX(double _x) {
			this._x = _x;
		}

		public double getY() {
			return _y;
		}

		public void setY(double _y) {
			this._y = _y;
		}

		public double getSideLength() {
			return _sideLength;
		}

		public void setSideLength(double _sideLength) {
			this._sideLength = _sideLength;
		}
		
		public double getHeight(){
			return _polygon.ypoints[0] - _polygon.ypoints[2];
		}
		
	}
}
