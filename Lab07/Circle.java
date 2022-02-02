
public class Circle extends Shape {
   private double radius;

 
   // Constructors
   public Circle()
   {
	  //add your code here

   }
   public Circle(String color, double radius) {
	   super(color);
	   this.radius = radius;
   }

   @Override
   public String toString() {
	return "Circle[radius = " +radius + ","+ super.toString()       ;
   }

   // Override the inherited getArea() to provide the proper implementation
   @Override
   public double getArea() {
      double area;
      area = radius*radius*Math.PI;
      return area;
   }

   public double getArea(double radius) {
	   	  	
	   	 this.radius = radius;
	   	return getArea();
	   }
}
