
/*
 * The Rectangle class, subclass of Shape
 */
public class Rectangle extends Shape {
   private double length;
   private double width;
 
   // Constructors
   public Rectangle()
   {
	  //add your code here

   }
   public Rectangle(String color, double length, double width) {
	   super(color);
	   this.length = length;
	   this.width = width;
   }

   @Override
   public String toString() {
	return "Rectangle[length = " +length +  ",width = "+width + ","+super.toString()       ;
   }

   // Override the inherited getArea() to provide the proper implementation
   @Override
   public double getArea() {
      double area;
      area = length*width;
      return area;
   }

   public double getArea(double length, double width) {
	   	  	this.length =length;
	   	this.width = width;
	   	return getArea();
   }
	   }
