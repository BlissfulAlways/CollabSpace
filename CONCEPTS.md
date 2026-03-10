# CollabSpace  
  
When a company starts a new software project, the first thing they do is ask these 3 questions :  
- Question 1: Where does the code live? : A folder structure that every team member understands. A predictable layout so when you open any part of the project you immediately know where you are.  
- Question 2: What does this project need to run? : Your code will depend on other people's code. Someone needs to write down exactly what those are and what versions - Dependency File. In Java it is usually pom.xml (Maven) and in JS it is package.json.  
- Question 3: How do the two halves talk during development? : You have a backend (Java) and a frontend (React). They are two separate programs. They run on different ports. They need to know about each other. Set it up.  
  
### Java Basics :  
- Java is built on one rule: everything is an object, and every object is built from a class (Java is object-oriented language).
- Class name follows PascalCase.  
- In Java, you cannot write code that floats freely. Every single instruction must live inside a method. Every method must live inside a class. There is no exception to this.  
public class HelloWorld{  
&nbsp;&nbsp;&nbsp;&nbsp;public static void main(String[] args){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println("Hello world");  
&nbsp;&nbsp;&nbsp;&nbsp;}  
}  
    
- In Java, access modifiers define which classes can see or use a specific field, method, or class.  
Project/  
│  
├── package_a/  
│   ├── Parent.java (Contains the variables)  
│   └── Neighbor.java (In the same folder/package)  
│  
└── package_b/  
    ├── Child.java (In a different folder, but extends Parent)  
    └── Stranger.java (In a different folder, no relation)
- public : Any class in any folder (package_a or package_b) can see and use this. It is open to the entire project.
- protected : Visible to Neighbor.java because it is in the same folder, and to Child.java because it inherits from the Parent, even though it is in a different folder.
- default (no keyword) : Visible only to Neighbor.java. Since Child.java and Stranger.java are in a different folder (package_b), they cannot see it at all.
- private : Visible only within Parent.java. No other file in any folder can access this. It is restricted to the specific file where it is written.  
  
public static void main(String[] args)  
- This is the one line Java looks for when you say "run this."  
- static — normally to use something inside a class, you first have to build an object from that class. But Java needs to run main before any objects exist. static means this method belongs to the class itself, not to any object built from it. Java can call it directly without building anything first.  
- void — every method in Java must declare what it gives back when it finishes. void means it gives back nothing. It just runs and ends.  
- String[] args — when you run a Java program from the terminal you can pass extra words after it. String means text. [] means it is a list of them. args is just the name given to that list.  
- System — a class that represents the environment your program is running in. The operating system, the terminal, the hardware.  
- out — a specific channel inside System that points to your terminal output. The screen.  
- println — a method that takes whatever you give it, converts it to text, writes it to the screen, and moves to the next line. print does the same without moving to the next line. println means print line.  