
/*
 * The Rectangle class, subclass of Shape
 */
public class Triangle extends Shape {
   private double base;
   private double height;
 
   // Constructors
   public Triangle()
   {
	  //add your code here

   }
   public Triangle(String color, double base, double height) {
	   super(color);
	   this.base = base;
	   this.height = height;
   }

   @Override
   public String toString() {
	return "Triangle[base = " +base +  ",height = "+ height + "," +super.toString()       ;
   }

   // Override the inherited getArea() to provide the proper implementation
   @Override
   public double getArea() {
      double area;
      area = 0.5*base*height;
      return area;
   }

   public double getArea(double base, double height) {
	   	  	this.base=base;
	   	this.height =  height;
	   	return getArea();
	   }
}